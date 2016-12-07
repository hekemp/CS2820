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
		  Floor F = new Floor(7);
		  Robot i=new Robot(F,0,0,1)
		inventory I = new inventory(F,i);
		
		assertEquals(totalnum,14);

	    }
	  /**
	   * Test that an item is in stock, on an expected shelf
	   */
	  @Test
	  public void test002() {
	
		Floor F = new Floor(7);
		Robot i=new Robot(F,0,0,1)
		inventory I = new inventory(F,i);
		 
		Shelf s = I.findItem("pen");
		assertTrue(s.Item.contains("pen");
	    }
	  }
