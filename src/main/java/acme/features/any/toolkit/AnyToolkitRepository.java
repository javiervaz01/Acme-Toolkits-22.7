package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnyToolkitRepository extends AbstractRepository{

	@Query("select t from Toolkit t")
	Collection<Toolkit> findToolkits();
	
	@Query("SELECT t, q FROM Toolkit t LEFT JOIN Quantity q ON t.id = q.toolkit.id")
	Collection<Toolkit> findToolkitsIncludingItemsInfo();
	/*@Query("select (t, q) from Toolkit t where t.id = q.toolkit.id from Quantity q")
	Collection<Toolkit> findToolkitsIncludingItemsInfo();*/

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);
	
	
	
	

	@Query("select distinct(q.item.retailPrice.currency) from Quantity q")
	Collection<String> currencies();
	

	@Query("select sum(q.number*q.item.retailPrice.amount) from Quantity q where q.toolkit.id = :id and q.item.retailPrice.currency = :currency")
	Double findRetailPriceByToolkitId(int id, String currency);

	@Query("select distinct(q.item) from Quantity q where q.toolkit.id = :id")
	Collection<Item> findItemsByToolkitId(int id);
	
	
	
	
	@Query("select sum(q.number*q.item.retailPrice.amount) from Quantity q where q.toolkit.id = :id")
	Double findRetailPriceByToolkitId(int id);
	
	@Query("select q.item.retailPrice.currency from Quantity q where q.toolkit.id = :id")
	String findCurrencyByToolkitId(int id);
	
}