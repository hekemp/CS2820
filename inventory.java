package production;
import java.awt.List;
import java.util.ArrayList;



	/**
	 * @author Yunfan Jiang
	 *  inventory class 
	 * 
	 */
public class inventory implements Event{
	Robot robot;
	Floor floor;
	Shelf[] myshelf;
	int totalnum;
	int currentID;           
	int maxinventory=80;
	String[] itemlist={"pen","pear","apple","banana","pen","pineapple","eraser","ruler","comic book","ruler","laptop","pear","eraser","comic book"};
		
	/**
	 * @author Yunfan Jiang
	 * initialize the stock at the beginning
	 */
	public void inventory(Floor y, Robot x ){
		
		this.floor=y;
		this.robot=x;
		this.myshelf=y.myShelf();
		currentID=0;
		totalnum=0;
		for (int z=0;z<itemlist.length&&z<maxinventory;z++){
			item a= new item(currentID,itemlist[z]);    
			placeitemtoshelf(a);  // This line modified by Xinyu Qian, changed (item x) to (a)
			currentID++;
			totalnum++;
			System.out.println("item with ID"+currentID+"has been added to shelf");
			}
		System.out.println("Inventory Initialized" );	
		}
		/**
		 * @author Yunfan Jiang
		 * @author Xinyu Qian, Just add a few corrections
		 * @param product
		 * find the location of ONE of the many items with order description
		 */
	
	public Shelf finditem(String product){
		/**
		 * it would be better to if we could tell shelf is moving here
		 * to avoid calling a moving shelf
		 */
		for (Shelf j:this.myshelf){ 
			for (item i:j.Item){ // Modified by Xinyu Qian, just debugging
				if(i.type==product){
					System.out.println("item is located at Shelf"+ (j.y - 2));  // Modified by Xinyu Qian, just debugging
					return i.getplace();
					
				}
					
			}
	
		}
		// Modified by Xinyu Qian, add a return statement if cannot find this item from shelves
		System.out.println("This item is sold out.");
                return null;
	}
		
		/**@author Yunfan
		 * @param item i
		 * @param shelf y
		 * remove a item from its shelf 
		 */
	
	public item removeitem(item i, Shelf y){
		y.removeItems(i);
		totalnum--;
		System.out.println("item is removed from the stock");
		return i;
	}

		/**@author Yunfan Jiang
		 * this method check the number of stocks
		 * and update inventory if stock is low
		 */
	public void checkstatus(){
		if (totalnum<20){		
			increaseinventory(maxinventory-totalnum);
			System.out.println("Inventory updated");
			}
		}
			
		/**@author Yunfan Jiang
		 * this is an inner method I used for 
		 * constructor and increaseinventory
		 */
	private void placeitemtoshelf(item x){
		for (Shelf j:this.myshelf){ 
			if(j.items<20){
				j.addItems(x);	
				break;	
			}
		}
	}
		/**@author Yunfan Jiang
		 * @param int number to increase
		 * this is an inner method for checkstatus
		 */
		
	private void increaseinventory(int x){
		while(totalnum<maxinventory){
			    
			for (Shelf i:myshelf){
				if (!i.full()){	
					this.robot.move(i.getX(),i.getY());
					this.robot.pickShelf();
					this.robot.move(this.floor.getReceving().x,this.floor.getReceving().y);
					while(!i.full()){
						item a= new item(currentID,itemlist[currentID]);
						currentID++;
						i.addItems(a);	
						}
					this.robot.move(i.getX(),i.getY());
					this.robot.dropShelf();
						
				}
				
			}
		
			
			
		}
		
	}
	
	
	/**@Yunfan Jiang
	 * @Override
	 * In every tick, check status
	 * automatically start to increase stock if low
	 */
	public void performAction(String Method){
		checkstatus();
	
	}
		
		
	public Event getEvent(){

		    return (Event) this;
		  }
		
		
	public String getPara(){
		   return "";
		  }


	}
	
