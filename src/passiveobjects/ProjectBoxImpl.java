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
public class ProjectBoxImpl implements ProjectBox {

	Queue<Project> projectQueue;
	ManagerSpecializtion managerSpecializtion;

	public ProjectBoxImpl(ManagerSpecializtion managerSpecializtion) {
		projectQueue = new LinkedList<Project>();
		this.managerSpecializtion = managerSpecializtion;
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
				this.managerSpecializtion)) {
			throw new RuntimeException("the next manager in project "
					+ project.getName()
					+ " did not match the one in ProjectBox "
					+ this.managerSpecializtion.specialization);
		}
		projectQueue.add(project);
		this.notify(); // no reason to wake up everybody to handle 1 project.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveobjects.ProjectBox#getProject()
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveobjects.ProjectBox#removeProject(passiveobjects.Project)
	 */
	@Override
	public void removeProject(Project project) throws RuntimeException {
		// TODO Auto-generated method stub

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see passiveobjects.ProjectBox#getManagerSpecializtion(passiveobjects.Project)
	 */
	@Override
	public ManagerSpecializtion getManagerSpecializtion() {
		// TODO Auto-generated method stub
		return null;
	}

}
