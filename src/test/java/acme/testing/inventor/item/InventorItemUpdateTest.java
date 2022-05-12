package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemUpdateTest extends TestHarness{

	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int key, final String name, final String code, final String technology, final String description, final String retailPrice, final String info, final String type ) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my items");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(key, 0, name);
		super.checkColumnHasValue(key, 1, code);
		super.checkColumnHasValue(key, 2, technology);

		super.clickOnListingRecord(key);
		super.checkFormExists();
		
		super.fillInputBoxIn("description", description);
		
		super.clickOnSubmit("Update");
		
		super.checkInputBoxHasValue("description", description);
		
		
	}
	
	
	
	
	
	
	
}
