/**
 * 
 */
package repl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

import passiveobjects.Helpers;
import passiveobjects.ManagerBoard;
import passiveobjects.Project;
import passiveobjects.ProjectBox;
import passiveobjects.ProjectImpl;
import passiveobjects.Resource;
import passiveobjects.Task;
import passiveobjects.Warehouse;
import passiveobjects.WorkerSpecialty;
import passiveobjects.WorkingBoard;
import acitiveobjects.Worker;

/**
 * @author lxmonk
 * 
 */
public class Repl {
	static private Set<String> commands;
	static Map<Project, Project> executingProjects;
	static ManagerBoard managerBoard;
	static List<Project> completedProjectsList;
	static Map<String, ProjectImpl> projects;
	static WorkingBoard workingBoard;
	static Logger logger;
	static Map<String, Worker> workers;
	private static List<Worker> workersList;
	private static Warehouse warehouse;
	private static ExecutorService workersExecutorService;
	private static ExecutorService managersExecutorService;
	static final Scanner SC = new Scanner(System.in);

	/**
	 * constructor
	 * 
	 * @param executingProjectsRef
	 *            a reference to the executing {@link Project}s data structure.
	 * @param theManagerBoard
	 *            the {@link ManagerBoard}
	 * @param theCompletedProjectsList
	 *            the {@link List} of completed {@link Project}s
	 * @param projectsMap
	 *            the set of {@link Project}s.
	 * @param aWarehouse
	 *            a {@link Warehouse}.
	 */
	public Repl(Map<Project, Project> executingProjectsRef,
			ManagerBoard theManagerBoard,
			List<Project> theCompletedProjectsList,
			Map<String, ProjectImpl> projectsMap, Warehouse aWarehouse) {
		Repl.executingProjects = executingProjectsRef;
		Repl.commands = new HashSet<String>();
		Repl.completedProjectsList = theCompletedProjectsList;
		Repl.projects = projectsMap;
		Repl.warehouse = aWarehouse;
		for (String s : new String[] { "currentProjects", "pendingProjects",
				"completedProjects", "abortProjcet", "project", "workers",
				"worker", "addWorker", "departmentManager",
				"addDepartmentManager", "stop", "help" }) {
			Repl.commands.add(s);
		}
		Repl.managerBoard = theManagerBoard;

	}

	/**
	 * set the Logger to aLoger
	 * 
	 * @param aLogger
	 *            a {@link Logger}
	 */
	public void setLogger(Logger aLogger) {
		Repl.logger = aLogger;
	}

	/**
	 * set the workers by refi-ing to the workers {@link List}.
	 * 
	 * @param aWorkersList
	 *            the {@link List} of workers.
	 */
	public void setWorkers(List<Worker> aWorkersList) {
		Repl.workersList = aWorkersList;
		Repl.workers = new HashMap<String, Worker>();
		for (Worker worker : aWorkersList) {
			Repl.workers.put(worker.getName(), worker);
		}
	}

	/**
	 * @param theWorkersExecutorService the workersExecutorService to set
	 */
	public void setWorkersExecutorService(
			ExecutorService theWorkersExecutorService) {
		Repl.workersExecutorService = theWorkersExecutorService;
	}

	/**
	 * @param theManagersExecutorService the managersExecutorService to set
	 */
	public void setManagersExecutorService(
			ExecutorService theManagersExecutorService) {
		Repl.managersExecutorService = theManagersExecutorService;
	}

	/**
	 * main method to start the repl.
	 */
	public void start() {
		System.out.println("This is the observer REPL.");
		Repl.nextCommand(Repl.commands, Repl.SC);
	}

