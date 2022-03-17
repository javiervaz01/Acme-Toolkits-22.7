package acme.entities.systemconfigurations;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	protected String				currency;
	
	@NotNull
	protected String 				acceptedCurrencies;

	@NotNull
	protected String				strongSpamTerms;

	@NotNull
	protected Double				strongSpamThreshold;
	
	@NotNull
	protected String				weakSpamTerms;
	
	@NotNull
	protected Double				weakSpamThreshold;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}