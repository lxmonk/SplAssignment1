/**
 * 
 */
package passiveobjects;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author lxmonk
 * 
 */
public class ProjectBoxImpl implements ProjectBox {

	Queue<Project> projectQueue;
	ManagerSpecialization managerSpecialization;
	Object Lock;

	/**
	 * constructor
	 * 
	 * @param aManagerSpecialization
	 *            the {@link ManagerSpecialization} of this {@link ProjectBox}.
	 */
	public ProjectBoxImpl(ManagerSpecialization aManagerSpecialization) {
		this.projectQueue = new ConcurrentLinkedQueue<Project>();
		this.managerSpecialization = aManagerSpecialization;
		this.Lock = new Object();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveobjects.ProjectBox#addProject(passiveobjects.Project)
	 */
	@Override
	public synchronized void addProject(Project project)
			throws RuntimeException {
		if (!project.getNextManagerSpecializtion().equals(
				this.managerSpecialization)) {
			throw new RuntimeException("the next manager in project "
					+ project.getName()
					+ " did not match the one in ProjectBox "
					+ this.managerSpecialization);
		}
		synchronized (Lock) {
			this.projectQueue.add(project);
			this.notify(); // no reason to wake up everybody to handle 1 project.
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveobjects.ProjectBox#getProject()
	 */
	@Override
	public synchronized Project getProject() {
		while (this.projectQueue.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				break;
			}
		}
		return this.projectQueue.poll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveobjects.ProjectBox#removeProject(passiveobjects.Project)
	 */
	@Override
	public void removeProject(Project project) throws RuntimeException {
		this.projectQueue.remove(project);
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * passiveobjects.ProjectBox#getManagerSpecializtion(passiveobjects.Project)
	 */
	@Override
	public ManagerSpecialization getManagerSpecializtion() {
		return this.managerSpecialization;
	}

}
