package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetectorService;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	SpamDetectorService spamDetectorService;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;

		Inventor inventor;
		Toolkit result;

		inventor = this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId());
		result = new Toolkit();
		result.setDraftMode(true);
		result.setInventor(inventor);

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
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Toolkit existing;

			existing = this.repository.findOneToolkitByCode(entity.getCode());
			errors.state(request, existing == null, "code", "inventor.toolkit.form.error.duplicated");
		}
		if (!errors.hasErrors("title")) {
			final boolean spamStrong = this.spamDetectorService.ratioSurpassesThreshold(entity.getTitle(), 
				this.repository.getSystemConfiguration().getStrongSpamThreshold(), 
				this.repository.getSystemConfiguration().getStrongSpamTerms());
			final boolean spamWeak = this.spamDetectorService.ratioSurpassesThreshold(entity.getTitle(), 
				this.repository.getSystemConfiguration().getWeakSpamThreshold(), 
				this.repository.getSystemConfiguration().getWeakSpamTerms());
			
			errors.state(request, !(spamStrong || spamWeak), "title", "inventor.toolkit.form.error.spam");
		}
		if(!errors.hasErrors("description")) {
			final boolean spamStrong = this.spamDetectorService.ratioSurpassesThreshold(entity.getDescription(), 
				this.repository.getSystemConfiguration().getStrongSpamThreshold(), 
				this.repository.getSystemConfiguration().getStrongSpamTerms());
			final boolean spamWeak = this.spamDetectorService.ratioSurpassesThreshold(entity.getDescription(), 
				this.repository.getSystemConfiguration().getWeakSpamThreshold(), 
				this.repository.getSystemConfiguration().getWeakSpamTerms());
			
			errors.state(request, !(spamStrong || spamWeak), "description", "inventor.toolkit.form.error.spam");			
		}
		if(!errors.hasErrors("assemblyNotes")) {
			final boolean spamStrong = this.spamDetectorService.ratioSurpassesThreshold(entity.getAssemblyNotes(), 
				this.repository.getSystemConfiguration().getStrongSpamThreshold(), 
				this.repository.getSystemConfiguration().getStrongSpamTerms());
			final boolean spamWeak = this.spamDetectorService.ratioSurpassesThreshold(entity.getAssemblyNotes(), 
				this.repository.getSystemConfiguration().getWeakSpamThreshold(), 
				this.repository.getSystemConfiguration().getWeakSpamTerms());
			
			errors.state(request, !(spamStrong || spamWeak), "assemblyNotes", "inventor.toolkit.form.error.spam");			
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
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
