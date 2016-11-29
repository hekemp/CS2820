package production;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
* @author Rachel Schneberger
*
* This class has the functionality for a single a robot. 
* There can be multiple robots in use at the same time. 
*/

public class Robot implements Event{

	
	private int robotId;
	private int robotCharge;
	private boolean shelf = false;
	public boolean usable = true;
        private Point location;
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
		 * Do I need to check to see if space given is occupied?
		 * also need to check if shelf is true or false...this
		 * determines if we are moving a shelf or not
		 */
		xCord = x;
		yCord = y;
		System.out.println( "Robot: " + robotId + " has moved: " + xCord + yCord);
		
	}
        
        
	
	/**
	* @author Rachel Schneberger
	* @param roboId - number to identify which robot (in our case, only one)
	* @param charge - number of current robot charge. 
        * Probably not necessary 
	* robot is being used and when robot is being charged.
	*/
	public void restAndCharge(int roboId, int charge){
		//robots cannot be used while charging
		// charge increases by one....eventually. 
                //called as an event....when?
                robotCharge = charge;
                usable = false;
		while (robotCharge < 50){
			robotCharge = robotCharge +1;
		}
		usable = true;
	}
	
	/**
	* @author Rachel Schneberger
	*/
	public void checkCharge(){
		//when a robot is usable = true
		//charge needs to decrease by one every clock tick
		if (robotCharge == 20){
                    move(1,1);//change x and y to actual charging station point
			//should probably be get route instead. 
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
		//wait one clock tick before picking up and moving....we are not implementing tick..
		shelf = true;
	}
	
	@Override
	public void performAction(String Method) {
                String[] action = Method.split(",");
                List<String> doAction = new ArrayList<String>(Arrays.asList(action));
                int x = Integer.valueOf(doAction.get(1));
                int y = Integer.valueOf(doAction.get(2));
                if(doAction.get(0) == "move"){
                    this.move(x,y);
                }
                if(doAction.get(0)== "restAndCharge"){
                    this.restAndCharge(x,y);
                }
                if(doAction.get(0) == "checkCharge"){
                    this.move(x, y);
                    // replace x and y with specific location of charger
                }
                if(doAction.get(0) == "pickShelf"){
                    pickShelf();
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
