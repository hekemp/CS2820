package production;

/**
 *
 * @author Xinyu Qian
 */
import java.util.ArrayList;
public class Floor implements viewFloor,robotPath, Event {
	int size = 7; //initailize floor size to be 7x7
	ArrayList<Point> belt = new ArrayList<>();
	ArrayList<Point> sPoint = new ArrayList<>(); // the list of shelfs' point
	Shelf[] shelf = new Shelf[4]; // array contain all shelf objects
	Point packer;
	Point picker;
	Point receving;
	Point charger;
	Point shipping;
	
	//Initializing the floor
	public Floor(){
		this(7);
        }
	public Floor(int floorsize){
		size = floorsize;
		// initialize the belt location
		for(int i = 0; i < size+1; i++){
			belt.add(new Point(0, i));
			belt.get(i).belt = true;
		}
		// initialize the shelf location
		for(int i = 2; i < 6; i++){
			sPoint.add(new Point(4,i));
			shelf[i-2] = new Shelf(sPoint.get(i-2));
		}
		packer = new Point(1,3);
		packer.packer = true;
		
		picker = new Point(1,size-1);
		picker.picker = true;
		
		receving = new Point(size-2, size);
		receving.receving = true;
		
		charger = new Point(size-1,0);
		charger.charger = true;
		
		shipping = new Point(0,0);
		shipping.shipping = true;
	}
	
	public ArrayList<Point> getBelt(){
		return belt;
	}
	public ArrayList<Point> getShelf(){
		return sPoint;
	}
	public Point getPacker(){
		return packer;
	}
	public Point getPicker(){
		return picker;
	}
	public Point getReceving(){
		return receving;
	}
	public Point getCharger(){
		return charger;
	}
	public Point shipping(){
		return shipping;
	}
	
	public int floorSize(){
		return size;
	}
	
	// the list of points for robot from p1 to p2
	private ArrayList<Point> route;
    /**
     * @auther Xinyu Qian
     * @param p1 start point
     * @param p2 end point
     * @return the ArrayList of points from p1 to p2
     */
	public ArrayList<Point> getRoute(Point p1, Point p2){
	route = new ArrayList<>();
        int move = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
        for(int i=0; i< move-1;i++){
            if(p1.x - p2.x > 1){
                route.add(new Point(p1.x -1, p1.y));
            }
            else if(p1.x - p2.x < -1){
                route.add(new Point(p1.x +1, p1.y));
            }else{
                if(p1.y- p2.y > 0){
                    route.add(new Point(p1.x, p1.y-1));
                }
                else if(p1.y- p2.y < 0){
                    route.add(new Point(p1.x, p1.y+1));
                
                }else{
                    if(p1.x - p2.x > 0){
                        route.add(new Point(p1.x -1, p1.y));
                    }
                    if(p1.x - p2.x < 0){
                        route.add(new Point(p1.x +1, p1.y));
                    }
                }
            }
            p1 = route.get(i);
        }
        route.add(p2);
        return route;
	}
	/**  
	simple test for getRoute method
	public static void main(String[] args){
		Floor F = new Floor();
		System.out.println(F.getRoute(F.getCharger(), F.getPacker()));
		System.out.println(F.getRoute(F.getPicker(), F.getCharger()));
		System.out.println(F.getRoute(F.getPicker(), F.getPacker()));
	}
	*/
	
	@Override
	public Event getEvent(){
		return (Event)this;
	}
	@Override
    public void performAction(String Method) {
         
    }

    @Override
    public String getPara() {
         return "";
    }
	/**@author yunfjiang
	 * to return a list of all shelf
	 */
	public Shelf[] myShelf(){
		return this.shelf;
	}
}
