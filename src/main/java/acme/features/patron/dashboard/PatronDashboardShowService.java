package acme.features.patron.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;

	// AbstractShowService<Patron, PatronDashboard> interface ----------------


	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;
		
		PatronDashboard result;
		int numberOfProposedPatronages;
		int numberOfAcceptedPatronages;
		int numberOfDeniedPatronages;
		Map<String,List<Double>> stastBudgetofProposedPatronages;
		Map<String,List<Double>> stastBudgetofAcceptedPatronages;
		Map<String,List<Double>> statsBudgetofDeniedPatronages;
		
		numberOfProposedPatronages=this.repository.numberOfProposedPatronages();
		numberOfAcceptedPatronages=this.repository.numberOfAcceptedPatronages();
		numberOfDeniedPatronages=this.repository.numberOfDeniedPatronages();
		stastBudgetofProposedPatronages=this.repository.stastBudgetofProposedPatronages();
		stastBudgetofAcceptedPatronages=this.repository.stastBudgetofAcceptedPatronages();
		statsBudgetofDeniedPatronages=this.repository.statsBudgetofDeniedPatronages();
		
		result= new PatronDashboard();
		result.setNumberOfProposedPatronages(numberOfProposedPatronages);
		result.setNumberOfAcceptedPatronages(numberOfAcceptedPatronages);
		result.setNumberOfDeniedPatronages(numberOfDeniedPatronages);
		result.setStastBudgetofAcceptedPatronages(stastBudgetofAcceptedPatronages);
		result.setStastBudgetofProposedPatronages(stastBudgetofProposedPatronages);
		result.setStastBudgetofDeniedPatronages(statsBudgetofDeniedPatronages);
		
		return null;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"averageNumberOfJobsPerEmployer", "averageNumberOfApplicationsPerWorker", // 
			"avegageNumberOfApplicationsPerEmployer", "ratioOfPendingApplications", //
			"ratioOfRejectedApplications", "ratioOfAcceptedApplications");
	}

}
