package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

public interface InventorItemRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t where t.id = :masterId")
	Toolkit findOneToolkitById(int masterId);
	
	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);

	@Query("select i from Item i where i.inventor.id = :id")
	Collection<Item> findItemsByInventorId(int id);

	@Query("select distinct(q.item) from Quantity q where q.toolkit.id = :masterId")
	Collection<Item> findItemsByToolkitId(int masterId);
	
	@Query("select distinct(q.toolkit) from Quantity q where q.item.inventor.id = :id")
	Collection<Toolkit> findToolkitsByInventorId(int id);
}
