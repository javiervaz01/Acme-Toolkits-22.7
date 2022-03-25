package acme.features.authenticated.announcement;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import acme.entities.announcements.Announcement;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedAnnouncementRepository extends AbstractRepository {
	@Query("select a from Announcement a where a.id = :id")
	Announcement findOneAnnouncementById(int id);
	
	@Query("select a from Announcement a where a.moment > :deadline")
	Collection<Announcement> findRecentAnnouncements(Date deadline);
}
