package production;

import java.util.*;
/**
 * 
 *  * @author hyo kyung kang credited to prof Ted Herman
 *
 */
public class OrderTest {

// orderdtest will have the event interface very soon 
public class OrderTest implements Orders, Tickable, Picker {
	
  private Inventory I;
  private RobotScheduler R;
  private Belt B;
  private LinkedList<Order> orderqueue;
  private SimRandom randomsource;
  private Order currentorder; 
  private Bin currentbin;
  private item neededitem;
  
  /**
   * @author Ted Herman
   * @param Inventory component, needed to create sensible
   * new Order to add to queue of work to do (for testing,
   * it just creates a few initial orders)
   * @param rand is a SimRandom, for predictable randomness
   */
  public OrderTest(Inventory I, Belt B,
		  RobotScheduler R, SimRandom rand) {
	this.I = I; // so we can later call upon Inventory methods
	this.R = R; // so that later, we can call RobotScheduler
	this.B = B; // we will need the Belt
	randomsource = rand;
	orderqueue = new LinkedList<Order>();
	currentorder = null;
	currentbin = null;
	neededitem = null;
	for (int i=0;i<3;i++) {
	  orderqueue.addLast(getRandomOrder());
	  }
    }
  
  /**
   * @author Ted Herman
   * 
   * This tick method would be where Orders does the 
   * real work (see design README)
   * 
   */
  public void tick(int count) {
	/**
	 * What tick should do (vague description)
	 * 1. if there is no Order in orderqueue, then
	 *    maybe it's time to generate a random new
	 *    Order and put it in the queue? Another idea
	 *    would be to delay putting a new Order in the 
	 *    queue (postpone that to a later tick)
	 * 2. if no Order being worked on, and
	 *    there is an Order in orderqueue, ask Belt 
	 *    if a new Bin is available - a "no" from the 
	 *    Belt would mean Orders has to wait - so ignore
	 *    this tick. 
	 * 3. if Belt says a new Bin can be started, and 
	 *    there's an Order in the orderqueue, time to 
	 *    start working (go to next step in this same tick).
	 *    Probably some variables/fields will be changed
	 *    to indicate that an Order is in progress, being
	 *    worked on by the Picker.
	 * 4. if an Order is being worked on (need additional
	 *    variable/fields to know this), then check if 
	 *    it has any Items that need fetching.
	 * 5. if an Item needs fetching, do pretty much what
	 *    is done in TestRobotScheduler, but when notify()
	 *    of the Picker interface is called (see below), then
	 *    get take the desired OrderItem from the Shelf that
	 *    the Robot brought, mark that OrderItem as being
	 *    in the Bin.
	 * 6. if all OrderItems are filled from the current 
	 *    Order being worked on, then tell the Belt that 
	 *    the Bin at the Picker is ready to go. 
	 *    
	 * Special Note on Step 5. In MockRobotScheduler, it's 
	 * assumed that whatever an Order needs, that OrderItem
	 * will be on some Shelf. A more realistic idea is to 
	 * attempt to find the OrderItem on a Shelf, but if it 
	 * cannot be found, tell Inventory (so Inventory would need
	 * to have a new method for that). Then Orders is stuck, 
	 * waiting for the desired OrderItem to be present, checking
	 * that in each tick(). Eventually, after Inventory has done
	 * its job, Orders will find a Shelf with the needed OrderItem
	 * and continue.
	 */
	// special case: make currentbin variable null if an order was
	// just finished and the belt moved it away
	if (currentbin != null && currentbin.isFinished() &&
		 B.binAvailable()) currentbin = null; 
	if (orderqueue.size() < 1) { makeOrder(); return; }
	if (currentorder == null) {
	  currentorder = orderqueue.pop(); // start work
	  System.out.println("Picker starts new order");
	  }
	
	// at this point, Orders is acting the Picker role, so it has 
	// to look at the current order and decide what to do
	if (currentbin == null && B.binAvailable()) {
	  System.out.println("Picker got a new bin");
	  currentbin = B.getBin();
	  }
	if (currentbin == null) return;  // try again in another tick to get bin
	if (currentorder.isFilled) {
	   // we have a bin, we have a filled order, so finish up this bin
	   currentbin.order = currentorder;
	   currentbin.setFinished();
	   currentorder = null;
	   return;  // done! The Bin part will take it away from here.
	   }
	
	// have we already requested a needed item? if yes, just wait for a 
	// later tick, or when the Robot notifies of arrival
	if (neededitem != null) return;
	
	// at this point in code, there is a currentorder not yet filled
	OrderItem nextitem = null;
	for (OrderItem item: currentorder.orderitems) {
	  if (item.inBin) continue;
	  nextitem = item; 
	  break;  // found an item to fetch
	  }
	if (nextitem == null) { // all items are in bin, so this order is done
	  currentorder.isFilled = true;
	  return;   // let a later tick take care of the bin
	  }
	// try to find the item in the warehouse, and ask for a robot
	if (!R.robotAvailable()) return;  // wait for a later tick
	Shelf s = I.findItem(nextitem);
	if (s == null) return;  // item not in warehouse, Inventory should replenish
	neededitem = nextitem;  // remember the item needed when Robot arrives
	R.requestShelf(s,(Picker)this);  // pretend to be the picker
	return;
    }