	private static void nextCommand(Set<String> commandsSet, Scanner sc) {
		System.out.println("What would you like to do?");
		String in = sc.next();
		if (commandsSet.contains(in)) {
			if (in.equals("currentProjects")) {
				Repl.currentProjects(Repl.vec(sc));
			} else if (in.equals("pendingProjects")) {
				Repl.pendingProjects(Repl.vec(sc));
			} else if (in.equals("completedProjects")) {
				Repl.completedProjects(Repl.vec(sc));
			} else if (in.equals("abortProjcet")) {
				Repl.abortProjcet(Repl.vec(sc));
			} else if (in.equals("project")) {
				Repl.project(Repl.vec(sc));
			} else if (in.equals("workers")) {
				Repl.workers(Repl.vec(sc));
			} else if (in.equals("worker")) {
				Repl.worker(Repl.vec(sc));
			} else if (in.equals("addWorker")) {
				Repl.addWorker(Repl.vec(sc));
			} else if (in.equals("departmentManager")) {
				Repl.departmentManager(Repl.vec(sc));
			} else if (in.equals("addDepartmentManager")) {
				Repl.addDepartmentManager(Repl.vec(sc));
			} else if (in.equals("stop")) {
				Repl.stop();
			} else if (in.equals("help")) {
				Repl.help();
			}
		} else {
			System.out.println("USAGE: command \"" + in
					+ "\" is not a valid command. (the observer is Case "
					+ "sensetive)\n" + "type 'help' to see all "
					+ "available commands.");
		}
		Repl.nextCommand(commandsSet, sc);
	}

	private static void help() {
		System.out.println("the available commands are:");
		System.out.println("currentProjects, pendingProjects,"
				+ "completedProjects, abortProjcet, project, workers,"
				+ "worker, addWorker, departmentManager,"
				+ "addDepartmentManager, stop, help");

	}

	private static void stop() {
		// TODO Auto-generated method stub
		return;

	}

	private static void addDepartmentManager(Vector<String> vec) {
		// String s0 = vec.elementAt(0), s1 = vec.elementAt(1), s2 =
		// vec.elementAt(2);
		// System.out.println("0,1,2 = " + s0 + "," + s1 + "" + s2);
		// TODO Auto-generated method stub
		System.out.println("addDepartmentManager");
	}

	private static void departmentManager(Vector<String> vec) {
		// TODO Auto-generated method stub

	}

	private static void addWorker(Vector<String> vec) {
		if (vec.size() != (1 + 2)) {
			System.out.println("USAGE: 'addWorker' takes exactly 3 arguments. "
					+ vec.size() + " given.");
		} else {
			String name = vec.elementAt(0);
			String types = vec.elementAt(1);
			List<WorkerSpecialty> specs = new ArrayList<WorkerSpecialty>();
			for (String s : types.replaceAll(" ", "").split(",")) {
				specs.add(new WorkerSpecialty(s));
			}
			int workHours = Integer.parseInt(vec.elementAt(2));
			Worker worker = new Worker(name, workHours, specs,
					Repl.workingBoard, Repl.warehouse);
			worker.setLogger(Repl.logger);
			Repl.workersList.add(worker);
			Repl.workers.put(worker.getName(), worker);
			Repl.logger.info(worker.getName() + "started working at "
					+ Helpers.staticTimeNow());
			Repl.workersExecutorService.execute(worker);
		}
		Repl.nextCommand(Repl.commands, Repl.SC);
	}

	private static void worker(Vector<String> vec) {
		if (vec.size() != 1) {
			System.out.println("USAGE: 'worker' takes exactly 1 argument. "
					+ vec.size() + " given.");
		} else {
			if (!Repl.workers.containsKey(vec.elementAt(0))) {
				System.out
						.println(vec.elementAt(0) + " is not a valid worker!");
			} else {
				Worker worker = Repl.workers.get(vec.elementAt(0));
				String status;
				if (worker.getCurrentTask() == null)
					status = "looking for a task";
				else
					status = "working on task: "
							+ worker.getCurrentTask().getName();
				List<String> resources = worker.getWorkerResources();
				String ress = "";
				if (resources != null) {
					for (String s : resources) {
						ress += s + " ";
					}
				}
				if (ress == "")
					ress = "none";
				System.out.println("Current Status: " + status + "\n"
						+ "Resources: " + ress);
			}
		}
	}

	private static void workers(Vector<String> vec) {
		int vecSize = vec.size();
		if (vecSize != 0) {
			System.out.println("USAGE: 'workers' takes exactly 0 arguments. "
					+ vecSize + " given.");
		} else {
			System.out.println("here");// FIXME: DLETE!
			System.out.println("Workers: "
					+ Repl.workerArr2Str(Repl.workers.values()));
		}
	}

