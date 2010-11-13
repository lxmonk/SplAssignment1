/**
 * 
 */
package passiveobjects;

import java.util.List;

import acitiveobjects.Manager;
import acitiveobjects.Worker;

/**
 * @author lxmonk
 * 
 */
public interface Task {

	/**
	 * return the specialization needed by the manager to complete this task.
	 * 
	 * @return the relevant ManagerSpecializtion
	 */
	public ManagerSpecializtion getManagerSpecializtion();

	/**
	 * returns
	 */
	public WorkerSpecialty getWorkerSpecialty();

	public String getManagerName();

	public Manager getManager();

	public int getSize();

	public int getHoursStillNeeded();

	public int getHoursDone();

	public List<Resource> getNeededResources();

	public List<Worker> getWorkers();

	public List<String> getAllWorkerNames();

	public boolean isComplete();

	public void taskIsDone(); // notify the manager the Task has been completed.

}
