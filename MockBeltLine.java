/**
 * 
 * @author Heather Kemp
 * the MockBelt.java class is a mock version of the Belt.java object, created simply to test the Master's ability to
 * cycle through events.
 * It only implements the methods required by the Event interface.
 *
 */

package production;

import java.util.*;

public class MockBeltLine implements Event{
    
   
   int myX;
   int myY;
   String myItem;
   private ArrayList<Event> myEvents;
  private ArrayList<String> myParameters;
    
    public MockBeltLine(int x, int y, String item){
    myX = x;
    myY = y;
    myItem = item;
    myEvents = new ArrayList<Event>();
    myParameters = new ArrayList<String>();
    }
    
    /**
     * Performs an action. Extracts the method to be executed and parameters from the method strong and then executes these
     * methods.
     * @input - a string which contains a method name/key and parameters, seperated by commas
     */
    public void performAction(String Method){
        while(!(myX == 0 && myY == 0))
        { 
            if(myX > 0)
            {int tempX = myX;
            myX = myX - 1;
            System.out.println(myItem + " moved from: X = " + String.valueOf(tempX) + " to X = " + String.valueOf(myX));}
        else if (myY > 0)
            {int tempY = myY;
            myY = myY - 1;
            System.out.println(myItem + " moved from: Y = " + String.valueOf(tempY) + " to Y = " + String.valueOf(myY));}
        else;
        }
        
    }
    
    /**
     * Gets the event. Returns this object as an Event
     */
    
    public Event getEvent(){

         return (Event) this;
       }
    /**
     * Gets the parameters. Returns the parameter for the next call of this method.
     */
    
    public String getPara(){
        if(myX == 0 && myY == 0) return " ";
        else return "Continuing";
       }
}
