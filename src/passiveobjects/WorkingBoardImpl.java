/**
 * 
 */
package passiveobjects;

/**
 * @author lxmonk
 *
 */
public class WorkingBoardImpl implements WorkingBoard {
	
	Object newMonitor;

	/* (non-Javadoc)
	 * @see passiveObjects.WorkingBoard#getTaskBySpecialty(passiveObjects.WorkerSpecialty)
	 */
	@Override
	public Task getTaskBySpecialty(WorkerSpecialty specialty) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.WorkingBoard#postTask(passiveObjects.Task)
	 */
	@Override
	public void postTask(Task task) {
		// TODO Auto-generated method stub
		this.newMonitor.notifyAll(); // wakes all the workers who didn't find tasks
	}

	/* (non-Javadoc)
	 * @see passiveObjects.WorkingBoard#removeTask(passiveObjects.Task)
	 */
	@Override
	public void removeTask(Task task) {
		// TODO Auto-generated method stub

	}

	@Override
	public Task findTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized Object getNewMonitor() {
		return newMonitor;
	}

}
