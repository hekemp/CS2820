package production;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class InventoryTest {
	

	
	  /**@author yunfjiang
	   * test if items are put on the shelves 
	   * when inventory is initialized
	   * 
	   */
	  @Test
	  public void test001() {
		  Floor newFloor = new Floor(6);
		  Shelf newShelf = new Shelf(new Point(0, 5));
		  Robot newRobot = new Robot(newFloor,newShelf,2,2,100);
		  inventory newInventory = new inventory(newFloor, newRobot);
		
		assertEquals(totalnum,14);

	    }
	  /**
	   * Test that an item is in stock, on an expected shelf
	   */
	  @Test
	  public void test002() {
		Floor newFloor = new Floor(6);
		Shelf newShelf = new Shelf(new Point(0, 5));
        	Robot newRobot = new Robot(newFloor,newShelf,2,2,100);
        	inventory newInventory = new inventory(newFloor, newRobot);
		 
		Shelf s = newInventory.finditem("pen");
		assertTrue(s.Item.contains("pen"));
	    }
	  }
