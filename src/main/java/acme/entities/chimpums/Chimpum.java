package acme.entities.chimpums;

import java.util.Date;

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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chimpum extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	// TODO created automatically from creationDate in the createService
	@NotNull
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?:\\d{4}/\\d{2}/\\d{2}$") // dd/mm/yy
	protected String code;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;

	@NotBlank
	@Length(max = 100)
	protected String title;

	@NotBlank
	@Length(max = 255)
	protected String description;

	// TODO implement custom validations for Date
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date startDate;

	// TODO implement custom validations for Date
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date endDate;

	// TODO implement custom validations for Money
	@NotNull
	protected Money budget;

	@URL
	protected String info;

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor inventor;
	
}