  /**
   * @author Ted Herman
   * Picker event notify(robot), not finished.
   */
  public void notify(Robot r, Shelf s) { 
	// should be code here to remove desired Item OrderItem from Shelf s
	// (Inventory has a method to do that)
	// mark that Item as being checked off in the Order
	// and if this is the last Item needed, tell Belt 
	// that a Bin is done.
	Item[] atpicker = I.onShelf(s);
	int found = -1;
	for (int i=0;i<atpicker.length;i++) {
	  if (atpicker[i].equals(neededitem)) found = i;
	  }
	assert found > -1;
	Item got = I.removeItem(neededitem, s);
	for (OrderItem e: currentorder.orderitems) {
	  if (!e.inBin && e.equals(neededitem)) {  // take item from shelf
		System.out.println("Picker item put in bin");
		e.inBin = true;  // put item in bin  
		break;
	    }
	  }
	neededitem = null;   // no longer need this item
	R.returnShelf(r);  // tell Robot to return Shelf back to its home
    };
    
  /**
   * @author Ted Herman
   * get a random new order and add it to the queue; a fancier
   * implementation of this would perhaps delay actually putting
   * the new order into the queue for some number of ticks, to 
   * simulate how orders might come in sporadically over time; 
   * and perhaps multiple new orders could be created in one call  
   */
  private void makeOrder() {
	orderqueue.add(getRandomOrder());
    };
  
  /**
   * @author Ted Herman
   * creates a random Order
   */
  public Order getRandomOrder() {
	String addr = new Address(randomsource).randomAddress();
	OrderItem[] orderitems = new OrderItem[1+randomsource.nextInt(2)];
	for (int i=0;i<orderitems.length;i++) {
	  orderitems[i] = new OrderItem(I.randomItem());
	  }
	return new Order(addr,orderitems);
    }
  }

/**
 * 
 * @author Ted Herman
 * A local class just to supply addresses
 * for orders in the Orders component
 *
 */
class Address {
	
  SimRandom SR;
  
  /**
   * @author Ted Herman
   * @param SR is SimRandom object, so that all the random
   * choices by methods of Address will be predictably random
   */
  public Address(SimRandom SR) {
	this.SR = SR;
    }

  /**
   * @author Ted Herman
   * @param R is a SimRandom for predictability in randomness
   * @return String containing a random address for an order
   */
  public String randomAddress() {
	String FirstName = randomFirstName();
	String LastName = randomLastName();
	String StreetNumber = new Integer(randomStreetNumber()).toString();
	String StreetName = randomStreetName();
	String City = randomCity();
	String State = randomState();
	String ZipCode = randomZip();
	String Address = FirstName + " " +
	  LastName + "\n" + StreetNumber + " " +
	  StreetName + "\n" + City + " " + State + ZipCode;
	return Address;
    }
  /**
   * @author Ted Herman
   * @return a string containing a random street name
   */
  private String randomStreetName() {
	final String[] baseNames = {"Park Street",
			"Main Street", "Washington Boulevard",
			"Third Street", "Park Road",
			"Maple Street", "Hill Road"};
	return baseNames[SR.nextInt(baseNames.length)];
    }
  /**
   * @author Ted Herman
   * @return an integer in the range [1,999] for street address
   */
  private int randomStreetNumber () {
	return 1+SR.nextInt(998);
    }
  /**
   * @author Ted Herman
   * @return a random first name for an address
   */
  private String randomFirstName() {
	final String[] baseFirstNames = {"Dakota", "Emma",
			"Julian", "Nigella", "Will", "Asti", "Lee",
			"Pat", "Mavis", "Jerome", "Lilly", "Tess"};
	return baseFirstNames[SR.nextInt(baseFirstNames.length)];
	}
  /**
   * @author Ted Herman
   * @return a random last name for an address
   */
  private String randomLastName() {
	final String[] baseLastNames = {"Parker","Mason",
				"Smith","Wright","Jefferson","Iqbal",
				"Owens","Lafleur","Metselen","Vinceroy",
				"Saville","Troitski","Andrews"};
	return baseLastNames[SR.nextInt(baseLastNames.length)];
    }
  /**
   * @author Ted Herman
   * @return a random city name
   */
  private String randomState() {
	final String[] baseState = {"IA","NE","MO",
				"IL","KS","MN","SD","AR","OK","TX"};
	return baseState[SR.nextInt(baseState.length)];
    }
  /**
   * @author Ted Herman
   * @return a random state code (two letters)
   */
  private String randomCity() {
	final String[] baseCity = {"Springfield","Clinton",
				"Madison","Franklin","Chester","Marion",
				"Greenville","Salem","Anytown","Hope"};
	return baseCity[SR.nextInt(baseCity.length)];
    }
  /**
   * @author Ted Herman
   * @return a random state code (two letters)
   */
  private String randomZip() {
    String ZipCode = "";
    for (int i=0; i<6; i++) 
      ZipCode += "0123456789".charAt(SR.nextInt(10));
    return ZipCode;
  }
}
