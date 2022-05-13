package acme.features.patron.inventor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface PatronInventorRepository extends AbstractRepository {
	
	@Query("select i from Inventor i")
	Collection<Inventor> findAllInventors();
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);
	
}
