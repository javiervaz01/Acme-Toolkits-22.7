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
	public void positiveTest(final int recordIndex, final Date moment, final String title, final String body, final String isCritical, final String info) {
		
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
	
	public void negativeTest(){
		//As this is a test that does not involve any form, we do not have negative cases to test,
		//so this function will be blank.
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		//As the framework doesn´t support this hacking feature we will have to perform this manually
		//-Start by initiating the Acme toolkits project
		//-Navigate to this URL /authenticated/announcement/show?id=373
		//-Check that a panic happens
	}
	
	
	@Test
	private String formatDate(final Date moment) {
		final SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		final String date=formater.format(moment);
		return date;
	}
}
