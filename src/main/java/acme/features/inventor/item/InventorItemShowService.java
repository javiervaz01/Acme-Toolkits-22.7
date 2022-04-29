package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;



@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item> {
	
	@Autowired
	protected InventorItemRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int id;
		Toolkit toolkit;
		
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitByItemId(id);
		result = (toolkit != null && (!toolkit.isDraftMode() || request.isPrincipal(toolkit.getInventor())));
		
		return result;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		int id;
		Item result;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findItemById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info", "type");
		
		final Toolkit toolkit = this.repository.findOneToolkitByItemId(entity.getId());
		model.setAttribute("masterId", toolkit.getId());
		model.setAttribute("draftMode", toolkit.isDraftMode());

	}
}
