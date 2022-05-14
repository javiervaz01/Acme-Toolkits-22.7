package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnyToolkitRepository extends AbstractRepository{

	@Query("select t from Toolkit t where t.draftMode = false")
	Collection<Toolkit> findAllToolkits();

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);

	@Query("select distinct(q.item) from Quantity q where q.toolkit.id = :id")
	Collection<Item> findItemsByToolkitId(int id);
	
	@Query("select sum(q.number*q.item.retailPrice.amount) from Quantity q where q.toolkit.id = :id")
	Double findRetailPriceAmountByToolkitId(int id);
	
	@Query("select distinct(q.item.retailPrice.currency) from Quantity q where q.toolkit.id = :id")
	String findRetailPriceCurrencyByToolkitId(int id);
}