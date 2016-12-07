
package production;

import java.util.*;

/**
 * @author Mouna Elkeurti
 * 
 * Belt class moves bins in the belt from picker to packer.
 * At packer location, the belt is stopped and a packer grab a package
 * and add it to the belt and goes to the shipping dock
 */

public class Belt implements Event{
   
    boolean moving; 
    int size;
    Floor floor;
    Bin bin;
    Order order;
    item item;
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
    
    public Bin getBin(){
  	  Bin bin = new Bin();
  	  return bin;
    }
    
    public item getItem(){
    	item item = new item();
    	return item;
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
