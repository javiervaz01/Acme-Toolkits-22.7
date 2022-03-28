package acme.features.patron.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

	@Query("select count(p) from Patronage p where p.status = PROPOSED")
	int numberOfProposedPatronages();

	@Query("select count(p) from Patronage p where p.status = ACCEPTED")
	int numberOfAcceptedPatronages();

	@Query("select count(p) from Patronage p where p.status = DENIED")
	int numberOfDeniedPatronages();

	@Query("select avg(p.budget.amount), stddev(p.budget.amount), min(p.budget.amount), max(p.budget.amount) from Patronage p WHERE p.status=PROPOSED group by p.budget.currency")
	Map<String,List<Double>> stastBudgetofProposedPatronages();
	
	@Query("select avg(p.budget.amount),stddev(p.budget.amount),min(p.budget.amount),max(p.budget.amount) from Patronage p WHERE p.status=ACCEPTED group by p.budget.currency")
	Map<String,List<Double>> stastBudgetofAcceptedPatronages();
	
	@Query("select avg(p.budget.amount),stddev(p.budget.amount),min(p.budget.amount),max(p.budget.amount) from Patronage p WHERE p.status=DENIED group by p.budget.currency")
	Map<String,List<Double>> statsBudgetofDeniedPatronages();

}
