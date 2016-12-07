package production;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.awt.Point;

/**
* @author Rachel Schneberger
*
* This class has the functionality for a single a robot. 
*
* 
* this is a test.
* ...............
*/

public class Robot implements Event{
	
	private int robotId;
	private int robotCharge;
        private ArrayList<Point> robotRoute = new ArrayList<Point>();
	private boolean shelf = false;
        private Point shelfLocation;
	public boolean usable = true;
        private Point location;
        private Point robotDestination;
	private int xCoord;
	private int yCoord;
	private PriorityQueue<Event> robotEvents;
	private PriorityQueue<String> robotParameters;
	
	
	/**
	* @author Rachel Schneberger
	* Robot constructor that sets a starting location, its id and a full charge
	*/
	public Robot(int x, int y, int id, int charge){
		xCoord = x;
		yCoord = y;
                this.location = new Point(xCoord, yCoord);
		robotId = id;
		robotCharge = charge;
	}
	
        /**
         * @author Rachel Schneberger
         * returns a Point with location of robot
         */
        public Point getLocation(){
            return this.location.getLocation();
        }
        
        /**
         * @author Rachel Schneberger
         * returns current x coord
         */
        public int getXcord(){
            double k = this.location.getX();
            int n = (int) k;
            return n;
        }
        
        /**
         * @author Rachel Schneberger
         * returns current y coord  
         */
        public int getYcord(){
            double k = this.location.getY();
            int n = (int) k;
            return n;
        }
        
        public void getNextPoint(){
            this.location = robotRoute.get(0);
            robotRoute.remove(0);
            for (int i = 1; i<robotRoute.size(); i++){
                robotEvents.add(this);
                robotParameters.add("move," + robotRoute.get(i).getX() + "," + robotRoute.get(i).getY());
                if (shelf == true){
                    robotParameters.add("moveShelf" + robotRoute.get(i).getX() + "," + robotRoute.get(i).getY());
                }
                System.out.println("New move enqueued");
            }
            
        }
        
        //check where the robot is within a route
        //How does a robot know when it gets to a shelf or a picking station?
        public void taskStatus(){
        	if (getLocation() == robotDestination){ //checking if robot is at the end of a route
        		if (shelf == true){ //meaning robot is carrying a shelf...drop it
        			this.shelf = false;
        			this.shelfLocation = getLocation();
        		}
        		if (shelf == false){
        			this.shelf = true;
        			this.shelfLocation = getLocation();
        			Floor floor = new Floor();
        			floor.getRoute(1,1); ///replace 1s with location of picking station.
        			System.out.println("Got shelf. Getting route to picking station.")
        		}
                //pick shelf
                //drop shelf
                //some other task
                System.out.println("I'm working on this.");
            	}else{
			System.out.println("I'm worrking on this too.");
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
		xCoord = x;
		yCoord = y;
                location.move(x, y);
                //checks if shelf is true. If yes, then shelfLocation updates with the robot.
                if (shelf == true){
                    shelfLocation.move(x, y);
                }
                robotCharge = this.robotCharge -1; //decrease charge after every move?
                                                   //how will this get checked?
		System.out.println("Robot: " + robotId + " has moved: " + xCoord + yCoord);
		taskStatus();
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
	* @author Heather Kemp
	*/
	public void pickShelf(Shelf newShelf){
		//wait one clock tick before picking up and moving....we are not implementing tick..
		shelf = true;
		this.shelfLocation = newShelf.location;
		
		
	}
        
        public void dropShelf(){
            this.shelf = false;
	    this.shelfLocation = null;
        }
	
	@Override
	public void performAction(String Method) {
                String[] action = Method.split(",");
                List<String> doAction = new ArrayList<String>(Arrays.asList(action));
                int x = Integer.valueOf(doAction.get(1));
                int y = Integer.valueOf(doAction.get(2));
                if("move".equals(doAction.get(0))){
                    this.move(x,y);
                }
                if("restAndCharge".equals(doAction.get(0))){
                    this.restAndCharge(x,y);
                }
                //not correct yet....just temporary
                if("checkCharge".equals(doAction.get(0))){
                    this.move(x, y);
                    // replace x and y with specific location of charger
                }
                if("pickShelf".equals(doAction.get(0))){
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
