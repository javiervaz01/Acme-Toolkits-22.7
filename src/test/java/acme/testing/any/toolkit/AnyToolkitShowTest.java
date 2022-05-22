package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources =  "/any/toolkits/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int key, final String code, final String title, final String description,
		final String assemblyNotes, final String link, final String retailPrice) {

		super.clickOnMenu("Any", "List toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(key);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("retailPrice", retailPrice);

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

}
