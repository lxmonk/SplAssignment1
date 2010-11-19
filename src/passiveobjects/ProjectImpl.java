/**
 * 
 */
package passiveobjects;

import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

import acitiveobjects.Manager;

/**
 * @author lxmonk
 *
 */
public class ProjectImpl implements Project {
	String name;
	Queue<Task> tasks;
	List<Task> completedTasks;
	Manager lastManager; //should there be also current manager?
	int totalHours;
	
	
	/**
	 * constructor
	 * @param aName {@link Project} name.
	 * @param initialTaskList the initial tasks
	 */
	public ProjectImpl(String aName, List<Task> initialTaskList){
		this.name = aName;
		this.tasks = new ConcurrentLinkedQueue<Task>();
		for (Task task : initialTaskList){
			this.tasks.add(task);
		}
		this.completedTasks = new Vector<Task>();
		this.lastManager = null;
		this.totalHours = 0;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#addTask(passiveObjects.Task)
	 */
	@Override
	public void addTask(Task task) {
		this.tasks.add(task);
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#addTasks(java.util.List)
	 */
	@Override
	public void addTasks(List<Task> taskList) {
		for (Task task : taskList){
			this.tasks.add(task);
		}
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getCompletedTasks()
	 */
	@Override
	public List<Task> getCompletedTasks() {
		return this.completedTasks;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getManager()
	 */
	@Override
	public Manager getManager() {
		return this.lastManager;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getManagerName()
	 */
	@Override
	public String getManagerName() {
		return this.getManager().getName();
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getNextManagerSpecializtion()
	 */
	@Override
	public ManagerSpecialization getNextManagerSpecializtion() {
		try {
			return this.getNextTask().getManagerSpecializtion();
		} catch (Exception e) { // no next ManagerSpecialization
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getNextTask()
	 */
	@Override
	public Task getNextTask() {
		return this.tasks.peek();
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getTotalHours()
	 */
	@Override
	public int getTotalHours() {
		return this.totalHours;
	}

	@Override
	public void removeTask(Task task) {
		this.tasks.poll();
	}

	@Override
	public void updateTotalHours(int hours) {
		this.totalHours += hours;
	}
	@Override
	public void abortProject(){
		this.getManager().interrupt();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		ProjectImpl other = (ProjectImpl) obj;
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public void setManager(Manager manager) {
		this.lastManager = manager;
		
	}

}
