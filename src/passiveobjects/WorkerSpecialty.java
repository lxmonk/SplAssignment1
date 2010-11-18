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
	
	public WorkerSpecialty(String aSpecialty) {
		this.specialty = aSpecialty;
	}
	public String getSpecialty() {
		return this.specialty;
	}
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
