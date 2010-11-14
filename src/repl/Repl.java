/**
 * 
 */
package repl;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

/**
 * @author lxmonk
 * 
 */
public class Repl {
	// final Set<String> commands;
	//
	// public Repl() {
	// commands = new HashSet<String>();
	// for (String s : new String[] { "currentProjects", "pendingProjects",
	// "completedProjects", "abortProjcet", "project", "workers",
	// "worker", "addWorker", "departmentManager",
	// "addDepartmentManager", "stop"}) {
	// commands.add(s);
	// }
	// }

	/**
	 * main method
	 * @param args String array of arguments.
	 */
	public static void main(String[] args) {
		Set<String> commands = new HashSet<String>();
		for (String s : new String[] { "currentProjects", "pendingProjects",
				"completedProjects", "abortProjcet", "project", "workers",
				"worker", "addWorker", "departmentManager",
				"addDepartmentManager", "stop", "help" }) {
			commands.add(s);
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("This is the observer REPL.");
		Repl.nextCommand(commands, sc);
	}

	private static void nextCommand(Set<String> commands, Scanner sc) {
		System.out.println("What would you like to do?");
		String in = sc.next();
		if (commands.contains(in)) {
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
		Repl.nextCommand(commands, sc);
	}

	private static void help() {
		System.out.println("help");

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
		// TODO Auto-generated method stub

	}

	private static void pendingProjects(Vector<String> arr) {
		// TODO Auto-generated method stub

	}

	private static void currentProjects(Vector<String> strVec) {
		// TODO Auto-generated method stub

	}

	private static Vector<String> vec(Scanner sc) {
		Vector<String> ret = new Vector<String>();
		while (sc.hasNext()) {
			ret.add(sc.next());
		}
		return ret;
	}
}
