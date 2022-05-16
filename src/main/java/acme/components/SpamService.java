package acme.components;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemconfigurations.SystemConfiguration;

@Service
public class SpamService {

	@Autowired
	protected SpamRepository repository;

	public boolean isSpam(final String text) {
		final SystemConfiguration config = this.repository.getSystemConfiguration();
		final boolean isStrongSpam = this.isSpam(text, config.getStrongSpamTerms(), config.getStrongSpamThreshold());
		final boolean isWeakSpam = this.isSpam(text, config.getWeakSpamTerms(), config.getWeakSpamThreshold());

		return isWeakSpam || isStrongSpam;
	}

	private boolean isSpam(final String text, final String spamTerms, final double spamThreshold) {
		final String[] splitSpamTerms = spamTerms.toLowerCase().split(",");
		final Set<String> uniqueSpamTerms = Arrays.stream(splitSpamTerms).map(String::trim).collect(Collectors.toSet());
		int foundSpamTermsCount = 0;
		int totalTermsCount = text.split("[ \\r\\n]+").length;

		for (final String term : uniqueSpamTerms) {
			final Pattern spamFilterPattern = this.generateSpamRegexPattern(term);
			final Matcher matcher = spamFilterPattern.matcher(text);
			// Counting the occurences must be done this way in JDK 8 and older versions
			while (matcher.find()) {
				foundSpamTermsCount++;

				// Multi-word spam terms should be considered a single term towards the global
				// count to give a realistic spam value. The -1 is to consider it as a single
				// term for each occurence of the multi-word spam term. ("you've won six" should
				// be 50% spam, not 33% as would happen if we didn't consider this)
				totalTermsCount -= (term.split(" +").length - 1);
			}
		}

		return (double) foundSpamTermsCount / totalTermsCount > spamThreshold;
	}

	private Pattern generateSpamRegexPattern(final String term) {
		final String[] splitTerm = term.split(" ");
		// This method creates a regex that matches the spam term in all the
		// requested manners
		final StringBuilder buffer = new StringBuilder();
		buffer.append("(");
		buffer.append(Pattern.quote(splitTerm[0]));
		// Consider the possible white spaces or blank lines between words of the same
		// term (e.g., "one million").
		Arrays.stream(splitTerm).skip(1).forEach(t -> buffer.append(String.format("[ \\r\\n]+%s", Pattern.quote(t))));
		// Don't match "one millionAIRE" for the spam term "one million"
		buffer.append(")(?!\\w)");

		// Example result: "(one[ \\n]+million[ \\r\\n]+)(?!\\w)/gi"
		return Pattern.compile(buffer.toString(), Pattern.CASE_INSENSITIVE);
	}
}
