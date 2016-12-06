package production;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Mouna Elkeurti
 * @param belt variable that indicate which cell in belt the Objects are.
 * @param moving variable to check if the belt is moving. 
 * Belt class moves bins in the belt from picker to packer.
 * At packer location, the belt is stopped and a packer grab a package
 * and add it to the belt and goes to the shipping dock
 */

public class Belt implements Event {

    int belt = 0;         
    boolean moving;       
    Floor floor;
    Bin bin;
    Order order;
    Package packages;
    
    Queue Bin  = new LinkedList();     //Queue that holds bins used for belt
    Queue Package = new LinkedList();  //Queue that hols package that are need to package the items
    Queue item = new LinkedList();     //Queue that holds the items in order on the belt
 
    /**
     * @author Mouna Elkeurti
     * Belt constructor that set the belt cell (belt variable) to 0 
     * to keep track of where bins/item and package are on belt
     */
    
    public Belt(Floor f){
    	this.floor = f;
    	belt =0;
    	moving = false;
    	
    }
    
    // populating an initial Queue for bins
    public void populateBin(){
    	Bin.offer(bin);Bin.offer(bin);Bin.offer(bin);
    	Bin.offer(bin);Bin.offer(bin);Bin.offer(Bin);
    	Bin.offer(bin);
    	
    	}
    
    // Populating an intial Queue for packages
    public void popoluatePackage(){
    	Package.offer(packages);Package.offer(packages);Package.offer(packages);
    	Package.offer(packages);Package.offer(packages);Package.offer(packages);
    	Package.offer(packages);
    }
    
    // Populate an intial Queue fro items
    public void populateItem(){
    	item.offer(item);item.offer(item);item.offer(item);
    	item.offer(item);item.offer(item);item.offer(item);
    	item.offer(item);
    }
    
    /**
     * @author Mouna Elkeurti
     */
    public void moveBelt(){
    	while( Bin.isEmpty() == false && Package.isEmpty() == false){
    		Bin.peek();
    		item.peek();
    		Bin.offer(bin);
    		item.offer(item);
    		belt++;
    		if (belt ==4){
    			moving =false;
    			pack();
    			}
    		if (belt == 7){
    		belt =0;
    		}
    		
    	moving = true;
    	}
    	
    }
    
    /**
     * @author Mouna Elkeurti
     */
    public void pack(){
    	while(Package.isEmpty()==false && belt == 4){
    		Package.peek();
    		moving = false;
    	}
    	Package.peek();
    }
    
    public void performAction(String Method){
        System.out.println("Belt is moving");
    }
	
	
    public Event getEvent(){ // not implemented yet
	   
    }
    
    public String getPara(){ // not implemented yet
    }
    

   
}
