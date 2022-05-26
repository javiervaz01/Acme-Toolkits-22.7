package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageListProposedTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list-proposed.csv", encoding = "utf-8", numLinesToSkip = 1)
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

		super.signOut();
	}
	
	@Test
	public void negativeTest(){
		//As this is a test that does not involve any form, we do not have negative cases to test,
		//so this function will be blank.
	}
	
	@Test
	public void hackingTest() {
		super.navigate("/inventor/patronage/list-proposed");
		super.checkPanicExists();
	}

}
