package production;

import java.util.HashMap;
/**
 * 
 * @author hyo kyung kang also credited to prof Ted Herman
 *
 */
public class Order {
	
	String progress;
	String address;
	String dateReceived;
	int orderNumber;
	orderitems = items;
	HashMap<String, Integer> Item;
	public Orders(String progress,String address, String dateReceived,int orderNumber, OrderItem[] items){
		progress=progress;
		address=address;
		dateReceived=dateReceived;
		this.orderNumber=orderNumber;
		this.Item= new HashMap<String,Integer>();
		
	}
	
	public OrderItem getUnfilledItem() {
	for (OrderItem e: orderitems) {
	  if (e.inBin) continue;
	  return e;
	  }
	isFilled = true;  // because all OrderItems are in bin
	return null; // if no needed OrderItem can be found
   	 }
 	 public String getAddress() { return address; }
  	public OrderItem[] getOrderItems() { return orderitems; }
	
	
	
	public void ship(String dateReceived){
	
	}
	public void cancel(){
		
	}
	
	public void changeProgress(){
		
	}//
	public void returningprocess(){
		
	}
	public void addItems(){
		
	}
	public void getProgress(){
		
	}//
	
	
}
