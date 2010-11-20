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
	
	Map<String,Queue<Task>> workingBoard;
	 	
	/**
	 * The {@link WorkingBoardImpl} constructor
	 */
	public WorkingBoardImpl() {
		this.workingBoard = new ConcurrentHashMap<String,Queue<Task>>();
	}
	
	/* (non-Javadoc)
	 * @see passiveObjects.WorkingBoard#getTaskBySpecialty(passiveObjects.WorkerSpecialty)
	 */
	@Override
	public Task getTaskBySpecialty(WorkerSpecialty specialty) {
		Queue<Task> temp = this.workingBoard.get(specialty.getSpecialty());
		if (temp == null) return null;
		Task task =  temp.poll();
		if (task == null) return null;
		this.workingBoard.get(specialty.getSpecialty()).add(task);
		if (task.getHoursStillNeeded() <= 0) {
			task =  this.workingBoard.get(specialty.getSpecialty()).poll();
			this.workingBoard.get(specialty.getSpecialty()).add(task);
		}
		return task;
		
	}

	/* (non-Javadoc)
	 * @see passiveObjects.WorkingBoard#postTask(passiveObjects.Task)
	 */
	@Override
	public synchronized void postTask(Task task,Manager manager) throws InterruptedException {
		task.setManager(manager);
		
		Queue<Task> specialtyList=this.workingBoard.get(task.getWorkerSpecialty().getSpecialty()); 	
		if (specialtyList == null) {
			specialtyList = new ConcurrentLinkedQueue<Task>();
			this.workingBoard.put(task.getWorkerSpecialty().getSpecialty(),specialtyList);
		}
		specialtyList.add(task);
		this.notifyAll();
	}

	/* (non-Javadoc)
	 * @see passiveObjects.WorkingBoard#removeTask(passiveObjects.Task)
	 */
	@Override
	public void removeTask(Task task) {
		Queue<Task> specialtyList=this.workingBoard.get(task.getWorkerSpecialty().getSpecialty()); 	
		if (specialtyList != null)
			specialtyList.remove(task);
	}

	@Override
	public boolean findTask(Task task) {
		Queue<Task> specialtyList=this.workingBoard.get(task.getWorkerSpecialty().getSpecialty()); 	
		if (specialtyList != null)
			return specialtyList.contains(task);
		return false;
	}

	@Override
	public synchronized void waitTillPostTask() {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
	}

}
