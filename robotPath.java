package production;
// interface for robot to use 
// in order to  return a path from floor
public interface robotPath {
	public Point[] getRoute(Point p1, Point p2);
}
