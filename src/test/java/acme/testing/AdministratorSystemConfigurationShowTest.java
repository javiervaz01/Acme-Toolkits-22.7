package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AdministratorSystemConfigurationShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources =  "/administrator/systemConfiguration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
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
	
	

}
