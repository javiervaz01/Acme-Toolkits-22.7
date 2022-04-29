package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.features.inventor.patronagereport.InventorPatronageReportRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageRepository repository;
	
	@Autowired
	protected InventorPatronageReportRepository repositoryReport;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		// Check that the inventor trying to see the patronage is the one linked to it
		
		final int patronId = request.getPrincipal().getActiveRoleId();
		final int patronageId = request.getModel().getInteger("id");
		final int patronageInventorId = this.repository.findOnePatronageById(patronageId).getInventor().getId();
		
		return patronId == patronageInventorId; 
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findOnePatronageById(id);
	}

	@SuppressWarnings("unused") // The variables are being used in the unbind() as Strings, so SonarLint does not detect them
	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String patronName = entity.getPatron().getIdentity().getName();
		final String patronSurname = entity.getPatron().getIdentity().getSurname();
		final String patronEmail = entity.getPatron().getIdentity().getEmail();
		final String patronCompany = entity.getPatron().getCompany();
		final String patronStatement = entity.getPatron().getStatement();
		final String patronInfo = entity.getPatron().getInfo();
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startDate", "endDate", "info",
			"patronName", "patronSurname", "patronEmail", "patronCompany", "patronStatement", "patronInfo");
	
		final int masterId = request.getModel().getInteger("id");
		model.setAttribute("masterId", masterId);
	}
}
