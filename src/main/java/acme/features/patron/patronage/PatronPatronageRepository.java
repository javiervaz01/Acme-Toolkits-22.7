package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronagereports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository{

	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(int id);
	
	@Query("select p from Patronage p where p.patron.id = :id")
	Collection<Patronage> findPatronagesByPatronId(int id);
	
	@Query("select pr from PatronageReport pr where pr.patronage.code = :code")
	Collection<PatronageReport> findPatronageReportsByPatronageCode(String code);
	
	@Query("select p from Patronage p where p.code = :code")
	Patronage findOnePatronageByCode(String code);
	
	@Query("select p from Patron p where p.id = :id")
	Patron findOnePatronById(int id);
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);
	
}
