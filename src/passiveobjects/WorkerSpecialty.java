/**
 * 
 */
package passiveobjects;

/**
 * @author lxmonk
 *
 */
public class WorkerSpecialty {

	String specialty; 
	/**
	 * a WorkerSpecialty Constructor
	 * @param aSpecialty the {@link WorkerSpecialty} type
	 */
	public WorkerSpecialty(String aSpecialty) {
		this.specialty = aSpecialty;
	}
	
	/**
	 * returns the type of {@link WorkerSpecialty}
	 * @return the type of {@link WorkerSpecialty}
	 */
	public String getSpecialty() {
		return this.specialty;
	}
	
	/**
	 * compares between obj and this {@link WorkerSpecialty}
	 * @param obj the {@link WorkerSpecialty} to compare with
	 * @return it the two WorkerSpecialties are the same or not 
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		WorkerSpecialty other = (WorkerSpecialty) obj;
		if (this.specialty == null) {
			if (other.getSpecialty() != null)
				return false;
		} else if (!this.getSpecialty().equals(other.getSpecialty()))
			return false;
		return true;

		
	}

}
