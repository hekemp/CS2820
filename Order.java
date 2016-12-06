package production;

import java.util.ArrayList;

/**
 * 
 * @author hyo kyung kang also credited to prof Ted Herman
 *
 */
public class Order implements Event {
	String progress;
	String address;
	String dateReceived;
	int orderNumber;
	boolean isFilled;
	OrderItem[] orderitems;
	
	ArrayList<item>orderItemstatus;
	
	/**
	 * @author Mouna Elkeurti
	 * Default constructor needed for belt
	 */
	
	public Order(){}
	
	public Order(String progress,String address, String dateReceived,int orderNumber, OrderItem[] items){
		progress=progress;
		address=address;
		dateReceived=dateReceived;
		this.orderNumber=orderNumber;
		orderitems=items;
		this.orderItemstatus= orderItemstatus;
		
	}
	
	public OrderItem getUnfilledItem() {
	for (OrderItem e: orderitems) {
	  if (e.inBin) continue;
	  return e;
	  }
	isFilled = true;  // because all OrderItems are in bin
	return null; // if no needed OrderItem can be found
   	 }
	
	public void itemadding(item o) {
		orderItemstatus.add(o);
	}
	
	public void itemremoving(item o){
		orderItemstatus.remove(o);
		
	}
	
	public boolean itemcontaining(item o){
		if (orderItemstatus.contains(o)){
			return true;
		}
		else{
			return false;
		}
			
		
	}
	
	
	
	public String showAddress() { return address; }
  	public OrderItem[] getOrderItems() { return orderitems; }
	public void cancel(){}
	
	public void returningprocess(){	}
	
	public String getProgress(){
		return progress;
		
	}

	@Override
	public void performAction(String Method) {
			}

	@Override
	public Event getEvent() {
		return (Event)this;
	}

	@Override
	public String getPara() {
		return "";
	}
	
	
}
