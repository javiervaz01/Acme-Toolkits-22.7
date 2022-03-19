package acme.entities.patronages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	protected Status					status;
	
	@NotNull
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique = true)
	protected String					code;
	
	@NotBlank
	@Length(max = 255)
	protected String 					legalStuff;
	
	@NotNull
	protected Money						budget;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date						creationDate;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date						startDate;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date						endDate;
	
	@URL
	protected String					info;

	// Derived attributes -----------------------------------------------------
	

	// Relationships ----------------------------------------------------------
	
	@ManyToOne(optional = false)
	@Valid
	@NotNull
	// If we don't use this notNull, the error will get
	// to the database, panic and throw an exception.
	protected Inventor inventor;
	
	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Patron patron;
	
	// the patron who offers the patronage?
}
