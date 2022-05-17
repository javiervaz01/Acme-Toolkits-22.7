package acme.components;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SpamServiceExternal {
	// This code is ready to be externalized to a .jar
	public static boolean isSpam(final String text, final String spamTerms, final double spamThreshold) {
		final String[] splitSpamTerms = spamTerms.toLowerCase().split(",");
		final Set<String> uniqueSpamTerms = Arrays.stream(splitSpamTerms).map(String::trim).collect(Collectors.toSet());
		int foundSpamTermsCount = 0;
		int totalTermsCount = SpamServiceExternal.getWordCount(text);

		for (final String term : uniqueSpamTerms) {
			final Pattern spamFilterPattern = SpamServiceExternal.generateSpamRegexPattern(term);
			final Matcher matcher = spamFilterPattern.matcher(text);
			// Counting the occurences must be done this way in JDK 8 and older versions
			while (matcher.find()) {
				foundSpamTermsCount++;

				// The line below considers terms as "you have won" as a single word towards the
				// final count
				totalTermsCount -= (term.split(" +").length - 1);
			}
		}
		return (double) foundSpamTermsCount / totalTermsCount > spamThreshold;
	}

	private static Pattern generateSpamRegexPattern(final String term) {
		final String[] splitTerm = term.split(" ");
		// This method creates a regex that matches the spam term in all the
		// requested manners
		final StringBuilder buffer = new StringBuilder();
		buffer.append("(");
		buffer.append(Pattern.quote(splitTerm[0]));
		// Consider the possible white spaces or blank lines between words of the same
		// term (e.g., "one million").
		Arrays.stream(splitTerm).skip(1).forEach(t -> buffer.append(String.format("[ \\s]+%s", Pattern.quote(t))));
		// Don't match "one millionAIRE" for the spam term "one million"
		buffer.append(")(?!\\w)");

		// Example result: "(one[ \\s]+million[ \\s]+)(?!\\w)/gi"
		return Pattern.compile(buffer.toString(), Pattern.CASE_INSENSITIVE);
	}

	private static int getWordCount(final String text) {
		final Pattern pattern = Pattern.compile("[\\w]+");
		final Matcher matcher = pattern.matcher(text);
		int count = 0;
		while (matcher.find())
			count++;
		return count != 0 ? count : 1; // Avoid division by zero
	}
}
