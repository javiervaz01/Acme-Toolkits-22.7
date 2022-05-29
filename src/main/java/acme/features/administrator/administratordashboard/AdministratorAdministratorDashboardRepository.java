
package acme.features.administrator.administratordashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Item i where i.type = 0 ")
	int numberOfComponents();
	
	@Query("select distinct i.technology from Item i where i.type = 0")
	Collection<String> technologies();

	@Query("select avg(i.retailPrice.amount) from Item i where i.type = 0 and i.retailPrice.currency = :currency and i.technology = :technology")
	Double averageRetailPriceOfComponents(String currency, String technology);

	@Query("select stddev(i.retailPrice.amount) from Item i where i.type = 0 and i.retailPrice.currency = :currency and i.technology = :technology")
	Double deviationRetailPriceOfComponents(String currency, String technology);

	@Query("select min(i.retailPrice.amount) from Item i where i.type = 0 and i.retailPrice.currency = :currency and i.technology = :technology")
	Double minumumRetailPriceOfComponents(String currency, String technology);

	@Query("select max(i.retailPrice.amount) from Item i where i.type = 0 and i.retailPrice.currency = :currency and i.technology = :technology")
	Double maximumRetailPriceOfComponents(String currency, String technology);

	

	@Query("select count(i) from Item i where i.type = 1")
	int numberOfTools();

	@Query("select avg(i.retailPrice.amount) from Item i where i.type = 1 and i.retailPrice.currency = :currency ")
	double averageRetailPriceOfTools(String currency);

	@Query("select stddev(i.retailPrice.amount) from Item i where i.type = 1 and i.retailPrice.currency = :currency ")
	double deviationRetailPriceOfTools(String currency);

	@Query("select min(i.retailPrice.amount) from Item i where i.type = 1 and i.retailPrice.currency = :currency ")
	double minimumRetailPriceOfTools(String currency);

	@Query("select max(i.retailPrice.amount) from Item i where i.type = 1 and i.retailPrice.currency = :currency ")
	double maximumRetailPriceOfTools(String currency);



	@Query("select count(p) from Patronage p where p.status = :status ")
	int numberOfPatronages(Status status);

	@Query("select distinct p.budget.currency from Patronage p")
	Collection<String> currencies();
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = :status and p.budget.currency = :currency")
	Double averagePatronage(Status status,String currency);
	
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = :status and p.budget.currency = :currency ")
	Double deviationPatronage(Status status,String currency);
	
	@Query("select min(p.budget.amount) from Patronage p where p.status = :status and p.budget.currency = :currency ")
	Double minimumPatronage(Status status,String currency);
	
	@Query("select max(p.budget.amount) from Patronage p where p.status = :status and p.budget.currency = :currency ")
	Double maximumPatronage(Status status,String currency);

	
	@Query("select count(i) from Item i where i.chimpum != null")
	int numberOfChimpum();
	
	@Query("select avg(c.budget.amount) from Chimpum c where c.budget.currency = :currency")
	Double averageRetailPriceChimpum(String currency);
	
	@Query("select stddev(c.budget.amount) from Chimpum c where c.budget.currency = :currency")
	Double deviationRetailPriceChimpum(String currency);
	
	@Query("select min(c.budget.amount) from Chimpum c where c.budget.currency = :currency")
	Double minimumRetailPriceChimpum(String currency);
	
	@Query("select max(c.budget.amount) from Chimpum c where c.budget.currency = :currency")
	Double maximumRetailPriceChimpum(String currency);

}

