package production;

import java.util.PriorityQueue;

/**
* @author Rachel Schneberger
*
* This class has the functionality for a single a robot. 
* There can be multiple robots in use at the same time. 
*/

public class Robot implements Event, robotPath{
	
	private int robotId;
	private int robotCharge;
	private boolean shelf = false;
	public boolean usable = true;
	private int xCord;
	private int yCord;
	private PriorityQueue<Event> robotEvents;
	private PriorityQueue<String> robotParameters;
	
	
	/**
	* @author Rachel Schneberger
	* Robot constructor that sets a starting location, its id and a full charge
	*/
	public Robot(int x, int y, int id, int charge ){
		xCord = x;
		yCord = y;
		robotId = id;
		robotCharge = charge;
	}
	
	/**
	* @author Rachel Schneberger
	* @param x, the x location on the floor grid 
	* @param y, the y location on the floor grid
	*/
	public void move(int x, int y){
		/*
		 * needs to check to see if space given is occupied
		 * also need to check if shelf is true or false...this
		 * determines if we are moving a shelf or not
		 */
		xCord = x;
		yCord = y;
		System.out.println( "Robot: " + robotId + " has moved: " + xCord + yCord);
		
	}
	
	/**
	* @author Rachel Schneberger
	* @param usable a boolean that tells the floor if a robot is usable or not
	* @param robot charge an int that keeps track of a robots charge and will decrease when
	* robot is being used and when robot is being charged.
	*/
	public void restAndCharge(){
		//robots cannot be used while charging
		// charge increases by one every clock tick...
		usable = false;
		while (robotCharge < 30){
			robotCharge = robotCharge +1;
		}
		usable = true;
	}
	
	/**
	* @author Rachel Schneberger
	*/
	public void chargeUsage(){
		//when a robot is usable = true
		//charge needs to decrease by one every clock tick
		if (robotCharge == 20){
			//head back to charging station
		}
		if (robotCharge == 0){
			usable = false;
		}
		else{
			usable = true;
		}
	}
	
	/**
	* @author Rachel Schneberger
	*/
	public void pickShelf(){
		//wait one clock tick before picking up and moving
		shelf = true;
	}
	
	@Override
	public void performAction(String Method) {
		if(Method == "move,1,1"){
			this.move(5,5);
		}
		else{
			this.move(1,1);
		}
	}
	
	@Override
	public Event getEvent() {
		if(robotEvents.isEmpty()){
			return(Event) new Robot(1,1,1,100);
		}
		else{
			return robotEvents.remove();
		}
	}

	@Override
	public String getPara() {
		if(robotParameters.isEmpty()){
			return "empty";
		}
		else{
			return robotParameters.remove();
		}
	}
	
}
