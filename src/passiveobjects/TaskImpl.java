/**
 * 
 */
package passiveobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import acitiveobjects.Manager;
import acitiveobjects.Worker;

/**
 * @author lxmonk
 * 
 */
public class TaskImpl implements Task {

	final String name;
	final ManagerSpecialization managerSpecialization;
	final WorkerSpecialty workerSpecialty;
	Manager manager;
	final int size;
	volatile int hoursDone;
	volatile int hoursNeeded;
	final List<Resource> resources;
	List<Worker> workers;
	volatile boolean complete;
	volatile boolean aborted;

	/**
	 * a new task constructor
	 * 
	 * @param aName
	 *            the name of the {@link Task}
	 * @param aManagerSpecialization
	 *            the {@link ManagerSpecialization} needed to perform this task.
	 * @param aWorkerSpecialty
	 *            the WorkerSpecialty needed for this task
	 * @param aSize
	 *            the size in work hours of this task
	 * @param resourcesList
	 *            the Resources needed for this task
	 */

	public TaskImpl(String aName, ManagerSpecialization aManagerSpecialization,
			WorkerSpecialty aWorkerSpecialty, int aSize,
			List<Resource> resourcesList) {
		this.name=aName;
		this.workerSpecialty = aWorkerSpecialty;
		this.managerSpecialization = aManagerSpecialization;
		this.size = aSize;
		this.hoursNeeded = aSize;
		this.hoursDone = 0;
		this.resources = resourcesList;
		this.workers = new CopyOnWriteArrayList<Worker>();
		this.complete=false;
		this.aborted=false;
		
	}

	@Override
	public WorkerSpecialty getWorkerSpecialty() {
		return this.workerSpecialty;
	}

	@Override
	public List<String> getAllWorkerNames() {
		List<String> workerNames = new CopyOnWriteArrayList<String>();
		for (Worker worker : this.workers) {
			workerNames.add(worker.getName());
		}
		return workerNames;
	}

	@Override
	public int getHoursDone() {
		return this.hoursDone;
	}

	@Override
	public int getHoursStillNeeded() {
		return this.hoursNeeded;
	}

	@Override
	public Manager getManager() {
		return this.manager;
	}

	@Override
	public String getManagerName() {
		return this.manager.getName();
	}

	@Override
	public ManagerSpecialization getManagerSpecializtion() {
		return this.managerSpecialization;
	}

	@Override
	public List<Resource> getNeededResources() {
		return this.resources;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public List<Worker> getWorkers() {
		return this.workers;
	}

	@Override
	public boolean isComplete() {
		return this.complete;
	}

	@Override
	public void taskIsDone() {
		this.complete=true;
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized int signInWorker(Worker worker) {
		int workHours = worker.getWorkHours();
		int shortShift=0;
		if (this.hoursNeeded >= workHours)
			this.decreaseHoursStillNeeded(workHours); 
		else { 
			shortShift = this.hoursNeeded; // Task will complete in the middle of the worker's workHours
			this.decreaseHoursStillNeeded(shortShift);
		}
		this.workers.add(worker);
		return shortShift;
	}

	@Override
	public synchronized void incrementHoursDone(int hours) {
		this.hoursDone+=hours;
	}

	@Override
	public void setManager(Manager aManager) {
		this.manager = aManager;
	}

	@Override
	public synchronized void decreaseHoursStillNeeded(int hours) {
		this.hoursNeeded -= hours;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public synchronized void work(int workHours) {
		try {
			this.wait(workHours*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		this.incrementHoursDone(workHours);
		if (this.hoursDone == this.size) this.taskIsDone();  
	}

	@Override
	public void abortTask() {
		this.aborted=true;
		
	}

	@Override
	public boolean isAborted() {
		return this.aborted;
	}
}