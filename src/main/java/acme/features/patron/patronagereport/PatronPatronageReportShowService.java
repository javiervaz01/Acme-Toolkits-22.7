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
	protected ExchangeService exchangeService;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		final int patronId = request.getPrincipal().getActiveRoleId();
		final int patronageReportId = request.getModel().getInteger("id");
		final int patronageInventorId = this.repository.findPatronIdByPatronageReportId(patronageReportId);

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

		request.unbind(entity, model, "sequenceNumber", "creationTime", "memorandum", "info");	
		
		final int masterId = request.getModel().getInteger("id");
		model.setAttribute("masterId", masterId);
		
		final Money exchange=this.exchangeService.getExchange(entity.getPatronage().getBudget());
		model.setAttribute("exchange", exchange);
		
		model.setAttribute("patronageStatus", entity.getPatronage().getStatus());
		model.setAttribute("patronageLegalStuff", entity.getPatronage().getLegalStuff());
		model.setAttribute("patronageBudget", entity.getPatronage().getBudget());
		model.setAttribute("patronageCreationDate", entity.getPatronage().getCreationDate());
		model.setAttribute("patronageStartDate", entity.getPatronage().getStartDate());
		model.setAttribute("patronageEndDate", entity.getPatronage().getEndDate());
		model.setAttribute("patronageInfo", entity.getPatronage().getInfo());
		model.setAttribute("patronageCode", entity.getPatronage().getCode());
	}
}