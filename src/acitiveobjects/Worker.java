/**
 * 
 */
package acitiveobjects;

import java.util.List;
import java.util.logging.Logger;

import javax.sql.rowset.spi.SyncResolver;

import passiveobjects.Task;
import passiveobjects.Warehouse;
import passiveobjects.WorkerSpecialty;
import passiveobjects.WorkingBoard;

/**
 * @author lxmonk
 *
 */
public class Worker implements Runnable {
	final String name;
	final int workHours;
	final List<WorkerSpecialty> specialtyList;
	Task currentTask;
	Logger logger;
	WorkingBoard workingBoard;
	Warehouse warehouse;

	
	/**
	 * The worker's constructor
	 * @param aName worker's name
	 * @param theWorkHours worker's work hours
	 * @param list A list of {@link WorkerSpecialty}
	 * @param theWorkingBoard the {@link WorkingBoard}
	 * @param theWarehouse the {@link Warehouse}
	 * @param aLogger the Logger
	 */	
	public Worker(String aName, int theWorkHours, List<WorkerSpecialty> list,
			WorkingBoard theWorkingBoard,Warehouse theWarehouse, Logger aLogger) {
		this.name=aName;
		this.workHours=theWorkHours;
		this.specialtyList=list;
		this.workingBoard=theWorkingBoard;
		this.warehouse=theWarehouse;
		this.currentTask=null;
		this.logger = aLogger;

	}

	/**
	 * returns the Worker's name
	 * @return Worker's name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * returns the worker's workHours 
	 * @return the worker's workHours
	 */
	public int getWorkHours() {
		return this.workHours;
	}
	
	/**
	 * simulates the worker's work (waiting) according to 
	 * the number of hours he needs to work on the task
	 * 
	 */
	public synchronized void doSomeWork() {
		int shortShift=this.currentTask.signInWorker(this);
		if (shortShift != 0) { // last worker doesn't spend all his workHours on the task (hoursNeeded < workHours)
			try {
				this.wait(shortShift*1000); // TODO: make sure it is right
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
			this.currentTask.incrementHoursDone(shortShift);
			if ( (!this.currentTask.isComplete()) && (this.currentTask.getHoursDone() == this.currentTask.getSize()))
				this.currentTask.taskIsDone(); 
		}
		try {
			this.wait((this.workHours-shortShift)*1000); // TODO: make sure it is right
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		this.currentTask.incrementHoursDone(this.workHours-shortShift);
		if ( (!this.currentTask.isComplete()) && (this.currentTask.getHoursDone() == this.currentTask.getSize())) 
				this.currentTask.taskIsDone();	
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			for (WorkerSpecialty workerSpecialty : this.specialtyList) {
				this.currentTask = this.workingBoard.getTaskBySpecialty(workerSpecialty);
				if (this.currentTask != null) break;
			}
			if (this.currentTask != null) { // found a task to work on
				this.warehouse.getResources(this.currentTask); // if resources are not available, the worker waits			
				this.doSomeWork();
				this.warehouse.returnResources(this.currentTask.getNeededResources());	
			} else
				try {
					this.workingBoard.getNewMonitor().wait(10000); // wait till a new task will be posted on the workingBoard or 10 seconds
											// TODO: make sure it is right
				} catch (InterruptedException e) {
					// TODO: change when we write the stop project
					// TODO Auto-generated catch block
				}
		}				
	}

}
