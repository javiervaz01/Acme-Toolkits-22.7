package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpShowTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int index, final String moment, final String title, final String author,
			final String body, final String email) {

		super.clickOnMenu("Any", "List chirps");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(index, 0, title);
		super.checkColumnHasValue(index, 1, author);
		super.checkColumnHasValue(index, 2, moment);
		
		super.clickOnListingRecord(index);
		
		super.checkFormExists();
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("author", author);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("email", email);
		super.checkInputBoxHasValue("moment", moment);
		

		
	}
	
	@Test
	public void negativeTest(){
		//As this is a test that does not involve any form, we do not have negative cases to test,
		//so this function will be blank.
	}
	
	@Test
	public void hackingTest() {
		//As this is a list that any user of Acme Toolkits can access,
		//there are not hacking tests for this feature.
	}

	// Ancillary methods ------------------------------------------------------

}