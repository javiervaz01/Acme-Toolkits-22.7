package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageShowMineTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/show-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String creationDate, final String startDate, final String endDate, final String info, final String patronIdentityName, final String patronIdentitySurname, final String patronIdentityEmail, final String patronCompany, final String patronStatement, final String patronInfo) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List my patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("patronName", patronIdentityName);
		super.checkInputBoxHasValue("patronSurname", patronIdentitySurname);
		super.checkInputBoxHasValue("patronEmail", patronIdentityEmail);
		super.checkInputBoxHasValue("patronCompany", patronCompany);
		super.checkInputBoxHasValue("patronStatement", patronStatement);
		super.checkInputBoxHasValue("patronInfo", patronInfo);
		
		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}