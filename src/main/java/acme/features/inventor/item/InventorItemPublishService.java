package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.components.SpamDetectorService;
import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
	public class InventorItemPublishService implements AbstractUpdateService<Inventor, Item> {

		// Internal state ---------------------------------------------------------
		
		@Autowired
		SpamDetectorService spamDetectorService;
	
		@Autowired
		protected InventorItemRepository repository;
		
		@Autowired
		protected ExchangeService exchangeService;


		@Override
		public boolean authorise(final Request<Item> request) {
			assert request != null;

			boolean result;
			int id;
			Item item;
			Inventor inventor;

			id = request.getModel().getInteger("id");
			item = this.repository.findItemById(id);
			inventor = item.getInventor();
			result = item.isDraftMode() && request.isPrincipal(inventor);

			return result;
		}

		@Override
		public Item findOne(final Request<Item> request) {
			assert request != null;

			Item result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findItemById(id);

			return result;
		}

		@Override
		public void bind(final Request<Item> request, final Item entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "info", "type");
		}

		@Override
		public void validate(final Request<Item> request, final Item entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			if (!errors.hasErrors("code")) {
				Item existing;
				Integer id;
				
				id = request.getModel().getInteger("id");
				existing = this.repository.findOneItemByCode(entity.getCode());
				
				errors.state(request, existing == null || existing.getId() == id, "code", "inventor.item.form.error.duplicated");
			}
			
			if (!errors.hasErrors("retailPrice")) {
				Double retailPrice;

				retailPrice = entity.getRetailPrice().getAmount();
				errors.state(request, retailPrice > 0.0, "retailPrice", "inventor.item.form.error.negative-price");
			}
			if (!errors.hasErrors("technology")) {
				final boolean spamStrong = this.spamDetectorService.ratioSurpassesThreshold(entity.getTechnology(), 
					this.repository.getSystemConfiguration().getStrongSpamThreshold(), 
					this.repository.getSystemConfiguration().getStrongSpamTerms());
				final boolean spamWeak = this.spamDetectorService.ratioSurpassesThreshold(entity.getTechnology(), 
					this.repository.getSystemConfiguration().getWeakSpamThreshold(), 
					this.repository.getSystemConfiguration().getWeakSpamTerms());
				
				errors.state(request, !(spamStrong || spamWeak), "technology", "inventor.item.form.error.spam");
			}
			if(!errors.hasErrors("description")) {
				final boolean spamStrong = this.spamDetectorService.ratioSurpassesThreshold(entity.getDescription(), 
					this.repository.getSystemConfiguration().getStrongSpamThreshold(), 
					this.repository.getSystemConfiguration().getStrongSpamTerms());
				final boolean spamWeak = this.spamDetectorService.ratioSurpassesThreshold(entity.getDescription(), 
					this.repository.getSystemConfiguration().getWeakSpamThreshold(), 
					this.repository.getSystemConfiguration().getWeakSpamTerms());
				
				errors.state(request, !(spamStrong || spamWeak), "description", "inventor.item.form.error.spam");			
			}
		}

		@Override
		public void unbind(final Request<Item> request, final Item entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info", "type",
					"draftMode");

			final Money exchange = this.exchangeService.getExchange(entity.getRetailPrice());
			model.setAttribute("exchange", exchange);
		}

		@Override
		public void update(final Request<Item> request, final Item entity) {
			assert request != null;
			assert entity != null;

			entity.setDraftMode(false);
			this.repository.save(entity);
		}
	}
