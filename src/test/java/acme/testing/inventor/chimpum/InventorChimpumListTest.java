package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumListTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int key, final String code, final String creationDate, final String title, final String startDate, final String endDate,final String budget) {

		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List the chimpums of my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(key, 0, code);
		super.checkColumnHasValue(key, 1, creationDate);
		super.checkColumnHasValue(key, 2, title);
		super.checkColumnHasValue(key, 3, startDate);
		super.checkColumnHasValue(key, 4, endDate);
		super.checkColumnHasValue(key, 5, budget);

		
		super.signOut();

			
	}
	
	@Test
	public void negativeTest(){
		//As this is a test that does not involve any form, we do not have negative cases to test,
		//so this function will be blank.
	}
	
	@Test
	public void hackingTest() {
		super.checkNotLinkExists("Inventor");
		super.navigate("/inventor/chimpum/list");
		super.checkPanicExists();

	}

}