package acme.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class SpamDetectorService {
	
	public boolean ratioSurpassesThreshold(final String text, final double threshold, final String spamTerms) {
		int counter = 0;
		final String normalizedText = text.trim().replaceAll("\\s{2,}", " ").toLowerCase().replaceAll("[^\\w\\s]", "");
		final int numberOfWords = normalizedText.split(" ").length;
		final String[] parts = spamTerms.split(",");
		this.eliminateDuplicated(parts);
		for(final String part: parts) {
			final Pattern regex = Pattern.compile("\\b" + Pattern.quote(part) + "\\b", Pattern.CASE_INSENSITIVE);
			final Matcher match = regex.matcher(normalizedText);
				if(match.find()) {
					counter+=1;
			}
		}
		final double ratio = counter/(double)numberOfWords;
		return ratio>threshold;
		
	}
	public void eliminateDuplicated(final String[] array) {
	   for(int i = 0; i < array.length; i++){
	      for(int j = i + 1; j < array.length; j++){
	    	  if(i != j){
	    		  if(array[i]!=null && array[i].equals(array[j])){
	    			  array[j] = ",";}	}	}	}
	}

}
