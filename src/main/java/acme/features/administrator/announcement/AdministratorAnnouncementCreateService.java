package acme.features.administrator.announcement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamService;
import acme.entities.announcements.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement> {

	@Autowired
	AdministratorAnnouncementRepository repository;

	@Autowired
	SpamService spamService;

	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "body", "isCritical", "info");

	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title", "body", "isCritical", "info");

	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;

		Announcement result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Announcement();
		result.setMoment(moment);
		
		return result;
	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");

		errors.state(request, confirmation, "confirmation", "administrator.announcement.form.error.confirmation");

		if (!errors.hasErrors("title")) {
			String title;

			title = entity.getTitle();
			errors.state(request, !this.spamService.isSpam(title), "title",
					"administrator.announcement.form.error.spam");
		}
		if (!errors.hasErrors("body")) {
			String body;

			body = entity.getBody();
			errors.state(request, !this.spamService.isSpam(body), "body", "administrator.announcement.form.error.spam");
		}

	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		Boolean critical;

		critical = request.getModel().getBoolean("isCritical");

		
		entity.setCritical(critical);
		this.repository.save(entity);

	}

}
