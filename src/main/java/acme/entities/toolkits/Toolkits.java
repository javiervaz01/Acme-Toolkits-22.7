package acme.entities.toolkits;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkits extends AbstractEntity {
	
	/**
	 * A toolkit is a bundle with components and tools that are expected to work as a whole.
	 * The system must store the following data about them:
	 * a code (pattern “^[A-Z]{3}-[0-9]{3}(-[A-Z])?$”, unique),
	 * title (not blank, shorter than 101 characters),
	 * description (not blank, shorter than 256 characters),
	 * assembly notes (not blank, shorter than 256 characters),
	 * and an optional link with further information.
	 * A toolkit may have several instances of the same component, but only one instance of a given tool.
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------
	
	@NotNull
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$", message = "default.error.conversion")
	protected String code;
	
	@NotBlank
	@Max(101)
	protected String title;
	
	@NotBlank
	@Max(256)
	protected String description;
	
	@NotBlank
	@Max(256)
	protected String assemblyNotes;
	
	@URL
	protected String optionalLink;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
