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

	private static final int TEN = 10;

	/**
	 * A static method returning a string of the current time, in HH:MM:SS
	 * format.
	 * 
	 * @return a string of the current time, in HH:MM:SS format.
	 */
	@SuppressWarnings("deprecation")
	public static String staticTimeNow() {
		Date date = new Date();
		String hr, min, sec;
		int hours = date.getHours(), minutes = date.getMinutes(), seconds = date
				.getSeconds();
		hr = String.valueOf(hours);
		if (hours < Helpers.TEN) hr = "0" + hr;
		min = String.valueOf(minutes);
		if (minutes < Helpers.TEN) min = "0" + min;
		sec = String.valueOf(seconds);
		if (seconds < Helpers.TEN) sec = "0" + sec;

		return hr + ":" + min + ":" + sec;
	}

	/**
	 * A method returning a string of the current time, in HH:MM:SS format.
	 * 
	 * @return a string of the current time, in HH:MM:SS format.
	 */
	public String timeNow() {
		Date date = new Date();
		return date.getHours() + ":" + date.getMinutes() + ":"
				+ date.getSeconds();
	}

}
