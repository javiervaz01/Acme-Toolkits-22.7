package acme.testing.authenticated.annoucements;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final Date moment, final String title, final String body, final String isCritical, final String info) {
		
		final String time = this.formatDate(moment);
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Authenticated", "List recent announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, isCritical);
		super.checkColumnHasValue(recordIndex, 2, time);
		
		super.signOut();
	}
	
	public void negativeTest(){
		//As this is a test that does not involve any form, we do not have negative cases to test,
		//so this function will be blank.
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		super.navigate("/authenticated/announcement/list-recent");
		super.checkPanicExists();
	}
	
	@Test
	private String formatDate(final Date moment) {
		final SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		final String date=formater.format(moment);
		return date;
	}
	
	
}
