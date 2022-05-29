package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.chimpums.Chimpum;
import acme.entities.items.Item;
import acme.framework.repositories.AbstractRepository;

public interface InventorChimpumRepository extends AbstractRepository {

	@Query("select i.chimpum from Item i where i.inventor.id = :id")
	Collection<Chimpum> findChimpumsByInventorId(int id);

	@Query("select c from Chimpum c where c.id = :id")
	Chimpum findOne(int id);

	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);

	@Query("select i.chimpum from Item i where i.id = :id")
	Chimpum findOneChimpumByItemId(int id);

	@Query("select i from Item i where i.chimpum.id = :id")
	Item findOneItemByChimpumId(int id);

	@Query("select count(sc) > 0 from SystemConfiguration sc where sc.acceptedCurrencies LIKE %:currency%")
	boolean isAcceptedCurrency(String currency);
}
