package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumDeleteTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String code,final String creationDate,final String title, final String description,
							final String startDate, final String endDate,final String budget,final String info,final String itemName,
							final String codeNew,final String creationDateNew,final String titleNew,final String startDateNew,
							final String endDateNew,final String budgetNew) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List the chimpums of my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("itemName", itemName);
		
		super.clickOnSubmit("Delete");
		
		
		super.clickOnMenu("Inventor", "List the chimpums of my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(0,0, codeNew);
		super.checkColumnHasValue(0,1, creationDateNew);
		super.checkColumnHasValue(0,2, titleNew);
		super.checkColumnHasValue(0,3, startDateNew);
		super.checkColumnHasValue(0,4, endDateNew);
		super.checkColumnHasValue(0,5, budgetNew);
		
		super.signOut();
		
		
	}
	
	@Test
	public void negativeTest() {
		// As the framework doesn´t support this negative feature we will have to perform this manually
		// Delete a chimpum for an item as a principal without the "Inventor" role;
		// Check that a panic happens
	}
	
	@Test
	public void hackingTest() {
		// As the framework doesn´t support this hacking feature we will have to perform this manually
		// Delete a chimpum for an item as a principal without the "Inventor" role;
		// Check that a panic happens
	}

}
