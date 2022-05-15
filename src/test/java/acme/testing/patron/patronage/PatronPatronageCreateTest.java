package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageCreateTest extends TestHarness {
@ParameterizedTest
@CsvFileSource(resources = "/patron/patronage/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
@Order(10)
public void positiveTest(final int inventorRecordIndex, final String status, final String code, final String legalStuff, final String budget, final String exchange, final String startDate, final String endDate, final String info) {
	super.signIn("patron1", "patron1");

	super.clickOnMenu("Patron", "Propose a patronage");
	super.checkListingExists();
	super.clickOnListingRecord(inventorRecordIndex);
	super.clickOnButton("Propose a Patronage");	
		
	super.fillInputBoxIn("code", code);
	super.fillInputBoxIn("legalStuff", legalStuff);
	super.fillInputBoxIn("budget", budget);
	super.fillInputBoxIn("exchange", exchange);
	super.fillInputBoxIn("startDate", startDate);
	super.fillInputBoxIn("endDate", endDate);
	super.fillInputBoxIn("info", info);
	super.clickOnSubmit("Create");

	super.clickOnMenu("Patron", "List my patronages");
	super.checkListingExists();
	super.sortListing(0, "asc");
	//super.checkColumnHasValue(recordIndex, 0, reference);
	//super.checkColumnHasValue(recordIndex, 1, deadline);
	//super.checkColumnHasValue(recordIndex, 2, title);

	//super.checkFormExists();
	//super.checkInputBoxHasValue("reference", reference);
	//super.checkInputBoxHasValue("title", title);
	//super.checkInputBoxHasValue("deadline", deadline);
	//super.checkInputBoxHasValue("salary", salary);
	//super.checkInputBoxHasValue("score", score);
	//super.checkInputBoxHasValue("moreInfo", moreInfo);
	//super.checkInputBoxHasValue("description", description);

	//super.clickOnButton("Duties");

	//super.checkListingExists();
	//super.checkListingEmpty();

	//super.signOut();
}

}
