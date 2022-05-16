package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamService;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	@Autowired
	protected SpamService spamService;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int id;
		Toolkit toolkit;

		id = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(id);
		result = (toolkit != null && toolkit.isDraftMode() && request.isPrincipal(toolkit.getInventor()));

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);

		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Toolkit existing;
			Integer id;

			id = request.getModel().getInteger("id");
			existing = this.repository.findOneToolkitByCode(entity.getCode());

			errors.state(request, existing == null || existing.getId() == id, "code",
					"inventor.toolkit.form.error.duplicated");
		}
		if (!errors.hasErrors("title")) {
			errors.state(request, !this.spamService.isSpam(entity.getTitle()), "title",
					"inventor.toolkit.form.error.spam");
		}
		if (!errors.hasErrors("description")) {
			errors.state(request, !this.spamService.isSpam(entity.getDescription()), "description",
					"inventor.toolkit.form.error.spam");
		}
		if (!errors.hasErrors("assemblyNotes")) {
			errors.state(request, !this.spamService.isSpam(entity.getAssemblyNotes()), "assemblyNotes",
					"inventor.toolkit.form.error.spam");
		}
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "draftMode");
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
