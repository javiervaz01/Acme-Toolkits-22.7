package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

public interface InventorToolkitRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select distinct(q.item.retailPrice.currency) from Quantity q")
	Collection<String> currencies();
	
	@Query("select distinct(t) from Toolkit t where t.inventor.id = :id")
	Collection<Toolkit> findToolkitsByInventorId(int id);
	
	@Query("select sum(q.number*q.item.retailPrice.amount) from Quantity q where q.toolkit.id = :id and q.item.retailPrice.currency = :currency")
	Double findRetailPriceByToolkitId(int id, String currency);
		
	@Query("select distinct(q.item) from Quantity q where q.toolkit.id = :id")
	Collection<Item> findItemsByToolkitId(int id);
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);
	
	@Query("select t from Toolkit t where t.code = :code")
	Toolkit findOneToolkitByCode(String code);
}
