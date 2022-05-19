package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageDenyTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/deny.csv", encoding = "utf-8", numLinesToSkip = 1)
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

		super.clickOnSubmit("Deny");
		
		super.clickOnMenu("Inventor", "List my patronages");
		
		super.sortListing(0, "desc");
		
		super.checkColumnHasValue(2, 0, status);
		super.checkColumnHasValue(2, 1, code);
		super.checkColumnHasValue(2, 2, budget);
		
		super.clickOnListingRecord(2);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("status", "DENIED");
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("info", info);

		super.signOut();
	}

}
