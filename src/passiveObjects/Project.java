/**
 * 
 */
package passiveObjects;

import java.util.List;

import acitiveObjects.Manager;

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
	
	public ManagerSpecializtion getNextManagerSpecializtion();
	
	public void addTask(Task task);
	
	public void addTasks(List<Task> taskList);
}
