/**
 * 
 */
package passiveobjects;

import java.util.Map;

/**
 * @author lxmonk
 * 
 */
public interface ManagerBoard {
	/**
	 * returns a map of all the {@link Project}s waiting to be executed.
	 * 
	 * @pre none.
	 * @post none.
	 * @return a map of all the {@link Project}s waiting to be executed.
	 */
	public Map<ManagerSpecializtion, ProjectBox> getPendingProjects();

	/**
	 * return the {@link ProjectBox} containing all the {@link Project}s by a
	 * specific {@link ManagerSpecializtion}.
	 * 
	 * @pre none.
	 * @post none.
	 * @param specializtion
	 *            the wanted {@link ManagerSpecializtion}
	 * @return {@link ProjectBox} containing all the {@link Project}s by a
	 *         specific {@link ManagerSpecializtion}.
	 */
	public ProjectBox getProjectBox(ManagerSpecializtion specializtion);

}
