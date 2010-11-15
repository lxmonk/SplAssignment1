/**
 * 
 */
package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import passiveobjects.WorkerSpecialty;
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
		Logger logger = Logger.getLogger("TestingLog");
		Handler fh = null;
		try {
			fh = new FileHandler("my_log.log");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// logger output is written to a file in fh handler - my_log.log
		logger.addHandler(fh);

		// Set the log level specifying which message levels will be logged by
		// this logger
		logger.setLevel(Level.ALL); // TODO: update this.
		/* start the logger */
		logger.info("logger started.");
		/* create the Boards */
		ManagerBoard managerBoard = new ManagerBoardImpl();
		logger.info("ManagerBoardImpl created");
		WorkingBoard workingBoard = new WorkingBoardImpl();
		Warehouse warehouse = new WarehouseImpl();
		List<Project> completedProjects = new Vector<Project>();
		/* create temporary data structures */
		Set<ManagerSpecialization> managerSpecializations = new HashSet<ManagerSpecialization>();
		Set<WorkerSpecialty> workerSpecialties = new HashSet<WorkerSpecialty>();
		Set<Resource> resources = new HashSet<Resource>();
		Set<Project> projects = new HashSet<Project>();
		Map<ManagerSpecialization, ProjectBox> initMap = new HashMap<ManagerSpecialization, ProjectBox>();
		List<Manager> managers = new Vector<Manager>();
		List<Worker> workers = new Vector<Worker>();

		/* read configuration files */

		Properties configTxt = new Properties();
		Properties projectsTxt = new Properties();
		try {
			// loading file content into data structure prop
			configTxt.load(new FileInputStream(args[0]));
			projectsTxt.load(new FileInputStream(args[1]));
		} catch (IOException e) {
			logger.severe("properties file unreadable. exiting.");
			System.exit(-1);
		}

		// config.parse(args[?])
		Init.parse(configTxt, logger); // ???
		/* create the ProjectBoxes */
		logger.info("creating Project Boxes");
		int numOfManagerSpecializations = Integer.parseInt(configTxt
				.getProperty("numOfManagerSpecialities")); // Redundant??
		int numOfWorkerSpecialties = Integer.parseInt(configTxt
				.getProperty("numOfWorkerSpeciality")); // Redundant??
		String[] specializations = configTxt.getProperty("ManagerSpecialties")
				.split(",");
		String[] specialties = configTxt.getProperty("workerSpecialties")
				.split(",");
		/* create ManagerSpecializations and ProjectBoxes */
		for (String specialization : specializations) {
			ManagerSpecialization managerSpecialization = new ManagerSpecialization(
					specialization);
			managerSpecializations.add(managerSpecialization);
			ProjectBox projectBox = new ProjectBoxImpl(managerSpecialization);
			initMap.put(managerSpecialization, projectBox);
		}
		/* create the projects */

		/* create managers */
		int numOfMangers = Integer.parseInt(configTxt.getProperty("numOfMangers"));
		for (int i = 1; i <= numOfMangers; i++) {
			String si = String.valueOf(i);
			Manager manager = new Manager(configTxt.getProperty("manager" + si + "Name"), 
					new ManagerSpecialization(configTxt.getProperty("manager" + si + "Specialty")),
					managerBoard, workingBoard, completedProjects);
			managers.add(manager);
		}

		/* create workers */
		for (String specialty : specialties) {
			WorkerSpecialty workerSpecialty = new WorkerSpecialty(specialty);
			workerSpecialties.add(workerSpecialty);
		}
		int numOfWorkers = Integer.parseInt(configTxt
				.getProperty("numOfWorkers"));
		for (int i = 1; i <= numOfWorkers; i++) {
			String si = String.valueOf(i);
			Worker worker = new Worker(configTxt.getProperty(
					"worker" + si + "Name"), 
					Integer.parseInt(configTxt.getProperty("worker" + si + "WorkHours")), 
					Init.arr2spec(configTxt.getProperty(
							"worker" + si + "specialties").split(",")));
			workers.add(worker);
		}

		/* populate the boards */
		for (Resource resource : resources) {
			warehouse.addResource(resource);
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

	private static Vector<WorkerSpecialty> arr2spec(String[] arr) {
		Vector<WorkerSpecialty> vector = new Vector<WorkerSpecialty>();
		for (String specialty : arr) {
			vector.add(new WorkerSpecialty(specialty));
		}
		return vector;
	}

	private static void parse(Properties properties, Logger logger) {
		// TODO Auto-generated method stub

	}
}
