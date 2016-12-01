package production;


/**
 * 
 * @author Heather Kemp
 *
 */

public interface Tick {
	
	/**
	 * Perform the action at the specified time passed in.
	 * @input - a int representing the current time
	 */
	
	void performAction(int tick);

}
