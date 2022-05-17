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

	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport findOnePatronageReportById(int id);

	@Query("select p from PatronageReport p where p.patronage.inventor.id = :id")
	Collection<PatronageReport> findPatronagesByInventorId(int id);

	@Query("select p from PatronageReport p where p.patronage.id = :masterId")
	Collection<PatronageReport> findPatronageReportsByMasterId(int masterId);

	@Query("select pr.sequenceNumber from PatronageReport pr order by pr.id desc")
	Collection<String> findLastPatronageReportSequenceNumber();

	@Query("select count(pr) from PatronageReport pt where pt.patronage.id = :id")
	int countPatronageReportsInPatronageById(int id);
}
