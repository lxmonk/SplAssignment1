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



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		WorkerSpecialty other = (WorkerSpecialty) obj;
		if (this.specialty == null) {
			if (other.specialty != null)
				return false;
		} else if (!this.specialty.equals(other.specialty))
			return false;
		return true;
	}
	


}
