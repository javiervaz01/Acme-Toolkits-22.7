package acme.features.administrator.systemconfiguration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemconfigurations.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSystemConfigurationRepository extends AbstractRepository {

	@Query("select c from SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

}
