package passiveobjects;

import java.util.Collection;
import java.util.Iterator;

public interface ProjectBox {

	/**
	 * return a project from the box. if the box is empty, the manager will
	 * wait.
	 * 
	 * @pre none
	 * @post none
	 * 
	 * @return a project from the box.
	 */
	public Project getProject();

	/**
	 * add a project to the {@link ProjectBox}.
	 * 
	 * @pre project.getNextManagerSpecializtion() == this.ManagerSpecializtion
	 * @post the {@link ProjectBox} now contains project.
	 * @param project
	 *            the {@link Project} to be added.
	 * @throws RuntimeException
	 *             in case the project should not be added to this
	 *             {@link ProjectBox}.
	 */
	public void addProject(Project project) throws RuntimeException;

	/**
	 * remove a project from the box, if it's in the box.
	 * 
	 * @pre the {@link ProjectBox} contains project.
	 * @post the {@link ProjectBox} no longer contains project.
	 * @param project
	 *            the {@link Project} to be removed.
	 * @throws RuntimeException
	 *             in case the {@link ProjectBox} does NOT contain the project.
	 */
	public void removeProject(Project project) throws RuntimeException;

	/**
	 * returns the {@link ManagerSpecialization} corresponding to this
	 * {@link ProjectBox}.
	 * 
	 * @return the {@link ManagerSpecialization} corresponding to this
	 *         {@link ProjectBox}.
	 */
	public ManagerSpecialization getManagerSpecializtion();

	/**
	 * return a {@link Collection} of all the {@link Project}s in the
	 * {@link ProjectBox} .
	 * 
	 * @return a {@link Collection} of all the {@link Project}s in the
	 *         {@link ProjectBox}
	 */
	public Collection<Project> getAllProjects();

}
