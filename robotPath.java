package production;
/**
 *
 * @author Xinyu Qian
 */
import java.util.ArrayList;
// interface for robot to use 
// in order to  return a path from floor
public interface robotPath {
	ArrayList<Point> getRoute(Point p1, Point p2);
}
