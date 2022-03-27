package acme.features.patron.dashboard;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

	@Query("select new map () from Patronage p WHERE p.status= PROPOSED")
	List<Map<String,Double>> stastBudgetofProposedPatronages();
	
	@Query("select new map () from Patronage p WHERE p.status= ACCEPTED")
	Collection<Map<String, Double>> stastBudgetofAcceptedPatronages();
	
	@Query("select new map () from Patronage p WHERE p.status= DENIED")
	Collection<Map<String,Double>> statsBudgetofDeniedPatronages();

}
