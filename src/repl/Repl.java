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
	 * @param args
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
		nextCommand(commands, sc);
		System.out.println("BYE");

	}

	private static void nextCommand(Set<String> commands, Scanner sc) {
		System.out.println("What would you like to do?");
		String in = sc.next();
		if (commands.contains(in)) {
			if (in.equals("currentProjects")) {
				currentProjects(vec(sc));
			} else if (in.equals("pendingProjects")) {
				pendingProjects(vec(sc));
			} else if (in.equals("completedProjects")) {
				completedProjects(vec(sc));
			} else if (in.equals("abortProjcet")) {
				abortProjcet(vec(sc));
			} else if (in.equals("project")) {
				project(vec(sc));
			} else if (in.equals("workers")) {
				workers(vec(sc));
			} else if (in.equals("worker")) {
				worker(vec(sc));
			} else if (in.equals("addWorker")) {
				addWorker(vec(sc));
			} else if (in.equals("departmentManager")) {
				departmentManager(vec(sc));
			} else if (in.equals("addDepartmentManager")) {
				addDepartmentManager(vec(sc));
			} else if (in.equals("stop")) {
				/*stop();*/
				return;
			} else if (in.equals("help")) {
				help();
			}
		} else {
			System.out.println("USAGE: command \"" + in
					+ "\" is not a valid command. (the observer is Case "
					+ "sensetive)\n" + "type 'help' to see all "
					+ "available commands.");
		}
		nextCommand(commands, sc);
	}

	private static void help() {
		System.out.println();

	}

	private static void stop() {
		// TODO Auto-generated method stub
		return;

	}

	private static void addDepartmentManager(Vector<String> vec) {
		// TODO Auto-generated method stub

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
