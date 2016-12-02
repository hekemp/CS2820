package production;
import java.awt.List;
import java.util.ArrayList;



	/**
	 * @author Yunfan Jiang
	 *  inventory class 
	 * 
	 */
public class inventory implements Event{
	int totalnum;
	int currentID;           
	int maxinventory=80;
	String[] itemlist={"pen","pear","apple","banana","pen","pineapple","eraser","ruler","comic book","ruler","laptop","pear","eraser","comic book"};
		
	/**
	 * @author Yunfan Jiang
	 * initialize the stock at the beginning
	 */
	public void inventory(){
			//import a list of items
		currentID=0;
		totalnum=0;
		for (int z=0;z<itemlist.length&&z<maxinventory;z++){
			item a= new item(currentID,itemlist[z]);    
			placeitemtoshelf(item x);
			currentID++;
			totalnum++;
			System.out.println("item with ID"+currentID+"has been added to shelf");
			}
		System.out.println("Inventory Initialized" );	
		}
		/**
		 * @author Yunfan Jiang
		 * @param product
		 * find the location of ONE of the many items with order description
		 */
	
	public Shelf finditem(String product){
		/**
		 * it would be better to if we could tell shelf is moving here
		 * to avoid calling a moving shelf
		 */
		for (int j=0;j<floor.shelf.length;j++){ 
			for (item i:floor.shelf[j]){
				if(i.type==product){
					System.out.println("item is located at Shelf"+j);
					return i.getplace();
					
				}
					
			}
		}	
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
		for (int j=0;j<floor.shelf.length;j++){
			if(floor.shelf[j].items<20){
				floor.shelf[j].addItems(x.itemID);	
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
			item a= new item(currentID,itemlist[currentID]);    
				
			//unfinished
			//need to call available robots here
			for (Robot i:)
			placeitemtoshelf(a);
				currentID++;	
		}
		
	}
	public void performAction(String Method){
		String[] action = Method.split(",");
		List<String> doAction = new ArrayList<String>(Arrays.asList(action));
		if("itemcontrol".equals(doAction.get(0))){
			this.itemcontrol());
		}
		if("checkstatus".equals(doAction.get(0))){
			this.checkstatus());
		}
	}
		
		
	public Event getEvent(){

		    return (Event) this;
		  }
		
		
	public String getPara(){
		   return "Null";
		  }


	}
	
