package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.framework.datatypes.Money;

@Service
public class ExchangeService {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ExchangeRepository repository;

	public Money getExchange(final Money budget) {

		final String systemCurrency = this.repository.getSystemConfiguration().getCurrency();

		return budget.getCurrency().equals(systemCurrency) ? budget : this.getAdaptedCurrency(budget);
	}

	private Money getAdaptedCurrency(final Money budget) {
		final RestTemplate api;
		final ExchangeRate record;
		final Double rate;
		final String sourceCurrency = budget.getCurrency();
		final Double sourceAmount = budget.getAmount();
		final Double targetAmount;
		final String targetCurrency = this.repository.getSystemConfiguration().getCurrency();
		Money result;

		try {
			api = new RestTemplate();

			record = api.getForObject("https://api.exchangerate.host/latest?base={0}&symbols={1}", ExchangeRate.class,
					sourceCurrency, targetCurrency);

			assert record != null;

			rate = record.getRates().get(targetCurrency);
			targetAmount = rate * sourceAmount;

			result = new Money();
			result.setAmount(targetAmount);
			result.setCurrency(targetCurrency);

		} catch (final Throwable oops) {
			result = null;
		}

		return result;
	}

}
