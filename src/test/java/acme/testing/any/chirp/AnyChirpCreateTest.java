package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpCreateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String title, final String author,
			final String body, final String email) {
		
		super.clickOnMenu("Any","List chirps");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("email", email);
		
		super.fillInputBoxIn("confirmation", "true");
		
		super.clickOnSubmit("Create");
		
		super.checkListingExists();
		super.sortListing(2, "desc");
		
		super.checkColumnHasValue(0, 0, title);
		super.checkColumnHasValue(0, 1, author);
		
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("author", author);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("email", email);
		
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final String title, final String author,
			final String body, final String email) {
		
		super.clickOnMenu("Any","List chirps");
		super.clickOnButton("Create");
		
		super.checkFormExists();
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("email", email);
		
		super.fillInputBoxIn("confirmation", "true");
		
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
		
	}
	
	@Test
	public void hackingTest() {
		//As this is a list that any user of Acme Toolkits can access,
		//there are not hacking tests for this feature.
	}

}
