package acme.entities.chimpums;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
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
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{2}$") // Change also in the create service
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

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date endDate;

	@NotNull
	protected Money budget;

	@URL
	protected String info;
}
