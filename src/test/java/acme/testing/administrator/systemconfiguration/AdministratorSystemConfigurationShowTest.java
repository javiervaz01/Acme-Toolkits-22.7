package acme.testing.administrator.systemconfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String currency, final String acceptedCurrencies, final String strongSpamTerms,
			final String strongSpamThreshold, final String weakSpamTerms, final String weakSpamThreshold) {
		super.signIn("administrator", "administrator");

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

	@Test
	public void negativeTest() {
		// There aren't any negative tests for this feature because it doesn't involve
		// any forms.
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
