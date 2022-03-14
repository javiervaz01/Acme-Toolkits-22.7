package acme.datatypes;


import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.AbstractDatatype;

public class SequenceNumber extends AbstractDatatype{

	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

		@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
		@Column(unique=true)
		@NotBlank
		protected String patronagCode;
		
		@Pattern(regexp="[0-9]{4}")
		@NotBlank
		protected String serialNumber;

		// Object interface -------------------------------------------------------


		@Override
		public String toString() {
			StringBuilder result;
			
			result= new StringBuilder();
			result.append("<");
			result.append(this.patronagCode);
			result.append(">:<");
			result.append(this.serialNumber);
			result.append(">");

			return result.toString();
		}

	
	
	
	
	
}
