/**
 * 
 */
package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import acitiveobjects.Manager;
import acitiveobjects.Observer;
import acitiveobjects.Worker;

import passiveobjects.ManagerBoard;
import passiveobjects.ManagerBoardImpl;
import passiveobjects.ManagerSpecialization;
import passiveobjects.Project;
import passiveobjects.ProjectBox;
import passiveobjects.ProjectBoxImpl;
import passiveobjects.ProjectImpl;
import passiveobjects.Resource;
import passiveobjects.Warehouse;
import passiveobjects.WarehouseImpl;
import passiveobjects.WorkingBoard;
import passiveobjects.WorkingBoardImpl;

/**
 * @author lxmonk
 * 
 */
public class Init {

	/**
	 * @param args
	 *            the invocation arguments.
	 */
	public static void main(String[] args) {
		/* create the logger */
		/* start the logger */
		/* create the Boards */
		ManagerBoard managerBoard = new ManagerBoardImpl();
		WorkingBoard workingBoard = new WorkingBoardImpl();
		Warehouse warehouse = new WarehouseImpl();
		List<ProjectImpl> completedProjects = new CopyOnWriteArrayList<ProjectImpl>();
		/* create temporary data structures */
		Set<ManagerSpecialization> specializations = new HashSet<ManagerSpecialization>();
		Set<Resource> resources = new HashSet<Resource>();
		Set<Project> projects = new HashSet<Project>();
		Map<ManagerSpecialization, ProjectBox> initMap = new HashMap<ManagerSpecialization, ProjectBox>();
		List<Manager> managers = new Vector<Manager>();
		List<Worker> workers = new Vector<Worker>();

		/* read configuration files */
		// config.parse(args[?])
		/* create the projects */

		/* create workers */
		/* create managers */

		/* populate the boards */
		for (Resource resource : resources) {
			warehouse.addResource(resource);
		}
		/* populate specializations - create ProjectBoxes */
		for (ManagerSpecialization managerSpecialization : specializations) {
			initMap.put(managerSpecialization, new ProjectBoxImpl(
					managerSpecialization));
		}
		managerBoard.createProjectsMap(initMap);
		for (Project project : projects) {
			managerBoard.getProjectBox(project.getNextManagerSpecializtion())
					.addProject(project);
		}
		/* create the observer */
		Observer observer = new Observer();

		ExecutorService mangersExecutorService = Executors
				.newCachedThreadPool();
		ExecutorService workersExecutorService = Executors
				.newCachedThreadPool();
		
		observer.start();
		
		for (Worker worker : workers)
			workersExecutorService.execute(worker);
		
		for (Manager manager : managers)
			mangersExecutorService.execute(manager);
		
	}
}
