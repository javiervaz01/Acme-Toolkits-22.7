package acme.components;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemconfigurations.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ExchangeRepository extends AbstractRepository {

	@Query("select c from SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

	@Query("select ec.rate from ExchangeCache ec where (ec.date > :limit) and (ec.sourceCurrency = :sourceCurrency) and (ec.targetCurrency = :targetCurrency)")
	Double getRecentExchangeRate(Date limit, String sourceCurrency, String targetCurrency);

	@Query("select ec from ExchangeCache ec where (ec.sourceCurrency = :sourceCurrency) and (ec.targetCurrency = :targetCurrency)")
	ExchangeCache findOneExchangeRate(String sourceCurrency, String targetCurrency);
}
