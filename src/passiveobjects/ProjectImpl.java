/**
 * 
 */
package passiveobjects;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	long totalHours;
	
	/**
	 * constructor
	 * @param aName {@link Project} name.
	 * @param initialTaskList the initial tasks
	 */
	public ProjectImpl(String aName, List<Task> initialTaskList){
		this.name = aName;
		this.tasks = new LinkedList<Task>();
		for (Task task : initialTaskList){
			this.tasks.add(task);
		}
		completedTasks = new LinkedList<Task>();
		lastManager = null;
		totalHours = 0;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#addTask(passiveObjects.Task)
	 */
	@Override
	public void addTask(Task task) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#addTasks(java.util.List)
	 */
	@Override
	public void addTasks(List<Task> taskList) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getCompletedTasks()
	 */
	@Override
	public List<Task> getCompletedTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getManager()
	 */
	@Override
	public Manager getManager() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getManagerName()
	 */
	@Override
	public String getManagerName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getNextManagerSpecializtion()
	 */
	@Override
	public ManagerSpecialization getNextManagerSpecializtion() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getNextTask()
	 */
	@Override
	public Task getNextTask() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see passiveObjects.Project#getTotalHours()
	 */
	@Override
	public long getTotalHours() {
		// TODO Auto-generated method stub
		return 0;
	}

}
