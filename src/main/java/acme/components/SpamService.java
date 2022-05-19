package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemconfigurations.SystemConfiguration;

@Service
public class SpamService {

	@Autowired
	protected SpamRepository repository;

	public boolean isSpam(final String text) {
		final SystemConfiguration config = this.repository.getSystemConfiguration();
		final boolean isStrongSpam = SpamServiceExternal.isSpam(text, config.getStrongSpamTerms(),
				config.getStrongSpamThreshold());
		final boolean isWeakSpam = SpamServiceExternal.isSpam(text, config.getWeakSpamTerms(),
				config.getWeakSpamThreshold());

		return isWeakSpam || isStrongSpam;
	}

}
