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

	final ManagerSpecializtion managerSpecialization;
	final WorkerSpecialty workerSpecialty;
	Manager manager;
	final int size;
	volatile int hoursDone;
	volatile int hoursNeeded;
	final List<Resource> resources;
	List<Worker> workers;
	boolean complete;

	/**
	 * a new task constructor
	 * 
	 * @param aManagerSpecialization
	 *            the {@link ManagerSpecializtion} needed to perform this task.
	 * @param aWorkerSpecialty
	 *            the WorkerSpecialty needed for this task
	 * @param aSize
	 *            the size in work hours of this task
	 * @param resourcesList
	 *            the Resources needed for this task
	 */
	public TaskImpl(ManagerSpecializtion aManagerSpecialization,
			WorkerSpecialty aWorkerSpecialty, int aSize,
			List<Resource> resourcesList) {
		this.workerSpecialty = aWorkerSpecialty;
		this.managerSpecialization = aManagerSpecialization;
		this.size = aSize;
		this.hoursNeeded = aSize;
		this.hoursDone = 0;
		this.resources = resourcesList;
		this.workers = new CopyOnWriteArrayList<Worker>();
		this.complete = false;
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
		return this.size - this.hoursDone;
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
	public ManagerSpecializtion getManagerSpecializtion() {
		return this.manager.getSpecializtion();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void addWorker(Worker worker) {
		this.workers.add(worker);
		// TODO: do something with hours
	}

	@Override
	public synchronized void incrementHoursDone() {
		this.hoursDone++;
	}

	@Override
	public void setManager(Manager aManager) {
		this.manager = aManager;
	}

	@Override
	public synchronized void decreaseHoursStillNeeded(int hours) {
		this.hoursNeeded -= hours;
	}
}
