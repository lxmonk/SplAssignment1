/**
 * 
 */
package passiveObjects;

import java.util.Map;


/**
 * @author lxmonk
 * 
 */
public interface ManagerBoard {
	/**
	 * returns a map of  all the projects waiting to be executed
	 * @pre none.
	 * @post none. 
	 * @return a map of  all the projects waiting to be executed
	 */
	public Map<ManagerSpecializtion, ProjectBox> getPendingProjects();
	/**
	 * return the ProjectBox containing all the projects by a specific ManagerSpecializtion
	 * @pre none.
	 * @post none.  
	 * @param specializtion the wanted ManagerSpecializtion
	 * @return ProjectBox of all the projects by a specific ManagerSpecializtion
	 */
	public ProjectBox getProjectBox(ManagerSpecializtion specializtion);
	

}
