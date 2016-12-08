package production;

import java.util.*;

/**
 * 
 * @author Heather Kemp
 * The MockOrder object will function as a very fundamental test case containing one order and one item.
 * It manages instances of the Belt and Robot in order to execute calls to these methods.
 *
 */

public class MockOrder implements Event {
  
  Robot myRobot;
  MockBeltLine myBelt;
  Floor myFloor;
  Shelf myShelf;
  private ArrayList<Event> myEvents;
  private ArrayList<String> myParameters;
  private inventory myInventory;
  private int originalX;
  private int originalY;
  private int robotX;
  private int robotY;
  
  public MockOrder(Robot newRobot, inventory newInventory, Floor newFloor){
    myRobot = newRobot;
    robotX = newRobot.getXcord();
    robotY = newRobot.getYcord();
    myFloor = newFloor;
    myEvents = new ArrayList<Event>();
    myParameters = new ArrayList<Event>();
    myInventory = newInventory;
  }
  
  public void performAction(String Method){
    if(Method == "recieveOrder")
      this.recieveOrder();
    if(Method == "pickUpShelf")
      this.pickUpShelf();
  }
  
  public Event getEvent(){
    if (myEvents.isEmpty()){
      return Event(this);
    }
    else{
      return myEvents.remove();}
    }
  
  public String getPara(){
    if (myParameters.isEmpty()){
      return " ";
    }
    else{
      return myParameters.remove();
    }
  }
  
  public void recieveOrder(){
    System.out.println("Order recieved for: Pen");
    myShelf = myInventory.finditem("pen");
    System.out.println("Scheduling robot" + String.valueOf(myRobot.getID()) + " to go pick up shelf with: Pen.");
    myEvents.add(Event(myRobot));
    originalX = myShelf.x;
    originalY = myShelf.y;
    String robotMove = "move," + String.valueOf(myShelf.x) + "," + String.valueOf(myShelf.y);
    myParameters.add(robotMove);
  }
  
  public void pickUpShelf(){
    System.out.println("Robot " +  String.valueOf(myRobot.getID()) + " is picking up the shelf with: Pen.");
    myRobot.pickShelf(myShelf);
    System.out.println("Robot " + String.valueOf(myRobot.getID()) + " is moving to the picker.");
    myEvents.add(Event(myRobot));
    String robotMove = "move," + String.valueOf(myFloor.getPicker().x) + "," + String.valueOf(myFloor.getPicker().y);
    myParameters.add(robotMove);
    myBelt = new MockBeltLine(myFloor.getPicker().x,myFloor.getPicker().y,"pen");
    myEvents.add(Event(myBelt));
    myParameters.add("moveBelt,");
    myEvents.add(Event(myRobot));
    String robotMove2 = "move," + String.valueOf(originalX) + "," + String.valueOf(originalY);
    myParameters.add(robotMove2);
    myEvents.add(Event(myRobot));
    myParameters.add("dropShelf,");
    String robotMove3 = "move," + String.valueOf(robotX) + "," + String.valueOf(robotY
    myParameters.add(robotMove3);
    myEvents.add(Event(myRobot);
  }
  
  
}
