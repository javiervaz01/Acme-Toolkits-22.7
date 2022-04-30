package acme.entities.systemconfigurations;

import javax.persistence.Entity;
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
	@Pattern(regexp = "^[A-Z]{3}$")
	protected String currency;

	@NotBlank
	@Pattern(regexp = "^([A-Z]{3},)*[A-Z]{3}$")
	protected String acceptedCurrencies; // Comma separated groups of three letters


	@NotBlank
	@Pattern(regexp = "^(?:[a-zA-Z0-9 ']+,)*[a-zA-Z0-9 ']+$")
	protected String strongSpamTerms;

	@PositiveOrZero
	protected double strongSpamThreshold;

	@NotBlank
	@Pattern(regexp = "^(?:[a-zA-Z0-9 ']+,)*[a-zA-Z0-9 ']+$")
	protected String weakSpamTerms;

	@PositiveOrZero
	protected double weakSpamThreshold;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
