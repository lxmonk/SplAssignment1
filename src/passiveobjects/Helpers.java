/**
 * 
 */
package passiveobjects;

import java.util.Date;

/**
 * @author lxmonk
 * 
 */
public class Helpers {

	/**
	 * A static method returning a string of the current time, in HH:MM:SS
	 * format.
	 * 
	 * @return a string of the current time, in HH:MM:SS format.
	 */
	public static String staticTimeNow() {
		Date date = new Date();
		return date.getHours() + ":" + date.getMinutes() + ":"
				+ date.getSeconds();
	}

	/**
	 * A method returning a string of the current time, in HH:MM:SS
	 * format.
	 * 
	 * @return a string of the current time, in HH:MM:SS format.
	 */
	public String timeNow() {
		Date date = new Date();
		return date.getHours() + ":" + date.getMinutes() + ":"
				+ date.getSeconds();
	}

}
