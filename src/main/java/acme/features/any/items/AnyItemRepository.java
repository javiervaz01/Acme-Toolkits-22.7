package acme.features.any.items;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnyItemRepository extends AbstractRepository {

	@Query("select distinct(q.item) from Quantity q where q.toolkit.draftMode = false")
	Collection<Item> findItems();

	@Query("select i from Item i where i.id = :id")
	Item findItemById(int id);

	@Query("select q.toolkit from Quantity q where q.item.id = :id")
	Toolkit findOneToolkitByItemId(int id);

}
