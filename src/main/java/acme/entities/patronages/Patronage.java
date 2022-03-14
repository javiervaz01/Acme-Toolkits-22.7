package acme.entities.patronages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.URL;

import acme.datatypes.TimePeriod;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage  extends AbstractEntity{
	

	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@Enumerated(EnumType.STRING)
	protected Status					status;
	
	@NotNull
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique=true)
	protected String					code;
	
	@NotBlank
	@Max(256)
	protected String 					legalStuff;
	
	@NotNull
	@Positive
	protected Money						budget;
	
	@NotNull
	protected Date						creationDate;
	
	@NotNull
	protected TimePeriod				period;
	
	@URL
	protected String					info;

	// Derived attributes -----------------------------------------------------
	

	// Relationships ----------------------------------------------------------


}
