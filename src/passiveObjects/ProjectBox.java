/**
 * 
 */
package passiveObjects;

import java.util.Queue;

/**
 * @author lxmonk
 *
 */
public class ProjectBox {
	
	Queue<Project> projectQueue;
	ManagerSpecializtion specializtion;
	
	
	public synchronized Project getProject(){
		while (projectQueue.isEmpty()){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return projectQueue.poll();
	}
	
	public synchronized void addProject(Project project){
		projectQueue.add(project);
		this.notify(); // no reason to wake up everybody to handle 1 project.
	}

}