	private static void project(Vector<String> vec) {
		if (!(vec.size() == 1)) {
			System.out.println("USAGE: 'project' takes exactly 1 argument. "
					+ vec.size() + " given.");
		} else {
			if (!Repl.projects.containsKey(vec.elementAt(0))) {
				System.out.println(vec.elementAt(0)
						+ " is not a valid project!");
			} else {
				ProjectImpl project = Repl.projects.get(vec.elementAt(0));
				List<Task> completed = project.getCompletedTasks();
				System.out.println("Current Task: "
						+ project.getNextTask().getName()
						+ "\nCompleted Tasks:");
				for (Task t : completed) {
					System.out.print(t.getName() + ": ");
					System.out.println("\tpublisher: " + t.getManagerName());
					System.out.println("\tworkers: ");
					for (Worker w : t.getWorkers()) {
						System.out.print(w.getName() + " ");
					}
					System.out.println("\n\tresources used: ");
					for (Resource r : t.getNeededResources()) {
						System.out.print(r.getName() + " ");
					}
					System.out.println("\n\twork neede: " + t.getSize()
							+ " hours.");
				}
			}
		}
		Repl.nextCommand(Repl.commands, Repl.SC);
	}

	private static void abortProjcet(Vector<String> vec) {
		// TODO Auto-generated method stub

	}

	private static void completedProjects(Vector<String> vec) {
		if (!(vec.size() == 0)) {
			System.out
					.println("USAGE: 'completedProjects' takes exactly 0 arguments. "
							+ vec.size() + " given.");
		} else {
			for (Project project : Repl.completedProjectsList) {
				System.out.println("Project: " + project.getName() + ".\n"
						+ "Hours worked: " + project.getTotalHours() + ".\n"
						+ "Completed Tasks: "
						+ Repl.taskArr2Str(project.getCompletedTasks()) + "\n"
						+ "Last Manager was: " + project.getManagerName());
			}
		}
		Repl.nextCommand(Repl.commands, Repl.SC);

	}

	private static void pendingProjects(Vector<String> vec) {
		if (!(vec.size() == 0)) {
			System.out
					.println("USAGE: 'currentProjects' takes exactly 0 arguments. "
							+ vec.size() + " given.");
		} else {
			for (ProjectBox projectBox : Repl.managerBoard.getPendingProjects()
					.values()) {
				for (Project project : projectBox.getAllProjects()) {
					System.out.println("Project: " + project.getName() + ".\n"
							+ "Completed Tasks: "
							+ Repl.taskArr2Str(project.getCompletedTasks())
							+ "\n" + "Next Task: "
							+ project.getNextTask().getName());
				}
				Repl.nextCommand(Repl.commands, Repl.SC);
			}
		}

	}

	private static void currentProjects(Vector<String> vec) {

		if (!(vec.size() == 0)) {
			System.out
					.println("USAGE: 'currentProjects' takes exactly 0 arguments. "
							+ vec.size() + " given.");
		} else {
			for (Project project : Repl.executingProjects.values()) {
				System.out.println("Project: "
						+ project.getName()
						+ ".\n"
						+ "Manager: "
						+ project.getManagerName()
						+ ".\n"
						+ "Completed Tasks: "
						+ Repl.taskArr2Str(project.getCompletedTasks())
						+ "\n"
						+ "Current Task has "
						+ +project.getNextTask().getHoursDone()
						+ "hours done.\n"
						+ "Current Workers are: "
						+ Repl
								.workerArr2Str(project.getNextTask()
										.getWorkers()) + ".");
			}
		}
		Repl.nextCommand(Repl.commands, Repl.SC);
	}

	private static String workerArr2Str(Collection<Worker> collection) {
		if (collection.size() == 0) {
			return "None.";
		} else {
			String ret = "";
			for (Worker worker : collection) {
				ret += worker.getName() + ", ";
			}
			return ret.substring(0, ret.length() - 2); // remove the last comma
			// and space (", ").
		}
	}

	private static String taskArr2Str(List<Task> completedTasks) {
		if (completedTasks.size() == 0) {
			return "None.";
		} else {
			String ret = "";
			for (Task task : completedTasks) {
				ret += task.getName() + ", ";
			}
			return ret.substring(0, ret.length() - 1); // remove the last comma
			// (,)
		}
	}

	private static Vector<String> vec(Scanner sc) {
		String input = sc.nextLine();
		Vector<String> ret = new Vector<String>();
		if (input.length() == 0)
			return ret;
		for (String s : input.substring(1).split(" "))
			ret.add(s);
		return ret;
	}

}
