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
  Belt myBelt;
  private ArrayList<Event> myEvents;
  private ArrayList<String> myParameters;
  
  public MockOrder(Robot newRobot, Belt newBelt){
    myRobot = newRobot;
    myBelt = newBelt;
    myEvents = new ArrayList<Event>();
    myParameters = new ArrayList<Event>();
  }
  
  public void performAction(String Method){
  }
  
  public Event getEvent(){
  }
  
  public String getPara(){
  }
  
  public void recieveOrder(){
  }
  
}
