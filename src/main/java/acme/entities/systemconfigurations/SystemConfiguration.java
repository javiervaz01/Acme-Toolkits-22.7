package acme.entities.systemconfigurations;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}$",
			message = "{acme.validation.system-configuration.pattern.currency}")
	protected String currency;

	@NotBlank
	@Pattern(regexp = "^([A-Z]{3})(,[A-Z]{3})*$",
			message = "{acme.validation.system-configuration.pattern.accepted-currencies}")
	protected String acceptedCurrencies;

	@NotBlank
	@Pattern(
			regexp = "^([\\p{L}\\p{N}\\s]|(?!,)[\\p{P}])+(, *([\\p{L}\\p{N}\\s]|(?!,)[\\p{P}])+)*$",
			message = "{acme.validation.system-configuration.pattern.spam-terms}")
	protected String strongSpamTerms;

	@PositiveOrZero
	@Max(1)
	protected double strongSpamThreshold;

	@NotBlank
	@Pattern(
			regexp = "^([\\p{L}\\p{N}\\s]|(?!,)[\\p{P}])+(, *([\\p{L}\\p{N}\\s]|(?!,)[\\p{P}])+)*$",
			message = "{acme.validation.system-configuration.pattern.spam-terms}")
	protected String weakSpamTerms;

	@PositiveOrZero
	@Max(1)
	protected double weakSpamThreshold;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
