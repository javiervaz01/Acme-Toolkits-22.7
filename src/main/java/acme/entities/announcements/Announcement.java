package acme.entities.announcements;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date moment;

	@NotBlank
	@Length(max = 100)
	protected String title;

	@NotBlank
	@Length(max = 100)
	protected String body;

	// We tried to use the primitive type boolean, but inserting the sample
	// data failed. The error was: "couldn't assign value 'false' to property"
	// We also tried another formats, such as using 0 and 1, and the error
	// persisted. We have decided to use the Boolean type with @NotNull again,
	// as it works as expected when populating the system.
	protected boolean isCritical;

	@URL
	protected String info;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	// We don't need to have a @ManyToOne Announcement because we don't need to
	// keep track of who was the administrator who created the announcement.

}
