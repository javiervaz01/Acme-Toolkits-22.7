package acme.testing;

import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AnyChirpListTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int index, final Date moment, final String title, final String author, final String body, final String email) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Any", "Chirps list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(index, 0, title);
		super.checkColumnHasValue(index, 1, author);
		super.checkColumnHasValue(index, 2, body);
		super.checkColumnHasValue(index, 3, email);
		super.clickOnListingRecord(index);
		super.checkNotFormExists();
		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

} 