/**
 * 
 */
package passiveobjects;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author lxmonk
 * 
 */
public class ProjectBoxImpl implements ProjectBox {

	Queue<Project> projectQueue;
	final ManagerSpecialization managerSpecialization;

	/**
	 * constructor
	 * 
	 * @param aManagerSpecialization
	 *            the {@link ManagerSpecialization} of this {@link ProjectBox}.
	 */
	public ProjectBoxImpl(ManagerSpecialization aManagerSpecialization) {
		this.projectQueue = new ConcurrentLinkedQueue<Project>();
		this.managerSpecialization = aManagerSpecialization;
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
			this.projectQueue.add(project);
			this.notify(); // no reason to wake up everybody to handle 1 project.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveobjects.ProjectBox#getProject()
	 */
	@Override
	public synchronized Project getProject() throws InterruptedException{
		while (this.projectQueue.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException ie) {
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

	@Override
	public Collection<Project> getAllProjects() {
		Collection<Project> col = new Vector<Project>();
		for (Object project : this.projectQueue.toArray()){
			col.add((Project) project);
		}
		return col;
	}

}
