package production;

/**
 * 
 * @author Xinyu Qian
 */
import java.util.ArrayList;
class Shelf{
	int items = 0;
	ArrayList<item> Item = new ArrayList<>();
	int x, y;
	/**@author yunfjiang
	 * Point/location 
	 * boolean to tell if full
	 */
	Point location; 
	boolean iffull=false;
	
	public Shelf(Point P){
		x = P.x;
		y = P.y;
		P.shelf = true;
		location =P;
	}
	
	public String addItems(item I){
		Item.add(I);
		items++;
		if(items == 20) {
			iffull=true;
			return "This shelf is full.";
		}
		if(items > 20){
			items--;
			return "Load fails, shelf is full.";
		}
		return String.format("Add item %1$d %s to shelf", I.itemID, I.type);
	}
	
	public String removeItems(item I){
		Item.remove(Item.indexOf(I));
		items--;
		return String.format("Removed item %1$d %s from shelf", I.itemID, I.type);
	}
	
	/**@author yunfjiang
	 * to get location
	 * and calculate robot move
	 * 
	 */
	public Point getlocation(){
		return location;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public boolean full(){
		return iffull;
	}
	
	// floor will memorize the new location where robot
	// put the shelf at
	/**
     * 
     * @param P the location where robot drop the shelf
     */
	public void reSetLocation(Point P){
		this.x = P.x;
		this.y = P.y;
		P.shelf = true;
	}
	
}
