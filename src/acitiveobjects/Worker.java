/**
 * 
 */
package acitiveobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.rowset.spi.SyncResolver;

import passiveobjects.Resource;
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
	boolean gotResources;

	
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
			WorkingBoard theWorkingBoard,Warehouse theWarehouse) {
		this.name=aName;
		this.workHours=theWorkHours;
		this.specialtyList=list;
		this.workingBoard=theWorkingBoard;
		this.warehouse=theWarehouse;
		this.currentTask=null;
		this.gotResources=false;
	}

	/**
	 * sets the {@link Logger}
	 * @param aLogger the Logger
	 */
	public void setLogger(Logger aLogger) {
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
	 * returns the worker's currents {@link Task} 
	 * @return the worker's currents {@link Task}
	 */
	public Task getCurrentTask() {
		return this.currentTask;
	}
	
	/**
	 * returns a String list of all the resources the worker got
	 * @return a list of resources names
	 */
	public List<String> getWorkerResources() {
		if ((!this.gotResources) || (this.currentTask == null)) 
			return null;
		else {
			List<String> resourceList = new ArrayList<String>();
			for (Resource resource : this.currentTask.getNeededResources())
				resourceList.add(resource.getName());
			return resourceList;
		}
	}
	

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			this.currentTask=null;
			this.gotResources=false;
			Task  temp;
			for (WorkerSpecialty workerSpecialty : this.specialtyList) { // searching for a task
				temp = this.workingBoard.getTaskBySpecialty(workerSpecialty);
				if ((temp != null) && (temp.getHoursStillNeeded() > 0)  &&  
						(!temp.isComplete()) && (!temp.isAborted())) {
					this.currentTask=temp;
					break; // we found a task , and it still needs hours, and it's not completed or aborted
				}
					
			}
			
			if (this.currentTask != null) { // found a qualifies task to work on
				try {
					this.gotResources =  this.warehouse.getResources(this.currentTask); // if resources are not available, the worker waits
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
				}
				
				while ((this.gotResources) && (this.currentTask.getHoursStillNeeded() > 0)  && (!this.currentTask.isComplete()) && (!this.currentTask.isAborted())) {
					int shortShift=this.currentTask.signInWorker(this);
					if (shortShift == 0)
						this.currentTask.work(this.workHours);
					else {
						this.currentTask.work(shortShift);
						try {
							this.wait((this.workHours-shortShift)*1000); // TODO: make sure it is right
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
						}
					}
				}
				
				if (this.gotResources) {
					this.warehouse.returnResources(this.currentTask.getNeededResources());
					this.gotResources=false;
				}

			} else
				try {
					this.workingBoard.getNewMonitor().wait(5000); // wait till a new task will be posted on the workingBoard or 10 seconds
					// TODO: make sure it is right
				} catch (InterruptedException e) {
					// TODO: change when we write the stop project
					// TODO Auto-generated catch block
				}
		}				
	}

}
