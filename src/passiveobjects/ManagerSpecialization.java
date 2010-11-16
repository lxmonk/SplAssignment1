/**
 * 
 */
package passiveobjects;

/**
 * @author lxmonk
 *
 */
public class ManagerSpecialization {

	final String specialization;
	/**
	 * constructor
	 * @param aSpecialization the given {@link ManagerSpecialization}
	 */
	public ManagerSpecialization(String aSpecialization) {
		this.specialization = aSpecialization;
	}
	/**
	 * 
	 * 
	 * @return
	 */
	public String getSpecialization(){
		return this.specialization;
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
		ManagerSpecialization other = (ManagerSpecialization) obj;
		if (this.specialization == null) {
			if (other.specialization != null)
				return false;
		} else if (!this.getSpecialization().equals(other.getSpecialization()))
			return false;
		return true;
	}
	
	

}
