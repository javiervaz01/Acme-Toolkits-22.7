package acme.features.patron.patronagereport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronagereports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository {

	@Query("select i from Patronage i where i.id = :id")
	Patronage findOnePatronageById(int id);
	
	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport findOnePatronageReportById(int id);

	@Query("select p from PatronageReport p where p.patronage.patron.id = :id")
	Collection<PatronageReport> findPatronagesByPatronId(int id);
	
	@Query("select p from PatronageReport p where p.patronage.id = :masterId")
	Collection<PatronageReport> findPatronageReportsByMasterId(int masterId);
	
	@Query("select count(pr) from PatronageReport pr where pr.patronage.id = :id")
	int countPatronageReportsInPatronageById(int id);
	
	@Query("select pr.patronage.patron.id from PatronageReport pr where pr.id = :id")
	int findPatronIdByPatronageReportId(int id);
}