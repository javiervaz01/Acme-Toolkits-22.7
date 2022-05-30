package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumShowTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int key, final String code, final String creationDate, final String title, final String description, final String startDate, final String endDate,final String budget,final String info,final String itemName) {

		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List the chimpums of my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(key);

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("itemName", itemName);

		
		super.signOut();

			
	}
	@Test
	public void negativeTest(){
		//As this is a test that does not involve any form, we do not have negative cases to test,
		//so this function will be blank.
	}
	
	@Test
	public void hackingTest() {
		// As the framework doesn´t support this hacking feature we will have to perform this manually
		// 1) Start by initiating the Acme toolkits project
		// 2) Navigate to this URL /inventor/chimpum/show?id=80
		// 3) Check that a panic happens
	}

}