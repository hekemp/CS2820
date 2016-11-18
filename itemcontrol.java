package production;
import java.awt.List;
import java.util.ArrayList;



	//@author Yunfan Jiang
public class itemcontrol implements Event{
	int totalnum;
	int currentID;           
	int maxinventory=80;
	String[] itemlist={"pen","pear","apple","banana","pen","pineapple","eraser","ruler","comic book","ruler","laptop","pear","eraser","comic book"};
		
		//initialize at the first tick/ event
	public void itemcontrol(){
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
		//Order can use this method to locate the item
	public Shelf finditem(String product){
		for (int j=0;j<floor.shelf.length;j++){  //need to check if shelf is moving here
			for (item i:floor.shelf[j]){
				if(i.type==product){
					System.out.println("item is located at Shelf"+j);
					return i.getplace();
					
				}
					
			}
		}	
	}
		
		
		//Order can use this method to remove item from shelf before putting it put on the belt
	public item removeitem(item i, Shelf y){
		y.removeItems(i);
		totalnum--;
		System.out.println("item is removed from the stock");
		return i;
	}

		//Everytime an order is completed, check status and update stock
	public void checkstatus(){
		if (totalnum<20){		
			increaseinventory(maxinventory-totalnum);
			System.out.println("Inventory updated");
			}
		}
			
		
	private void placeitemtoshelf(item x){
		for (int j=0;j<floor.shelf.length;j++){
			if(floor.shelf[j].items<20){
				floor.shelf[j].addItems(x.itemID);	
				break;	
			}
		}
	}
		
		
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
		System.out.println("Inventory did something");
	}
		
		
	public Event getEvent(){

		    return (Event) this;
		  }
		
		
	public String getPara(){
		   return "Null";
		  }


	}
	

