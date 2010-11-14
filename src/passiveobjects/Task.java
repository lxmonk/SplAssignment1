/**
 * 
 */
package passiveobjects;

import java.util.List;

import javax.sql.rowset.spi.SyncResolver;

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
	 * return the specialty needed by the worker to complete this task.
	 * 
	 * @return relevant WorkerSpecialty
	 */
	public WorkerSpecialty getWorkerSpecialty();

	/**
	 * return the name of the manager who published this task
	 * 
	 * @return the manager's name
	 */
	public String getManagerName();

	/**
	 * return the manager who published this task
	 * 
	 * @return the manager
	 */
	public Manager getManager();

	/**
	 * returns the size(number of work hours) of this task
	 * 
	 * @return the number of work hours of this task
	 */
	public int getSize();

	/**
	 * returns the number of work hours still needed in order to complete this
	 * task
	 * 
	 * @return the number of work hours still needed
	 */
	public int getHoursStillNeeded();

	/**
	 * returns the number of work hours already done on this task
	 * 
	 * @return the number of completed work hours
	 */
	public int getHoursDone();

	/**
	 * returns a list of the needed Resources for the task
	 * 
	 * @return a list of needed Resources
	 */
	public List<Resource> getNeededResources();

	/**
	 * returns a list of workers who worked on the task
	 * 
	 * @return a list of workers
	 */
	public List<Worker> getWorkers();

	/**
	 * returns a list of all workers names who worked on the task
	 * 
	 * @return a list of workers names
	 */
	public List<String> getAllWorkerNames();

	/**
	 * Checks if the task is complete or not
	 * 
	 * @return a boolean if the task is complete or not
	 */
	public boolean isComplete();

	/**
	 * notify the manager that Task has been completed.
	 */
	public void taskIsDone();

	/**
	 * adds a worker to the list of workers who worked on the task
	 * @param worker 
	 * 				the new worker working on the task
	 */
	public void addWorker(Worker worker);

	/**
	 * update the number of hours done on this task by one hour.
	 * 
	 * 
	 */
	public void incrementHoursDone();
	
	/**
	 * Decreases the number of hours the task still needs to be completed.
	 * @param hours the number of hours to be decreased.
	 */
	public void decreaseHoursStillNeeded(int hours);
	
	/**
	 * Sets the manager who published this task to the working board.
	 * @param manager the {@link Manager} 
	 */
	public void setManager(Manager manager);
}
