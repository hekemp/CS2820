package production;

/**
 * 
 * @author Ted Herman
 * This is just like Item, but we have extra members
 * to keep track of the progress of this in an order
 *
 */
public class OrderItem extends Item {
  boolean inBin;
  /**
   * @author Ted Herman
   * @param Num exactly as used in Item constructor
   * @param Title as in Item constructor
   * creates a subtype of Item with "in bin" boolean
   * to express whether or not this item has been put
   * in the bin at the picker station
   */
  public OrderItem(int Num, String Title) {
	super(Num,Title);
	inBin = false;
    }
  public OrderItem(Item N) {
	this(N.id,N.description);
    }
  public boolean filled() { return inBin; }
  public void setFilled() { inBin = true; }
  }
