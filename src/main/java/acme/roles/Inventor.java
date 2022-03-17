/*
 * Consumer.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.roles;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.announcements.Announcement;
import acme.entities.chirp.Chirp;
import acme.framework.roles.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inventor extends UserRole {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max=101)
	protected String			company;

	@NotBlank
	@Length(max=256)
	protected String			statement;
	
	@URL
	protected String 			info;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@OneToMany
	protected Chirp chirp;
	
	@OneToMany
	protected Announcement announcement;
}
