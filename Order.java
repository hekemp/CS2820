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
	
	ArrayList<item>orderItemList;
	
	public Order(String progress,String address, String dateReceived,int orderNumber, OrderItem[] items){
		progress=progress;
		address=address;
		dateReceived=dateReceived;
		this.orderNumber=orderNumber;
		orderitems=items;
		this.orderItemList= orderItemList;
		
	}
	
	public OrderItem getUnfilledItem() {
	for (OrderItem e: orderitems) {
	  if (e.inBin) continue;
	  return e;
	  }
	isFilled = true;  // because all OrderItems are in bin
	return null; // if no needed OrderItem can be found
   	 }
	
	public void itemadding(OrderItem e) {
		if(e.inBin){
		orderItemList.add(e);}
	}
	
	public void itemremoving(OrderItem e){
		orderItemList.remove(e);
		
	}
	
	public boolean itemcontaining(OrderItem e){
		if (orderItemList.contains(e)){
			return true;
		}
		else{
			return false;
		}
			
		
	}
	
	
	
	public String showAddress() { return address; }
  	public OrderItem[] getOrderItems() { return orderitems; }
	public String getProgress(){
		return progress;}
	public String shippeddate(){return dateRecieved;}
	public Integer OrderNumber(return orderNumber;)


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
