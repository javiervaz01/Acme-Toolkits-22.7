package acme.datatypes;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.AbstractDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class TimePeriod extends AbstractDatatype {
	
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

		@NotNull
		protected Date			startDate;

		@NotNull
		protected Date			endDate;



		// Object interface -------------------------------------------------------


		@Override
		public String toString() {
			StringBuilder result;
			
			result = new StringBuilder();
			result.append("[");
			result.append(this.startDate);
			result.append(" ,");
			result.append(this.endDate);
			result.append("]");
			

			return result.toString();
		}

}
