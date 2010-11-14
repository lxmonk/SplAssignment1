/**
 * 
 */
package passiveobjects;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lxmonk
 * 
 */
public class ProjectBox {

	Queue<Project> projectQueue;
	ManagerSpecializtion managerSpecializtion;

	public ProjectBox(ManagerSpecializtion managerSpecializtion) {
		projectQueue = new LinkedList<Project>();
		this.managerSpecializtion = managerSpecializtion;
	}

	public synchronized Project getProject() {
		while (projectQueue.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return projectQueue.poll();
	}

	public synchronized void addProject(Project project)
			throws RuntimeException {
		if (!project.getNextManagerSpecializtion().equals(
				this.managerSpecializtion)) {
			throw new RuntimeException("the next manager in project "
					+ project.getName()
					+ " did not match the one in ProjectBox "
					+ this.managerSpecializtion.specialization);
		}
		projectQueue.add(project);
		this.notify(); // no reason to wake up everybody to handle 1 project.
	}

}
