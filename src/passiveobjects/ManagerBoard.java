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
	public Map<ManagerSpecializtion, ProjectBoxImpl> getPendingProjects();

	/**
	 * return the {@link ProjectBoxImpl} containing all the {@link Project}s by a
	 * specific {@link ManagerSpecializtion}.
	 * 
	 * @pre none.
	 * @post none.
	 * @param specializtion
	 *            the wanted {@link ManagerSpecializtion}
	 * @return {@link ProjectBoxImpl} containing all the {@link Project}s by a
	 *         specific {@link ManagerSpecializtion}.
	 */
	public ProjectBoxImpl getProjectBox(ManagerSpecializtion specializtion);

	/**
	 * Create the map containing the {@link ProjectBoxImpl}es hashed by their
	 * {@link ManagerSpecializtion}.
	 * 
	 * <b>This method is used only at startup</b>
	 * 
	 * @param map
	 *            a map with {@link ManagerSpecializtion} as keys and the
	 *            corresponding {@link ProjectBoxImpl} as values.
	 */
	public void createProjectsMap(Map<ManagerSpecializtion, ProjectBoxImpl> map);

}
