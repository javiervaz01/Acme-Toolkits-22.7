package acme.features.patron.patronage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {
	
	@Autowired
	protected PatronPatronageRepository repository;


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		final Patron patron;

		patron = this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId());
		result = new Patronage();
		result.setDraftMode(true);
		result.setStatus(Status.PROPOSED);
		result.setPatron(patron);
		final Date creationDate = new Date(System.currentTimeMillis() - 1);
		result.setCreationDate(creationDate);
		final Integer inventorId = request.getModel().getInteger("inventorId");
		final Inventor inventor = this.repository.findOneInventorById(inventorId);
		result.setInventor(inventor);

		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "legalStuff", "budget", "startDate", "endDate", "info");
	}
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("startDate")) {
			 final Date creationDate = entity.getCreationDate();
			 final Date minimumStartDate = new Date();
			 if(creationDate.getMonth()<11) {
				 minimumStartDate.setHours(creationDate.getHours());
				 minimumStartDate.setMinutes(creationDate.getMinutes());
				 minimumStartDate.setSeconds(creationDate.getSeconds());
				 minimumStartDate.setYear(creationDate.getYear());
				 minimumStartDate.setMonth(creationDate.getMonth()+1);
			 }
			 else {
				 minimumStartDate.setHours(creationDate.getHours());
				 minimumStartDate.setMinutes(creationDate.getMinutes());
				 minimumStartDate.setSeconds(creationDate.getSeconds());
				 minimumStartDate.setYear(creationDate.getYear()+1);
				 minimumStartDate.setMonth(0); 
			 }
		
			 final Date startDate = entity.getStartDate();			
			errors.state(request, minimumStartDate.equals(startDate) || minimumStartDate.before(startDate), "startDate", "patron.patronage.form.error.too-close");
		}

		if (!errors.hasErrors("code")) {
			Patronage existing;

			existing = this.repository.findOnePatronageByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "patron.patronage.form.error.duplicated");
		}

		if (!errors.hasErrors("budget")) {
			
			errors.state(request, entity.getBudget().getAmount()>=0., "budget", "patron.patronage.form.error.negative-budget");
		}
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget","creationDate","startDate", "endDate", "info","draftMode");
	
		final Integer inventorId = request.getModel().getInteger("inventorId");
		model.setAttribute("inventorId", inventorId);
	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
