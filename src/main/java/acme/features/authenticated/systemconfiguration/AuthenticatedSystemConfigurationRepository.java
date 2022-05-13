package acme.features.authenticated.systemconfiguration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemconfigurations.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedSystemConfigurationRepository extends AbstractRepository{

	@Query("select c from SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();
	
	
}
