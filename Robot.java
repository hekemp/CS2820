package production;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import production.Floor;
import production.Point;

/**
* @author Rachel Schneberger
*
* This class has the functionality for a single a robot. 
*
* 
* this is a test.
* ...............
*/

public class Robot extends Production implements Event{
	
	private int robotId;
        private Floor myFloor;
        private Shelf myShelf;
        private ArrayList<Point> robotRoute = new ArrayList<Point>();
	private boolean shelf = false;
        private Point shelfLocation;
	public boolean usable = true;
        private Point location;
        private Point robotDestination;
        private String robotTask;
	private int xCoord;
	private int yCoord;
	private PriorityQueue<Event> robotEvents;
	private PriorityQueue<String> robotParameters;
	
	
	/**
	* @author Rachel Schneberger
	* Robot constructor that sets a starting location, its id and a full charge
	*/
	public Robot(Floor floor, Shelf shelf, int x, int y, int id){
		xCoord = x;
		yCoord = y;
                myFloor = floor;
                myShelf = shelf;
                this.location = new Point(xCoord, yCoord);
		robotId = id;
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
        
        //check where the robot is within a route
        //How does a robot know when it gets to a shelf or a picking station?
        public void taskStatus(){
        	if (getLocation() == robotDestination){ //checking if robot is at the end of a route
        		if (shelf == true){ //meaning robot is carrying a shelf...drop it
        			this.shelf = false;
        			this.shelfLocation = getLocation();
                                robotParameters.add("a"); // automatically goes back to charger
                                //we can work on getting route to charger
        		}
        		if (shelf == false){ //meaning robot is not carrying a shelf...pick it up
        			this.shelf = true;
        			this.shelfLocation = getLocation();
                                
                                //get a new route to picker
                        }
                //pick shelf
                //drop shelf
                //some other task
                System.out.println("I'm working on this.");
            }
        }
        
        /**
         * 
         * @param task 
         */
        public void assignTask(String task){
            if(task.equals("toShelf")){
                robotTask = task;
                robotRoute = myFloor.getRoute(getLocation(), myShelf.getlocation());
                robotParameters.add("move," + String.valueOf(robotRoute.get(0).getX()) + "," + String.valueOf(robotRoute.get(0).getY()));
                System.out.println("Robot received new route to shelf.");
            }
            if(task.equals("toPicker")){
                robotTask = task;
                robotRoute = myFloor.getRoute(getLocation(), myFloor.getPicker());
                robotParameters.add("," + "move," + String.valueOf(robotRoute.get(0).getX()) + String.valueOf(robotRoute.get(0).getY()));
                System.out.println("Robot received new route to picker.");
            }
            if(task.equals("toCharger")){
                robotTask = task;
                robotRoute = myFloor.getRoute(getLocation(), myFloor.getCharger());
                System.out.println("Robot received new route to charger");
            }
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
		System.out.println("Robot: " + String.valueOf(robotId) + " has moved: " + String.valueOf(xCoord) + String.valueOf(yCoord));
		taskStatus();
	}
        
        public void getNextPoint(){
            this.location = robotRoute.get(0);
            robotRoute.remove(0);
            for (int i = 1; i<robotRoute.size(); i++){
                robotEvents.add(this);
                robotParameters.add("move," + String.valueOf(robotRoute.get(i).getX()) + "," + String.valueOf(robotRoute.get(i).getY()));
                if (shelf == true){
                    robotParameters.add("moveShelf" + robotRoute.get(i).getX() + "," + robotRoute.get(i).getY());
                }
                System.out.println("New move enqueued");
            }
            
        }

	/**
	* @author Rachel Schneberger
	*/
	public void pickShelf(){
		//wait one clock tick before picking up and moving....we are not implementing tick..
		shelf = true;
	}
        
        public void dropShelf(){
            this.shelf = false;
        }
	
	@Override
	public void performAction(String Method) {
                String[] action = Method.split(",");
                List<String> doAction = new ArrayList<String>(Arrays.asList(action));
                if("move".equals(doAction.get(0))){
                    int x = Integer.valueOf(doAction.get(1));
                    int y = Integer.valueOf(doAction.get(2));
                    this.move(x,y);
                }
                if("pickShelf".equals(doAction.get(0))){
                    pickShelf();
                }
                if("assignTask".equals(doAction.get(0))){
                    this.assignTask(doAction.get(1));
                }
	}
	
	@Override
	public Event getEvent() {
		if(robotEvents.isEmpty()){
                    return(Event) new Robot(myFloor, myShelf, 1,1,1);
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
