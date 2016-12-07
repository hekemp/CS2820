
package production;

/**
 * 
 * @author Mouna Elkeurti
 *
 */



public class Bin {
  Order order; 
  
  public Bin() {}
	  
  public Bin getBin(){
	  Bin bin = new Bin();
	  return bin;
  }
  
  public String toString() { return "Bin"; }
}
