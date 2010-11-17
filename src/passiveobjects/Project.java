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
	 * 
	 * @return
	 */
	public String getName();

	public Task getNextTask();

	/**
	 * removes the {@link Task} task from the project.
	 * 
	 * @param task
	 *            the {@link Task} to be removed.
	 */
	public void removeTask(Task task);

	public List<Task> getCompletedTasks();

	public Manager getManager();

	public String getManagerName();

	public int getTotalHours();

	public ManagerSpecialization getNextManagerSpecializtion();

	public void addTask(Task task);

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
}
