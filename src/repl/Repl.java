/**
 * 
 */
package repl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import passiveobjects.ManagerBoard;
import passiveobjects.Project;
import passiveobjects.ProjectBox;
import passiveobjects.Task;
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
	static WorkingBoard workingBoard;
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
	 * @param theWorkingBoard
	 *            the {@link WorkingBoard}
	 */
	public Repl(Map<Project, Project> executingProjectsRef,
			ManagerBoard theManagerBoard,
			List<Project> theCompletedProjectsList, WorkingBoard theWorkingBoard) {
		Repl.executingProjects = executingProjectsRef;
		Repl.commands = new HashSet<String>();
		Repl.completedProjectsList = theCompletedProjectsList;
		for (String s : new String[] { "currentProjects", "pendingProjects",
				"completedProjects", "abortProjcet", "project", "workers",
				"worker", "addWorker", "departmentManager",
				"addDepartmentManager", "stop", "help" }) {
			Repl.commands.add(s);
		}
		Repl.managerBoard = theManagerBoard;
		Repl.workingBoard = theWorkingBoard;
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
		// TODO Auto-generated method stub
		System.out.println("addDepartmentManager");
	}

	private static void departmentManager(Vector<String> vec) {
		// TODO Auto-generated method stub

	}

	private static void addWorker(Vector<String> vec) {
		// TODO Auto-generated method stub

	}

	private static void worker(Vector<String> vec) {
		// TODO Auto-generated method stub

	}

	private static void workers(Vector<String> vec) {
		// TODO Auto-generated method stub

	}

	private static void project(Vector<String> vec) {
		// TODO Auto-generated method stub

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
			for (Project project : completedProjectsList) {
				System.out.println("Project: "
						+ project.getName()
						+ ".\n"
						+ "Hours worked: "
						+ project.getTotalHours()
						+ ".\n"
						+ "Completed Tasks: "
						+ Repl.taskArr2Str(project.getCompletedTasks())
						+ "\n"
						+ "Last Manager was: " + project.getManagerName());
			}
		}

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

	private static String workerArr2Str(List<Worker> workers) {
		if (workers.size() == 0) {
			return "None.";
		} else {
			String ret = "";
			for (Worker worker : workers) {
				ret += worker.getName() + ", ";
			}
			return ret.substring(0, ret.length() - 1); // remove the last comma
			// (,)
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
		Vector<String> ret = new Vector<String>();
		while (sc.hasNext()) {
			ret.add(sc.next());
		}
		return ret;
	}
}
