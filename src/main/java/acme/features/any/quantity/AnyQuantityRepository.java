package acme.features.any.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

public interface AnyQuantityRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);

	@Query("select i from Item i")
	Collection<Item> findAllItems();

	@Query("select q from Quantity q where q.toolkit.id = :id")
	Collection<Quantity> findManyQuantityByToolkitId(int id);

	@Query("select distinct(q.item) from Quantity q where q.toolkit.id = :id")
	Collection<Item> findManyItemByToolkitId(int id);

	@Query("select i from Item i where i.code = :code")
	Item findOneItemByCode(String code);

	@Query("select q from Quantity q where q.id = :id")
	Quantity findOneQuantityById(int id);

}