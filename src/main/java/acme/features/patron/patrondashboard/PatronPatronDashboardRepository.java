package acme.features.patron.patrondashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronDashboardRepository extends AbstractRepository {

	@Query("select count(p) from Patronage p where p.status = :status and p.patron.id =:id ")
	int numberOfPatronages(Status status, int id);

	@Query("select distinct p.budget.currency from Patronage p")
	Collection<String> currencies();
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = :status and p.budget.currency = :currency and p.patron.id =:id ")
	Double averagePatronage(Status status,String currency, int id);
	
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = :status and p.budget.currency = :currency and p.patron.id =:id  ")
	Double deviationPatronage(Status status,String currency, int id);
	
	@Query("select min(p.budget.amount) from Patronage p where p.status = :status and p.budget.currency = :currency and p.patron.id =:id ")
	Double minimumPatronage(Status status,String currency, int id);
	
	@Query("select max(p.budget.amount) from Patronage p where p.status = :status and p.budget.currency = :currency and p.patron.id =:id ")
	Double maximumPatronage(Status status,String currency, int id);

}
