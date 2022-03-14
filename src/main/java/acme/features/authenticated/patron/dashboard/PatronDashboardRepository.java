package acme.features.authenticated.patron.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository{
	
	@Query("select count(e) from Patronage p where p.status = PROPOSED")
	Double						numberOfProposedPatronages();
	
	@Query("select count(e) from Patronage p where p.status = ACCEPTED")
	Double						numberOfAcceptedPatronages();
	
	@Query("select count(e) from Patronage p where p.status = DENIED")
	Double						numberOfDeniedPatronages();
	
	@Query("select avg(select p.budget from Patronage p where p.status = PROPOSED)")
	Double						averageBudgetofProposedPatronages();
	
	@Query("select stddev(select p.budget from Patronage p where p.status = PROPOSED)")
	Double						deviationBudgetofProposedPatronages();
	
	@Query("select max(select p.budget from Patronage p where p.status = PROPOSED)")
	Double						maximumBudgetofProposedPatronages();
	
	@Query("select min(select p.budget from Patronage p where p.status = PROPOSED)")
	Double						minimumBudgetofProposedPatronages();
	
	@Query("select avg(select p.budget from Patronage p where p.status = ACCEPTED)")
	Double						averageBudgetofAcceptedPatronages();
	
	@Query("select stddev(select p.budget from Patronage p where p.status = ACCEPTED)")
	Double						deviationBudgetofAcceptedPatronages();
	
	@Query("select max(select p.budget from Patronage p where p.status = ACCEPTED)")
	Double						maximumBudgetofAccepetdPatronages();
	
	@Query("select min(select p.budget from Patronage p where p.status = ACCEPTED)")
	Double						minimumBudgetofAcceptedPatronages();
	
	@Query("select avg(select p.budget from Patronage p where p.status = DENIED)")
	Double						averageBudgetofDeniedPatronages();
	
	@Query("select stddev(select p.budget from Patronage p where p.status = DENIED)")
	Double						deviationBudgetofDeniedPatronages();
	
	@Query("select max(select p.budget from Patronage p where p.status = DENIED)")
	Double						maximumBudgetofDeniedPatronages();
	
	@Query("select min(select p.budget from Patronage p where p.status = DENIED)")
	Double						minimumBudgetofDeniedPatronages();

}
