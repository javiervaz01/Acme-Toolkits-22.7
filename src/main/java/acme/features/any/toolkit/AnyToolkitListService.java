package acme.features.any.toolkit;

import java.util.Collection;
import java.util.stream.Collectors;

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
	public void unbind(final Request<Toolkit> request, final Collection<Toolkit> entities, final Model model) {
		
		assert request != null;
		assert entities != null;
		assert model != null;

//		request.unbind(entities, model, "code", "title", "description", "assemblyNotes", "link", "draftMode");
	}
	

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "draftMode");
		
		//ID FUNCIONA EN SHOW, NO EN LIST
		final int id = entity.getId();
		final Collection<Item> items = this.repository.findItemsByToolkitId(id);
		
		final String payload = String.join(", ", items.stream().map(Item::toString).collect(Collectors.toList()));
		
		model.setAttribute("payload", payload);
	}
	
	
}
