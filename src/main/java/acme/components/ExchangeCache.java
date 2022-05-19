package acme.components;

import java.util.Date;

import javax.persistence.Entity;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExchangeCache extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	private Date date;
	private double rate;
	private String sourceCurrency;
	private String targetCurrency;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
