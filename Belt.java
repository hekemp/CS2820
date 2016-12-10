
package production;

import java.util.*;

/**
 * @author Mouna Elkeurti
 * 
 * Belt class moves bins in the belt from picker to packer.
 * At packer location, the belt is stopped and a packer grab a package
 * and adds it to the belt and goes to the shipping dock.
 */

public class Belt implements Event{
   
    boolean moving; 
    int size;
    Floor floor;
    Point location;
    Bin bin;
    item item;
    Queue<item> toBelt;
    PriorityQueue<Event> beltEvents;
    PriorityQueue<String> beltParameters;
    
    
    LinkedList belt = new LinkedList();
    ArrayList<Object> Dock = new ArrayList<Object>();
    
    public Belt(Floor f){
    	this.floor = f;
    	moving = false;
    	
    	for(int i=0; i<size; i++){
    		Bin b = new Bin();
    		belt.add(b);
    	}
    }
    /**
     * @author Mouna El Keurti
     * 
     */
    
    public Bin getBin(){
  	  Bin bin = new Bin();
  	  return bin;
    }
    
    // Store items to be added in the belt in a Queue
    public Queue<item> gettoBelt(){
		return toBelt;
	}
    
    // Picker get the items that needs to be added in the belt 
    public item getItem(){
    	return toBelt.poll();
    			
    }
    
    public Package getPackage(){
    	Package pack = new Package();
    	return pack;
    }
    
    public itemPackage getItemPackages(){
		return new itemPackage(getItem(), getPackage());
	}
    
    public itemBin getItemBin(){
		return new itemBin(getBin(),getItem());
	}
    
    /**
     * @author Mouna El keurti
     * One picker receives an items, picker add item to the Bin (A bin is removed from first index of belt
     * replaced with itemBin object) Once an item is added to the head, an other is removed from tail.
     * If item reaches packer location (size/2), packer remove item and bin and put the packed item back to belt.
     * If item reaches the end of belt location, an item is sent to dock location.
     */
    public void moveBelt(){
    	belt.set(0, getItemBin());
    	belt.removeLast();
    	while(belt.isEmpty() == false){
    		belt.addFirst(getItemBin());
    		belt.removeLast();
    		for(int i= 0; i<belt.size(); i++){
    			if( i == size/2 && belt.get(size/2) == getItemBin()){
    				doPackage();
    			}
    		}
    		while(belt.removeLast() == getItemPackages()){
    			addToDock();
    			beltParameters.add("A packaged item is dropped off from belt and added to dock");
    		}
    		
    	}
    	moving = true;
    	
    }
    
    public void doPackage(){
    	belt.set(size/2,getItemPackages());
    	moving = false;
    }
    
    public void addToDock(){
    	this.Dock.add(belt.removeLast());
    	beltEvents.add(this);
    	
    }
    
    /**
     * 
     * @author Mouna El keurti 
     * A class that put a received a item in a bin and make it as a new object
     *
     */
   
    public class itemBin{
    	
    	Bin bin;
    	item item;
    	
    	public itemBin(Bin bin, item item){
    		this.bin = bin;
    		this.item = item;
    		
    	}
    	
    	
    	
    	public itemBin combine(Bin bin, item item){
    		
    		return new itemBin(bin, item);
    	}
    	
    }
    
    /**
     * 
     * @author Mouna E keurti 
     * A class that pack the items and create a new object (packaged item)
     *
     */
    
    public class itemPackage{
    	item item;
    	Package packages;
    	
    	public itemPackage(item item,Package packages){
    		this.item = item;
    		this.packages = packages;
    		
    	}
    	
    	public itemPackage combine(item item, Package packages){
    		return new itemPackage(getItem(), getPackage());
    	}
    	
    	
    }
    
    
    @Override
    public void performAction(String Method){                                  //Robots implementation referance
    	String[] action = Method.split(",");
    	List<String> doAction = new ArrayList<String>(Arrays.asList(action));   
    	if("moveBelt".equals(doAction.get(0))){
            this.moveBelt();
        }
        if("doPackage".equals(doAction.get(0))){
            this.doPackage();
        }
        if("addToDock".equals(doAction.get(0))){
            this.addToDock();
        }
        
    }
	
	@Override
    public Event getEvent(){ 
		if(beltEvents.isEmpty()){
			return(Event) new Belt(floor);
			}
		else{
			return beltEvents.remove();
            }
    }
    
	@Override
    public String getPara(){
		if(beltParameters.isEmpty()){
			return "empty";
		}
		else{
			return beltParameters.remove();
		}
	}
     
	
   
}
