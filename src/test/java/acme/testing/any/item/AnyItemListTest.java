package acme.testing.any.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyItemListTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/any/items/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int key, final String name, final String code, final String technology, final String description, final String retailPrice, final String info) {

		super.clickOnMenu("Any", "Items list");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(key, 0, name);
		super.checkColumnHasValue(key, 1, code);
		super.checkColumnHasValue(key, 2, technology);

		super.clickOnListingRecord(key);
		super.checkFormExists();
		// TODO check all the values in the show form. Add it to the CSV
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("info", info);
	}

	// Ancillary methods ------------------------------------------------------

}