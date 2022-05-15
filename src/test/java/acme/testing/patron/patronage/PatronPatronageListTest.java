package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageListTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources= "/patron/patronage/list.csv", encoding ="utf-8", numLinesToSkip=1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code,final String legalStuff, final String budget, final String creationDate, final String exchange,final String startDate, final String endDate, final String info, final String patronIdentityName, final String patronIdentitySurname, final String patronIdentityEmail, final String patronCompany, final String patronStatement, final String patronInfo) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron", "List my patronages");
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
		super.checkInputBoxHasValue("exchange", exchange);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("info", info);
		
		super.checkInputBoxHasValue("inventorName", patronIdentityName);
		super.checkInputBoxHasValue("inventorSurname", patronIdentitySurname);
		super.checkInputBoxHasValue("inventorEmail", patronIdentityEmail);
		super.checkInputBoxHasValue("inventorCompany", patronCompany);
		super.checkInputBoxHasValue("inventorStatement", patronStatement);
		super.checkInputBoxHasValue("inventorInfo", patronInfo);
		
		super.signOut();
	}
	
	
}
