/**
 * 
 */
package acitiveobjects;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import passiveobjects.Helpers;
import passiveobjects.ManagerBoard;
import passiveobjects.ManagerSpecialization;
import passiveobjects.Project;
import passiveobjects.ProjectBox;
import passiveobjects.Task;
import passiveobjects.WorkingBoard;

/**
 * @author lxmonk
 * 
 */
public class Manager implements Runnable {

	String name;
	ManagerSpecialization managerSpecializtion;
	ManagerBoard managerBoard;
	WorkingBoard workingBoard;
	List<Project> completedProjects;
	Map<Project, Project> executingProjects;
	ProjectBox projectBox;
	Project currentProject;
	Logger logger;

	/**
	 * constructor for DepartmentManager.
	 * 
	 * @param aName
	 *            manager's name
	 * @param aManagerSpecialization
	 *            manager's specialization
	 * @param theManagerBoard
	 *            the {@link ManagerBoard}
	 * @param completedProjectsList
	 *            the {@link List} of completed projects
	 * @param executingProjectsRef
	 *            a reference to the set (Map) of projects being executed.
	 */
	public Manager(String aName, ManagerSpecialization aManagerSpecialization,
			ManagerBoard theManagerBoard, List<Project> completedProjectsList,
			Map<Project, Project> executingProjectsRef) {
		this.name = aName;
		this.managerSpecializtion = aManagerSpecialization;
		this.managerBoard = theManagerBoard;
		this.completedProjects = completedProjectsList;
		this.executingProjects = executingProjectsRef;
		this.currentProject = null;

	}

	/**
	 * Set the logger for this manager
	 * 
	 * @param aLogger
	 *            a {@link Logger}
	 */
	public void setLogger(Logger aLogger) {
		this.logger = aLogger;

	}

	/**
	 * Set the {@link WorkingBoard} for this {@link Manager}
	 * 
	 * @param theWorkingBoard
	 *            the {@link WorkingBoard}
	 */
	public void setWorkingBoard(WorkingBoard theWorkingBoard) {
		this.workingBoard = theWorkingBoard;

	}

	/**
	 * return the name of this {@link Manager}.
	 * 
	 * @return the name field of this {@link Manager}
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * get the {@link ManagerSpecialization}
	 * 
	 * @return the {@link ManagerSpecialization}
	 */
	public ManagerSpecialization getSpecializtion() {
		return this.managerSpecializtion;
	}

	@Override
	public void run() {
		this.projectBox = this.managerBoard.getProjectBox(this
				.getSpecializtion());
		while (!Thread.interrupted()) {
			try {
				this.currentProject = this.projectBox.getProject();
				Task currentTask = this.currentProject.getNextTask();
				this.currentProject.setManager(this);
				this.executingProjects.put(this.currentProject,
						this.currentProject);
				this.logger.info(this.name + " publishes task "
						+ currentTask.getName() + " of project "
						+ this.currentProject.getName() + " at "
						+ Helpers.staticTimeNow());
				this.workingBoard.postTask(currentTask);
				// will wait until completion - according to postTask method.
				this.workingBoard.removeTask(currentTask);
				this.logger.info(this.name + " completed task "
						+ currentTask.getName() + " of project "
						+ this.currentProject.getName() + " at "
						+ Helpers.staticTimeNow());
				this.executingProjects.remove(this.currentProject);
				this.currentProject.removeTask(currentTask);
				this.currentProject.updateTotalHours(currentTask.getSize());
				this.currentProject.getCompletedTasks().add(currentTask);
				Task nextTask = this.currentProject.getNextTask();
				ManagerSpecialization nextManagerSpecialization = null;
				if (nextTask == null) { // the project is done
					this.logger.info(this.name + " completed project "
							+ this.currentProject.getName() + " at "
							+ Helpers.staticTimeNow());
					this.completedProjects.add(this.currentProject);
				} else { // the project is not done yet
					nextManagerSpecialization = nextTask
							.getManagerSpecializtion();
				ProjectBox retrunProjectBox = this.managerBoard
						.getProjectBox(nextManagerSpecialization);
				retrunProjectBox.addProject(this.currentProject);
				}
			} catch (Exception ie) {
				if (ie instanceof InterruptedException) {
					//TODO: implement!
				}
					
				}
		}


			
	}
	
			// try {
			// this.currentProject = this.projectBox.getProject();
			// Task currentTask = this.currentProject.getNextTask();
			// this.currentProject.setManager(this);
			// this.currentProject.removeTask(currentTask);
			// this.logger.info(this.name + " publishes task "
			// + currentTask.getName() + " of project "
			// + this.currentProject.getName() + " at "
			// + Helpers.staticTimeNow());
			// this.workingBoard.postTask(currentTask);
			// // will wait until completion - according to postTask method.
			// this.workingBoard.removeTask(currentTask);
			// this.logger.info(this.name + " completed task "
			// + currentTask.getName() + " of project "
			// + this.currentProject.getName() + " at "
			// + Helpers.staticTimeNow());
			// this.currentProject.updateTotalHours(currentTask.getSize());
			// this.currentProject.getCompletedTasks().add(currentTask);
			// Task nextTask = this.currentProject.getNextTask();
			// ManagerSpecialization nextManagerSpecialization = null;
			// if (nextTask == null) { // the project is done
			// this.logger.info(this.name + " completed project "
			// + this.currentProject.getName() + " at "
			// + Helpers.staticTimeNow());
			// this.completedProjects.add(this.currentProject);
			// break;
			// } else { // the project is not done yet
			// nextManagerSpecialization = nextTask
			// .getManagerSpecializtion();
			// }
			// ProjectBox retrunProjectBox = this.managerBoard
			// .getProjectBox(nextManagerSpecialization);
			// retrunProjectBox.addProject(this.currentProject);
			// } catch (InterruptedException ie) {
			// // TODO: handle exception
			// ie.printStackTrace();
			// }
	
	

	/**
	 * interrupt the manager (a project is aborted, for example).
	 */
	public void interrupt() {
		// Thread
		Thread.currentThread().interrupt();
	}
}
