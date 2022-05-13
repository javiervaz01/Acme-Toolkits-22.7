package acme.testing.authenticated.systemconfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedSystemConfigurationShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources =  "/authenticated/system-configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String currency, final String acceptedCurrencies) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Authenticated", "See system configuration");

		super.checkFormExists();
		super.checkInputBoxHasValue("currency", currency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		
		super.signOut();
	}

}
