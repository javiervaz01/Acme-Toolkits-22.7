package acme.features.inventor.patronagereport;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportCreationRepository extends AbstractRepository{

	@Query("select i from Patronage i where i.id = :id")
	Patronage findPatronageById(int id);
}
