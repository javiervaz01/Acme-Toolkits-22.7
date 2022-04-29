package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorItemListByToolkitService implements AbstractListService<Inventor,Item> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorItemRepository repository;

		@Override
		public boolean authorise(final Request<Item> request) {
			assert request != null;
			int id;
			id = request.getModel().getInteger("masterId");
			final Toolkit requested = this.repository.findOneToolkitById(id);
			return !requested.isDraftMode() || request.isPrincipal(requested.getInventor());
		}
		
		@Override
		public Collection<Item> findMany(final Request<Item> request) {
			assert request != null;

			Collection<Item> result;
			final int id;

			id = request.getModel().getInteger("masterId");
			result = this.repository.findItemsByToolkitId(id);

			return result;
		}
		
		@Override
		public void unbind(final Request<Item> request, final Collection<Item> entities, final Model model) {
			assert request != null;
			assert !CollectionHelper.someNull(entities);
			assert model != null;

			int masterId;
			Toolkit toolkit;
			final boolean showCreate;

			masterId = request.getModel().getInteger("masterId");
			toolkit = this.repository.findOneToolkitById(masterId);
			showCreate = (toolkit.isDraftMode() && request.isPrincipal(toolkit.getInventor()));

			model.setAttribute("masterId", masterId);
			model.setAttribute("showCreate", showCreate);
		}

		@Override
		public void unbind(final Request<Item> request, final Item entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info", "type");
		}
}
