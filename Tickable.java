package production;


/**
 * 
 * @author Heather Kemp
 * Operates like a clock so that each part of the warehouse can perform an action at the specific time given.
 *
 */

public interface Tickable {
	
	/**
	 * Perform the action at the specified time passed in.
	 * @input - a int representing the current time
	 */
	
	void performAction(int tick);

}
