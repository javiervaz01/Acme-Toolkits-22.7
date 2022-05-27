package acme.features.inventor.chimpum;

import org.springframework.stereotype.Service;

import acme.entities.chimpums.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum>{

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		// TODO Auto-generated method stub
		
	}

}
