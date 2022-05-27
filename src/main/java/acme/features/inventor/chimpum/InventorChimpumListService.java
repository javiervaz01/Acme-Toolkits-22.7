package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpums.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorChimpumListService implements AbstractListService<Inventor, Chimpum>{

	@Autowired
	InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {

		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Chimpum> findMany(final Request<Chimpum> request) {
		
		assert request != null;
		
		int inventorId;
		
		inventorId = request.getPrincipal().getActiveRoleId();
		return this.repository.findChimpumsByInventorId(inventorId);
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "creationDate","title","startDate","endDate","budget");

		
	}
	
	

}
