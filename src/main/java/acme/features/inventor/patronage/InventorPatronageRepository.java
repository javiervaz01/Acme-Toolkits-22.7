package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronagereports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(int id);

	@Query("select p from Patronage p where p.inventor.id = :id and p.draftMode = false")
	Collection<Patronage> findPatronagesByInventorId(int id);

	@Query("select p from Patronage p where p.inventor.id = :id and p.status = 0 and p.draftMode = false")
	Collection<Patronage> findProposedPatronagesByInventorId(int id);

	@Query("select pr from PatronageReport pr where pr.patronage.code = :code")
	Collection<PatronageReport> findPatronageReportsByPatronageCode(String code);

}
