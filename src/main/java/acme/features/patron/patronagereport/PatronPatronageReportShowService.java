package acme.features.patron.patronagereport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.patronagereports.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowService implements AbstractShowService<Patron,PatronageReport>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageReportRepository repository;

	@Autowired
	protected ExchangeService exchangeRepository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		final int patronId = request.getPrincipal().getActiveRoleId();
		final int patronageId = request.getModel().getInteger("id");
		final int patronageInventorId = this.repository.findOnePatronageReportById(patronageId).getPatronage().getPatron().getId();

		return  patronId == patronageInventorId; 
	}

	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findOnePatronageReportById(id);
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationTime", "memorandum", "info", "patronage.status", "patronage.legalStuff", "patronage.budget", "patronage.creationDate", "patronage.startDate", "patronage.endDate", "patronage.info", "patronage.code");
	
		final Money exchange=this.exchangeRepository.getExchange(entity.getPatronage().getBudget());
		model.setAttribute("exchange", exchange);
	}

}