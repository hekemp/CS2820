package production;

public class item {
	
	/** 
	 *  
	 * @author Yunfan Jiang
	 * This class defines features of an item, which will be 
	 * transported around in the warehouse
	 * 
	 */ 


		String type;      
		int itemID;       //Each item has an integer assigned as its unique itemID
		Shelf place;
		
		/**
		 * @param id
		 * @param name
		 * create a new item with given id and description
		 * 
		 */
	        item(){}   // default item constructor added by melkeurti
	
		item(int id, String name){
			itemID=id;
			place=null;
			type=name;
		}
		
		/**@author Yunfan Jiang
		 * @param Shelf
		 * set item's location
		 */
		public void setplace(Shelf x){
		place=x;
		}
		
		/**@return which shelf it is located
		 */
		public Shelf getplace(){
			return this.place;
		}
		
		

	}

