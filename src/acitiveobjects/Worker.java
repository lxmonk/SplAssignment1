/**
 * 
 */
package acitiveobjects;

import java.util.List;
import java.util.logging.Logger;

import passiveobjects.WorkerSpecialty;

/**
 * @author lxmonk
 *
 */
public class Worker implements Runnable {
	String name;
	
	public Worker(String name, int workHours, List<WorkerSpecialty> list, Logger logger) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * re
	 * 
	 * @return ldsakfjldsafjlkdfj
	 */
	public String getName(){
		return this.name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
