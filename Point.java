package production;

/**
 * 
 * @author Xinyu Qian
 */
class Point{
	int x, y;
	
	//this part would be ignored for right now
	boolean belt = false;
	boolean shelf = false;
	boolean picker = false;
	boolean packer = false;
	boolean receving = false;
	boolean shipping = false;
	boolean charger = false;
	/////////////////////////
	
	/**
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public Point(){ 
		this(5, 7);
	}
        
        /**
         * @author Rachel Schneberger
         * @return location 
         */
        public Point getLocation(){
            return new Point(x,y);
        }
        
        /**
         * @author Rachel Schneberger
         * @return x location of Point
         */
        public int getX(){
            return x;
        }
        
        /**
         * @author Rachel Schneberger
         * @return y location of Point
         */
        public int getY(){
            return y;
        }
        
        /**
         * @author Rachel Schneberger
         * "move" method
         */
        public void move(int x, int y){
            this.x = x;
            this.y = y;
        }
	
	// if the point is a highway return true
	public boolean highway(){
		return shelf||picker||packer||receving||shipping||charger;
	}
	
	/**
     * 
     * @return a readable tuple of the point
     */
	@Override
    public String toString(){
        String s;
        s = String.format("(%d"+","+"%d)", x, y);
        return s;
    }
	
}
