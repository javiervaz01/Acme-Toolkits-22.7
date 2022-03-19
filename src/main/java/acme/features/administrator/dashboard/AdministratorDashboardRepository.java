
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	@Query("select count(i) from Item i where i.type = COMPONENT")
	int numberOfComponents();
	
	@Query("select avg(i.retailPrice.amount) from Item i where i.type = COMPONENT group by i.technology")
	double averageRetailPriceOfComponentsPerTechnology();
	@Query("select stddev(i.retailPrice.amount) from Item i where i.type = COMPONENT group by i.technology")
	double deviationRetailPriceOfComponentsPerTechnology();
	@Query("select min(i.retailPrice.amount) from Item i where i.type = COMPONENT group by i.technology")
	double minumumRetailPriceOfComponentsPerTechnology();
	@Query("select max(i.retailPrice.amount) from Item i where i.type = COMPONENT group by i.technology")
	double maximumRetailPriceOfComponentsPerTechnology();
	
	@Query("select avg(i.retailPrice.amount) from Item i where i.type = COMPONENT group by i.retailPrice.currency")
	double averageRetailPriceOfComponentsPerCurrency();
	@Query("select stddev(i.retailPrice.amount) from Item i where i.type = COMPONENT group by i.retailPrice.currency")
	double deviationRetailPriceOfComponentsPerCurrency();
	@Query("select min(i.retailPrice.amount) from Item i where i.type = COMPONENT group by i.retailPrice.currency")
	double minumumRetailPriceOfComponentsPerCurrency();
	@Query("select max(i.retailPrice.amount) from Item i where i.type = COMPONENT group by i.retailPrice.currency")
	double maximumRetailPriceOfComponentsPerCurrency();
	
	
	@Query("select count(i) from Item i where i.type = TOOL")
	int numberOfTools();
	
	@Query("select avg(i.retailPrice.amount) from Item i where i.type = TOOL group by i.retailPrice.currency")
	double averageRetailPriceOfToolsPerCurrency();
	@Query("select stddev(i.retailPrice.amount) from Item i where i.type = TOOL group by i.retailPrice.currency")
	double deviationRetailPriceOfToolsPerCurrency();
	@Query("select min(i.retailPrice.amount) from Item i where i.type = TOOL group by i.retailPrice.currency")
	double minumumRetailPriceOfToolsPerCurrency();
	@Query("select max(i.retailPrice.amount) from Item i where i.type = TOOL group by i.retailPrice.currency")
	double maximumRetailPriceOfToolsPerCurrency();
	
	
	
	@Query("select count(p) from Patronage p where p.status = PROPOSED")
	int numberOfProposedPatronages();
	@Query("select count(p) from Patronage p where p.status = ACCEPTED")
	int numberOfAcceptedPatronages();
	@Query("select count(p) from Patronage p where p.status = DENIED")
	int numberOfDeniedPatronages();
	
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = PROPOSED")
	double averageBudgetOfProposedPatronages();
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = PROPOSED")
	double deviationBudgetOfProposedPatronages();
	@Query("select min(p.budget.amount) from Patronage p where p.status = PROPOSED")
	double minumumBudgetOfProposedPatronages();
	@Query("select max(p.budget.amount) from Patronage p where p.status = PROPOSED")
	double maximumBudgetOfProposedPatronages();
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	double averageBudgetOfAcceptedPatronages();
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	double deviationBudgetOfAcceptedPatronages();
	@Query("select min(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	double minumumBudgetOfAcceptedPatronages();
	@Query("select max(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	double maximumBudgetOfAcceptedPatronages();
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = DENIED")
	double averageBudgetOfDeniedPatronages();
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = DENIED")
	double deviationBudgetOfDeniedPatronages();
	@Query("select min(p.budget.amount) from Patronage p where p.status = DENIED")
	double minumumBudgetOfDeniedPatronages();
	@Query("select max(p.budget.amount) from Patronage p where p.status = DENIED")
	double maximumBudgetOfDeniedPatronages();
	

}

