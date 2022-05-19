package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemconfigurations.SystemConfiguration;
import acme.external.SpamDetector;

@Service
public class SpamService {

	@Autowired
	protected SpamRepository repository;

	public boolean isSpam(final String text) {
		SystemConfiguration config;
		boolean isStrongSpam;
		boolean isWeakSpam;

		config = this.repository.getSystemConfiguration();
		isStrongSpam = SpamDetector.isSpam(text, config.getStrongSpamTerms(), config.getStrongSpamThreshold());
		isWeakSpam = SpamDetector.isSpam(text, config.getWeakSpamTerms(), config.getWeakSpamThreshold());

		return isWeakSpam || isStrongSpam;
	}
}
