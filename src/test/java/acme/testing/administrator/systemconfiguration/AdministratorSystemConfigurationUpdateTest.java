package acme.testing.administrator.systemconfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationUpdateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final String currency, final String acceptedCurrencies, final String strongSpamTerms, final String strongSpamThreshold, final String weakSpamTerms, final String weakSpamThreshold) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "See system configuration");

		super.checkFormExists();
		super.fillInputBoxIn("currency", currency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update");

		super.clickOnMenu("Administrator", "See system configuration");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("currency", currency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negative(final String currency, final String acceptedCurrencies, final String strongSpamTerms, final String strongSpamThreshold, final String weakSpamTerms, final String weakSpamThreshold) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "See system configuration");

		super.checkFormExists();
		super.fillInputBoxIn("currency", currency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Administrator");
		super.navigate("/administrator/system-configuration/show");
		super.checkPanicExists();
		
		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/system-configuration/show");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("patron1", "patron1");
		super.navigate("/administrator/system-configuration/show");
		super.checkPanicExists();
		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
