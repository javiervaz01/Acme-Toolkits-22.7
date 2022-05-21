package acme.features.inventor.patronagereport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronagereports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {

	@Query("select i from Patronage i where i.id = :id")
	Patronage findOnePatronageById(int id);

	@Query("select pr from PatronageReport pr where pr.id = :id")
	PatronageReport findOnePatronageReportById(int id);

	@Query("select pr from PatronageReport pr where pr.patronage.inventor.id = :id")
	Collection<PatronageReport> findPatronagesByInventorId(int id);

	@Query("select pr from PatronageReport pr where pr.patronage.id = :masterId")
	Collection<PatronageReport> findPatronageReportsByMasterId(int masterId);

	@Query("select count(pr) from PatronageReport pr where pr.patronage.id = :id")
	int countPatronageReportsInPatronageById(int id);
	
	@Query("select pr.patronage.inventor.id from PatronageReport pr where pr.id = :id")
	int findInventorIdByPatronageReportId(int id);
}
