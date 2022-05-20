package acme.features.any.chirp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirps.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;



@Service
public class AnyChirpShowService implements AbstractShowService<Any, Chirp> {
	
	@Autowired
	protected AnyChirpRepository repository;

	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;

		return true;
	}

	@Override
	public Chirp findOne(final Request<Chirp> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		return this.repository.findChirpById(id);
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title", "author", "body", "email");
	}
}
