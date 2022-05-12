package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/Create.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int key, final String name, final String code, final String technology, final String description, final String retailPrice, final String info, final String type ) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my items");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("type", type);
		
		super.clickOnSubmit("Create");
		
		super.signOut();
	}
		
	
	
	
}
