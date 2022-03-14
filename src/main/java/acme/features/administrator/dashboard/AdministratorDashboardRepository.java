
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	@Query("select count(i) from Item i where i.itemType = COMPONENT")
	Integer numberOfComponents();
	
	/*@Query("select avg(select i.retailPrice.amount from Item i where i.itemType = COMPONENT) from Item i group by i.technology")
	Double averageRetailPriceOfComponentsPerTechnology();
	@Query("select stddev(select i.retailPrice.amount from Item i where i.itemType = COMPONENT) from Item i group by i.technology")
	Double deviationRetailPriceOfComponentsPerTechnology();
	@Query("select min(select i.retailPrice.amount from Item i where i.itemType = COMPONENT) from Item i group by i.technology")
	Double minumumRetailPriceOfComponentsPerTechnology();
	@Query("select max(select i.retailPrice.amount from Item i where i.itemType = COMPONENT) from Item i group by i.technology")
	Double maximumRetailPriceOfComponentsPerTechnology();
	
	@Query("select avg(select i.retailPrice.amount from Item i where i.itemType = COMPONENT) from Item i group by i.retailPrice.currency")
	Double averageRetailPriceOfComponentsPerCurrency();
	@Query("select stddev(select i.retailPrice.amount from Item i where i.itemType = COMPONENT) from Item i group by i.retailPrice.currency")
	Double deviationRetailPriceOfComponentsPerCurrency();
	@Query("select min(select i.retailPrice.amount from Item i where i.itemType = COMPONENT) from Item i group by i.retailPrice.currency")
	Double minumumRetailPriceOfComponentsPerCurrency();
	@Query("select max(select i.retailPrice.amount from Item i where i.itemType = COMPONENT) from Item i group by i.retailPrice.currency")
	Double maximumRetailPriceOfComponentsPerCurrency();*/
	
	
	@Query("select count(i) from Item i where i.itemType = TOOL")
	Integer numberOfTools();
	
	/*@Query("select avg(select i.retailPrice from Item i where i.itemType = TOOL) from Item i group by i.retailPrice.currency")
	Double averageRetailPriceOfToolsPerCurrency();
	@Query("select stddev(select i.retailPrice from Item i where i.itemType = TOOL) from Item i group by i.retailPrice.currency")
	Double deviationRetailPriceOfToolsPerCurrency();
	@Query("select min(select i.retailPrice from Item i where i.itemType = TOOL) from Item i group by i.retailPrice.currency")
	Double minumumRetailPriceOfToolsPerCurrency();
	//select max(tc.retailPrice) from ToolComponent tc where tc.typeEntity = acme.entities.tool_component.Type.TOOL" + "group by (tc.retailPrice.currency)
	@Query("select max(select i.retailPrice from Item i where i.itemType = TOOL) from Item i group by i.retailPrice.currency")
	Double maximumRetailPriceOfToolsPerCurrency();*/
	
	
	
	@Query("select count(p) from Patronage p where p.status = PROPOSED")
	Integer numberOfProposedPatronages();
	@Query("select count(p) from Patronage p where p.status = ACCEPTED")
	Integer numberOfAcceptedPatronages();
	@Query("select count(p) from Patronage p where p.status = DENIED")
	Integer numberOfDeniedPatronages();
	
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = PROPOSED")
	Double averageBudgetOfProposedPatronages();
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = PROPOSED")
	Double deviationBudgetOfProposedPatronages();
	@Query("select min(p.budget.amount) from Patronage p where p.status = PROPOSED")
	Double minumumBudgetOfProposedPatronages();
	@Query("select max(p.budget.amount) from Patronage p where p.status = PROPOSED")
	Double maximumBudgetOfProposedPatronages();
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	Double averageBudgetOfAcceptedPatronages();
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	Double deviationBudgetOfAcceptedPatronages();
	@Query("select min(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	Double minumumBudgetOfAcceptedPatronages();
	@Query("select max(p.budget.amount) from Patronage p where p.status = ACCEPTED")
	Double maximumBudgetOfAcceptedPatronages();
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = DENIED")
	Double averageBudgetOfDeniedPatronages();
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = DENIED")
	Double deviationBudgetOfDeniedPatronages();
	@Query("select min(p.budget.amount) from Patronage p where p.status = DENIED")
	Double minumumBudgetOfDeniedPatronages();
	@Query("select max(p.budget.amount) from Patronage p where p.status = DENIED")
	Double maximumBudgetOfDeniedPatronages();
	

}

