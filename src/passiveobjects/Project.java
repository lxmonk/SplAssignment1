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
	
	public String getName();
	
	public Task getNextTask();
	
	public List<Task> getCompletedTasks();
	
	public Manager getManager();
	
	public String getManagerName();
	
	public long getTotalHours();
	
	public ManagerSpecialization getNextManagerSpecializtion();
	
	public void addTask(Task task);
	
	public void addTasks(List<Task> taskList);
}
