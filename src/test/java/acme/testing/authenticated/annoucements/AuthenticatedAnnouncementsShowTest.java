package acme.testing.authenticated.annoucements;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementsShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final Date moment, final String title, final String body,
			final String isCritical, final String info) {

		final String time = this.formatDate(moment);

		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Authenticated", "List recent announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("moment", time);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("isCritical", isCritical);
		super.checkInputBoxHasValue("info", info);

		super.signOut();
	}
	@Test
	@Order(20)
	public void negativeTest() {
		// As this is a test that does not involve any form, we do not have negative
		// cases to test, so this function will be blank.
	}

	@Test
	@Order(30)
	public void hackingTest() {
		// As the framework doesn´t support this hacking feature we will have to perform this manually
		// 1) Start by initiating the Acme toolkits project
		// 2) Log in as an administrator
		// 3) Go to the administrator menu and click "Create announcement"
		// 4) Fill the creation form and create the announcement
		// 5) Go to the authenticated menu and select "List recent announcements"
		// 6) Click on your new announcement and copy the id from the link of the page "/authenticated/announcement/show?id=XXX" (XXX should be the id of the announcement)
		// 7) Log out
		// 8) Navigate to this URL /authenticated/announcement/show?id=XXX (XXX should be the id of the announcement)
		// 9) Check that a panic happens
	}

	// Ancillary methods ------------------------------------------------------

	private String formatDate(final Date moment) {
		final SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		final String date = formater.format(moment);
		return date;
	}
}
