package production;

public class item {
	
	/** 
	 *  
	 * @author Yunfan Jiang
	 * 
	 */ 


		String type;      
		int itemID;       //Each item has an integer assigned as its unique itemID
		Shelf place;
		
		item(int id, String name){
			itemID=id;
			place=null;
			type=name;
		}
		public Shelf setplace(Shelf x){
		place=x;
		}
		public Shelf getplace(){
			return place;
		}
		
		

	}

