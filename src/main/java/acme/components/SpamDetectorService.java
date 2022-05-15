package acme.components;

import org.springframework.stereotype.Service;

@Service
public class SpamDetectorService {
	
	public boolean surpassesStrongTermsRatio(final String text, final double threshold, final String spamTerms) {
		int counter = 0;
		final String normalizedText = text.trim().replaceAll("\\s{2,}", " ").toLowerCase();
		final int numberOfWords = normalizedText.split(" ").length;
		//final double threshold = this.repository.getSystemConfiguration().getStrongSpamThreshold();
		//final String strongSpamTerms = this.repository.getSystemConfiguration().getStrongSpamTerms();
		final String[] parts = spamTerms.split(",");
		for(final String part: parts) {
				if(normalizedText.contains(part)) {
					counter+=1;
			}
		}
		final double ratio = counter/(double)numberOfWords;
		return ratio>threshold;
		
	}

}
