package acme.entities.toolkits;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity {

	/**
	 * A toolkit is a bundle with components and tools that are expected to work as a whole. The
	 * system must store the following data about them: a code (pattern
	 * “^[A-Z]{3}-[0-9]{3}(-[A-Z])?$”, unique), title (not blank, shorter than 101 characters),
	 * description (not blank, shorter than 256 characters), assembly notes (not blank, shorter than
	 * 256 characters), and an optional link with further information. A toolkit may have several
	 * instances of the same component, but only one instance of a given tool.
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;

	@NotBlank
	@Length(max = 100)
	protected String title;

	@NotBlank
	@Length(max = 255)
	protected String description;

	@NotBlank
	@Length(max = 255)
	protected String assemblyNotes;

	@URL
	protected String link;
	
	protected boolean draftMode;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor inventor;

}
