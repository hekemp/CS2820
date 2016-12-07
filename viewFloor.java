package production;
// interface for visualizer
/**
 *
 * @author Xinyu Qian
 */
import java.util.ArrayList;
public interface viewFloor {
	ArrayList<Point> getBelt();
	ArrayList<Point> getShelf();
	Point getPacker();
	Point getPicker();
	Point getReceving();
	Point getCharger();
	Point shipping();
	int floorSize();
}
