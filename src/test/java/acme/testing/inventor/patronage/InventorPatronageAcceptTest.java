package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageAcceptTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/accept.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String status, final String code, final String legalStuff,
			final String budget, final String creationDate, final String startDate, final String endDate,
			final String info) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List my proposed patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, "PROPOSED");
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);

		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();

		super.clickOnSubmit("Accept");
		
		super.clickOnMenu("Inventor", "List my patronages");
		
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(1, 0, status);
		super.checkColumnHasValue(1, 1, code);
		super.checkColumnHasValue(1, 2, budget);
		
		super.clickOnListingRecord(1);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("status", "ACCEPTED");
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("info", info);

		super.signOut();
	}
	
	@Test
	public void negativeTest(){
		//As this is a test that does not involve any form, we do not have negative cases to test,
		//so this function will be blank.
	}
	
	@Test
	public void hackingTest() {
		//As the framework doesn´t support this hacking feature we will have to perform this manually
		//-Start by initiating the Acme toolkits project
		//-Navigate to this URL /inventor/patronage/show?id=338
		//-Check that a panic happens
	}

}
