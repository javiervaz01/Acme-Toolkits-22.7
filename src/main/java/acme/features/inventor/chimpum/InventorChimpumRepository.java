package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.chimpums.Chimpum;
import acme.entities.items.Item;
import acme.framework.repositories.AbstractRepository;

public interface InventorChimpumRepository extends AbstractRepository{

	@Query("select c from Chimpum c where c.inventor.id = :id")
	Collection<Chimpum> findChimpumsByInventorId(int id);
	
	@Query("select c from Chimpum c where c.id = :id")
	Chimpum findOne(int id);
	
	@Query("select i from Item i where i.chimpum.id = :id")
	Item findItemByChimpum(int id);
	
}
