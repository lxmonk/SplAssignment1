/**
 * 
 */
package passiveobjects;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import acitiveobjects.Manager;

/**
 * @author lxmonk
 *
 */
public class WorkingBoardImpl implements WorkingBoard {
	
	Map<WorkerSpecialty,Queue<Task>> workingBoard;
	 
	Object newMonitor;
	
	/**
	 * The {@link WorkingBoardImpl} constructor
	 */
	public WorkingBoardImpl() {
		this.workingBoard = new ConcurrentHashMap<WorkerSpecialty,Queue<Task>>();
		this.newMonitor = new Object();
	}
	/* (non-Javadoc)
	 * @see passiveObjects.WorkingBoard#getTaskBySpecialty(passiveObjects.WorkerSpecialty)
	 */
	@Override
	public Task getTaskBySpecialty(WorkerSpecialty specialty) {
		Task task =  this.workingBoard.get(specialty).poll();
		this.workingBoard.get(specialty).add(task);
		if (task.getHoursStillNeeded() <= 0) {
			task =  this.workingBoard.get(specialty).poll();
			this.workingBoard.get(specialty).add(task);
		}
		return task;
		
	}

	/* (non-Javadoc)
	 * @see passiveObjects.WorkingBoard#postTask(passiveObjects.Task)
	 */
	@Override
	public void postTask(Task task,Manager manager) throws InterruptedException {
		task.setManager(manager);
		Queue<Task> specialtyList=this.workingBoard.get(task.getWorkerSpecialty()); 	
		if (specialtyList == null)
			specialtyList = new ConcurrentLinkedQueue<Task>();
		specialtyList.add(task);
		this.newMonitor.notifyAll(); // wakes all the workers who didn't find tasks
	}

	/* (non-Javadoc)
	 * @see passiveObjects.WorkingBoard#removeTask(passiveObjects.Task)
	 */
	@Override
	public void removeTask(Task task) {
		Queue<Task> specialtyList=this.workingBoard.get(task.getWorkerSpecialty()); 	
		if (specialtyList != null)
			specialtyList.remove(task);
	}

	@Override
	public boolean findTask(Task task) {
		Queue<Task> specialtyList=this.workingBoard.get(task.getWorkerSpecialty()); 	
		if (specialtyList != null)
			return specialtyList.contains(task);
		return false;
	}

	@Override
	public synchronized Object getNewMonitor() {
		return newMonitor;
	}

}
