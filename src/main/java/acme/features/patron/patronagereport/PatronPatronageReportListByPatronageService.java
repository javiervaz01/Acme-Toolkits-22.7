package acme.features.patron.patronagereport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronagereports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportListByPatronageService implements AbstractListService<Patron,PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageReportRepository repository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		final int patronId = request.getPrincipal().getActiveRoleId();
		final int patronageId = request.getModel().getInteger("masterId");
		final Patronage patronage = this.repository.findOnePatronageById(patronageId);
		final int patronagePatronId = patronage.getPatron().getId();
		
		return patronId == patronagePatronId && !patronage.isDraftMode(); 
	}

	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		assert request != null;

		final int masterId;

		masterId = request.getModel().getInteger("masterId");
		
		return this.repository.findPatronageReportsByMasterId(masterId);
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final Collection<PatronageReport> entities, final Model model) {
		assert request != null;
		assert entities != null;
		assert model != null;

		request.unbind(entities, model);
		
		final int masterId;

		masterId = request.getModel().getInteger("masterId");

		model.setAttribute("masterId", masterId);
	}
	
	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationTime", "memorandum", "info", "patronage.code");
	}
}