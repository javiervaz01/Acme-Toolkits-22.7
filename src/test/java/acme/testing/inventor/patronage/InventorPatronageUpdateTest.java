package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String status, final String code, final String legalStuff,
			final String budget, final String creationDate, final String startDate, final String endDate,
			final String info) {
		super.signIn("inventor1", "inventor1");

		// TODO this update class doesn't exist anymore. We have accept/deny services
		// Instead. However, make sure to ask the test-related questions we had in the
		// next follow up

		super.clickOnMenu("Inventor", "List my proposed patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, "PROPOSED");
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);

		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();

		super.clickOnButton("Update");

		super.fillInputBoxIn("status", status);

		// This will take us to the list. We have to modify this
		// Check in the list that the value has been modified
		super.clickOnSubmit("Update");

		super.checkFormExists();

		super.checkInputBoxHasValue("status", status);

		super.signOut();
	}

}
