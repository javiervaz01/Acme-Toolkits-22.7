package acme.testing.any.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyItemListTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/any/items/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int key, final String name, final String code, final String technology,final String description, final String retailPrice, final String info,final String type) {

		super.clickOnMenu("Any", "List items");
		super.checkListingExists();
		super.sortListing(1, "asc");


		super.checkColumnHasValue(key, 0, name);
		super.checkColumnHasValue(key, 1, code);
		super.checkColumnHasValue(key, 2, technology);
		super.checkColumnHasValue(key, 3, retailPrice);

		
		
	}
	
	@Test
	public void negativeTest(){
		//As this is a test that does not involve any form, we do not have negative cases to test,
		//so this function will be blank.
	}
	
	@Test
	public void hackingTest() {
		//As this is a list that any user of Acme Toolkits can access,
		//there are not hacking tests for this feature.
	}

	// Ancillary methods ------------------------------------------------------

}