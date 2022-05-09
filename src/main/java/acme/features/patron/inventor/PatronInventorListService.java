package acme.features.patron.inventor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronInventorListService implements AbstractListService<Patron, Inventor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronInventorRepository repository;

	@Override
	public boolean authorise(final Request<Inventor> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Inventor> findMany(final Request<Inventor> request) {
		assert request != null;

		return this.repository.findAllInventors();

	}

	@Override
	public void unbind(final Request<Inventor> request, final Inventor entity,
			final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		
		request.unbind(entity, model);
		final String username = entity.getUserAccount().getUsername();
		final String name = entity.getUserAccount().getIdentity().getName();
		final String surname = entity.getUserAccount().getIdentity().getSurname();
		model.setAttribute("username", username);
		model.setAttribute("name", name);
		model.setAttribute("surname", surname);
	}
}
