package production;

import java.util.*;

/**
 * @author Mouna Elkeurti
 * Bin class moves bins with the order from the picker to the packer then 
 * from the packer to the dock location to get it shipped
 */

public class Belt implements Event{

    
    boolean moving = false;
    boolean order_belt = false;
    double speed = 0.0;
    private Bin bin;
    Order order;
    
    public Belt(int sp){
    	speed = sp;
    }
    
    /**
     * @author Mouna Elkeurti
     * @param x is the speed of which the belt is moving with
     * pausing, starting and changing the speed of the belt
     */
    void changeSpeed(double x){
        speed = x;
    }
    
    /**
     * @author Mouna Elkeurti 
     * pausing the belt to leave time for the picker to add bin on the bell then restart it
     */
    void pause(){
        speed = 0;
    }
    
    void start(double x){
        speed = x;
    }
    
    boolean ismoving(){
    	moving = true;
    	System.out.println( "Belt is moving");
    }
    	
   
    
    ArrayList<Bin> binOnBelt = new ArrayList<>();
     
    /**
     * @author Mouna Elkeurti
     * @param bins are added to the belt
      * the picker add bins to the belt to the packer
      */
    void add(Bin bin){
    	pause();
    	binOnBelt.add(bin);
    }
    /**
     * @author Mouna Elkeurti 
     * @param bins are removed from the belt
     * bin is removed from the belt once it arrives to the dock location
     */
    void remove(Bin bin){
    	binOnBelt.remove(binOnBelt.size()-1);
    	ismoving();
    }
    
    ArrayList<Order> orderOnBelt = new ArrayList<>();
    
    /**
     * @author Mouna Elkeurti
     * @param orders are added to the belt 
     * method can be used by order to add a new order in the belt or remove it to go to the dock
     */
    void addOrder(Order order){
    	orderOnBelt.add(order);
    }
    
    /**
     * @author Mouna Elkeurti
     * Order arrives the to the shipping dock
     */
    void getorderNumber(){    // to scan the order
        return orderNumber;
    }
    
    void removeOrder(Order order){
    	orderOnBelt.remove(orderOnBelt.size()-1);
    }
    
    //void scan(){
    //}
    
    void pack(){
    	pause();
    	order_belt = true;
    }
    
    public void performAction(String Method){
        System.out.println("Belt is moving");
    }
	
	
    public Event getEvent(){ // not implemented yet
	   
    }
    
    public String getPara(){ // not implemented yet
    }

}