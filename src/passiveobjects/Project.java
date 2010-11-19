/**
 * 
 */
package passiveobjects;

import java.util.List;

import acitiveobjects.Manager;

/**
 * @author lxmonk
 * 
 */
public interface Project {
	/**
	 * return the name of this {@link Project}.
	 * 
	 * @return the name of this {@link Project}.
	 */
	public String getName();

	/**
	 * return the next {@link Task} for this project. null if there isn't one.
	 * 
	 * @return the next {@link Task} for this project. null if there isn't one.
	 */
	public Task getNextTask();

	/**
	 * removes the {@link Task} task from the project.
	 * 
	 * @param task
	 *            the {@link Task} to be removed.
	 */
	public void removeTask(Task task);

	/**
	 * return the {@link List} of completed {@link Task}s for this project.
	 * 
	 * @return the {@link List} of completed {@link Task}s for this project.
	 */
	public List<Task> getCompletedTasks();

	/**
	 * return the current/last {@link Manager} to handle this project.
	 * 
	 * @return the current/last {@link Manager} to handle this project.
	 */
	public Manager getManager();

	/**
	 * return the name of the current/last {@link Manager} to handle this
	 * project.
	 * 
	 * @return the name of the current/last {@link Manager} to handle this
	 *         project.
	 */
	public String getManagerName();

	/**
	 * return the total number of hours workers invested in this {@link Project}.
	 * @return the total number of hours workers invested in this {@link Project}.
	 */
	public int getTotalHours();

	/**
	 * return the next {@link ManagerSpecialization} needed by this project, or null.
	 * @return the next {@link ManagerSpecialization} needed by this project, or null.
	 */
	public ManagerSpecialization getNextManagerSpecializtion();

	/**
	 * add a task to this {@link Project}
	 * @param task the {@link Task} to be added.
	 */
	public void addTask(Task task);

	/**
	 * add a {@link List} of {@link Task}s to this project.
	 * @param taskList the {@link List} of {@link Task}s to be added.
	 */
	public void addTasks(List<Task> taskList);

	/**
	 * update the total number of hours invested in this project.
	 * 
	 * @param hours
	 *            the amount of hours to add
	 */
	public void updateTotalHours(int hours);

	/**
	 * set the {@link Manager} handling this {@link Project}.
	 * 
	 * @param manager
	 *            the {@link Manager} handling this {@link Project}.
	 */
	public void setManager(Manager manager);

	/**
	 * abort this {@link Project}.
	 */
	public void abortProject();
	
	/**
	 * return a boolean stating whether this project has been aborted.
	 * @return a boolean stating whether this project has been aborted.
	 */
	public boolean isAborted();
}
