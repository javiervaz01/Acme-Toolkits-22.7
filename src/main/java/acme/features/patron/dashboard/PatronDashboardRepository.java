package acme.features.patron.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

	@Query("select count(p) from Patronage p where p.status = PROPOSED")
	int numberOfProposedPatronages();

	@Query("select count(p) from Patronage p where p.status = ACCEPTED")
	int numberOfAcceptedPatronages();

	@Query("select count(p) from Patronage p where p.status = DENIED")
	int numberOfDeniedPatronages();

	@Query("select distinct p.budget.currency from Patronage p")
	Collection<String> currencies();
	
	@Query("select avg(p) from Patronage p where p.status = :status and p.budget.currency = :currency")
	double averagePatronage(Status status,String currency);
	
	@Query("select stddev(p) from Patronage p where p.status = :status and p.budget.currency = :currency ")
	double deviationPatronage(Status status,String currency);
	
	@Query("select min(p) from Patronage p where p.status = :status and p.budget.currency = :currency ")
	double minimunPatronage(Status status,String currency);
	
	@Query("select max(p) from Patronage p where p.status = :status and p.budget.currency = :currency ")
	double maximunPatronage(Status status,String currency);

}
