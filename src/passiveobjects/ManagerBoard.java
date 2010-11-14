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
	public Map<ManagerSpecialization, ProjectBox> getPendingProjects();

	/**
	 * return the {@link ProjectBox} containing all the {@link Project}s by a
	 * specific {@link ManagerSpecialization}.
	 * 
	 * @pre none.
	 * @post none.
	 * @param specializtion
	 *            the wanted {@link ManagerSpecialization}
	 * @return {@link ProjectBox} containing all the {@link Project}s by a
	 *         specific {@link ManagerSpecialization}.
	 */
	public ProjectBox getProjectBox(ManagerSpecialization specializtion);

	/**
	 * Create the map containing the {@link ProjectBox}es hashed by their
	 * {@link ManagerSpecialization}.
	 * 
	 * <b>This method is used only at startup</b>
	 * 
	 * @param map
	 *            a map with {@link ManagerSpecialization} as keys and the
	 *            corresponding {@link ProjectBox} as values.
	 */
	public void createProjectsMap(Map<ManagerSpecialization, ProjectBox> map);

}
