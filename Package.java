package production;

/**
 * 
 * @author Mouna ELkeurti (Similare to Ted Herman's Bin class)
 *
 */

public class Package {
	item item;
	boolean item_packed;
	
	public Package(){
		item = null;
		item_packed = false;
	}
	public boolean isPacked(){
		return true;
	}
	public void setPacked(){
		item_packed = true;
	}
	

}
