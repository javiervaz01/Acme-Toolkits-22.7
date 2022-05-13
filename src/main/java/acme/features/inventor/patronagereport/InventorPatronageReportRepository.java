package acme.features.inventor.patronagereport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronagereports.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {

	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport findOnePatronageReportById(int id);
	
	@Query("select p from PatronageReport p where p.patronage.inventor.id = :id")
	Collection<PatronageReport> findPatronagesByInventorId(int id);
	
	@Query("select p from PatronageReport p where p.patronage.id = :masterId")
	Collection<PatronageReport> findPatronageReportsByMasterId(int masterId);
}
