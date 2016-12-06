package production;
// interface for visualizer
public interface viewFloor {
	public ArrayList getBelt();
	public ArrayList getShelf();
	public Point getPacker();
	public Point getPicker();
	public Point getReceving();
	public Point getCharger();
	public Point shipping();
	public int floorSize();
}
