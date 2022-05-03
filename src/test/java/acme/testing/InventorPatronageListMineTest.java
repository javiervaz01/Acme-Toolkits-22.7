package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class InventorPatronageListMineTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
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
		super.checkInputBoxHasValue("patron.identity.name", patronIdentityName);
		super.checkInputBoxHasValue("patron.identity.surname", patronIdentitySurname);
		super.checkInputBoxHasValue("patron.identity.email", patronIdentityEmail);
		super.checkInputBoxHasValue("patron.company", patronCompany);
		super.checkInputBoxHasValue("patron.company", patronCompany);
		super.checkInputBoxHasValue("patron.statement", patronStatement);
		super.checkInputBoxHasValue("patron.info", patronInfo);
		
		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}