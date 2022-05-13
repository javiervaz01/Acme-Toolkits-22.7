package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListService implements AbstractListService<Any, Toolkit>{

	@Autowired
	protected AnyToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;

		
		Collection<Toolkit> result;
		result = this.repository.findToolkits();
		return result;
		/*result = this.repository.findToolkitsIncludingItemsInfo();
		return result;*/
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "draftMode");
		
		//ID FUNCIONA EN SHOW, NO EN LIST
		final int id = request.getModel().getInteger("id");
		final Collection<Item> items = this.repository.findItemsByToolkitId(id);
		
		String payload = "";
		
		for(final Item item: items) {
			if(payload.isEmpty()) {
				payload = String.format("%s; %s; %s; %s; %s; %s; %s; %s", 
										item.getName(),
										item.getCode(),
										item.getTechnology(),
										item.getDescription(),
										item.getRetailPrice(),
										item.getInfo(),
										item.getType(),
										item.getInventor());
				model.setAttribute("payload", payload);
			} else { 
				payload = String.format("%s; %s; %s; %s; %s; %s; %s; %s; %s", 
										payload,
										item.getName(),
										item.getCode(),
										item.getTechnology(),
										item.getDescription(),
										item.getRetailPrice(),
										item.getInfo(),
										item.getType(),
										item.getInventor());
				model.setAttribute("payload", payload);
			}
			
		}
		
	}



}