package acme.features.authenticated.patron.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

	@Query("select count(p) from Patronage p where p.status = PROPOSED")
	Double numberOfProposedPatronages();

	@Query("select count(p) from Patronage p where p.status = ACCEPTED")
	Double numberOfAcceptedPatronages();

	@Query("select count(p) from Patronage p where p.status = DENIED")
	Double numberOfDeniedPatronages();

	@Query("select avg(p.budget.amount) from Patronage p where p.status = PROPOSED")
	Double averageBudgetofProposedPatronages();

	@Query("select stddev(p.budget.amount) from Patronage p where p.status = PROPOSED")
	Double deviationBudgetofProposedPatronages();

	@Query("select max(p.budget.amount) from Patronage p where p.status = PROPOSED")
	Double maximumBudgetofProposedPatronages();

	@Query("select min(p.budget.amount) from Patronage p where p.status = PROPOSED")
	Double minimumBudgetofProposedPatronages();

	@Query("select avg(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	Double averageBudgetofAcceptedPatronages();

	@Query("select stddev(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	Double deviationBudgetofAcceptedPatronages();

	@Query("select max(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	Double maximumBudgetofAccepetdPatronages();

	@Query("select min(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	Double minimumBudgetofAcceptedPatronages();

	@Query("select avg(p.budget.amount) from Patronage p where p.status = DENIED")
	Double averageBudgetofDeniedPatronages();

	@Query("select stddev(p.budget.amount) from Patronage p where p.status = DENIED")
	Double deviationBudgetofDeniedPatronages();

	@Query("select max(p.budget.amount) from Patronage p where p.status = DENIED")
	Double maximumBudgetofDeniedPatronages();

	@Query("select min(p.budget.amount) from Patronage p where p.status = DENIED")
	Double minimumBudgetofDeniedPatronages();

}
