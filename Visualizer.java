
package production;

import java.awt.Color;
import java.awt.Graphics;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zhaoxinglu
 * Visualizer class is use Java Swing to show a picture of
 * what is happening in the warehouse
 * implements Event interface to be called by the Master class
 */
public class Visualizer implements Event{
 /**
 *
 * @author zhaoxinglu
 * @param Floor, which initialize the Floor size to be 7
 * two initializer,both of them call the setup method to 
 * print out the basic information
 */
   Floor x=new Floor(7);
   int count=0;
   Robot m;
   MockBelt b=new MockBelt();
   MyPanel mypanel1;
    JFrame f = new JFrame("Warehouse");
   
   
  public Visualizer(){
       setup(); 
       createAndShowGUI(); }//default initializer
  
  public Visualizer(Floor x1,Robot m1){
      x=x1;
      m=m1;
      mypanel1=new MyPanel(x,m);
      setup();
      createAndShowGUI();
      try {
            // Let the warehouse fully initialize before continuing
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      
      
  }
  /**
 *
 * @author zhaoxinglu
 * setup method just print out where fixed things are in the warehouse
 */
  public void setup(){
        
        System.out.println("Warehouse size is:"+x.floorSize());
        System.out.println("Belt information");
        System.out.println("Picker at:"+x.getPicker());
        System.out.println("Packer at:"+x.getPacker());
        System.out.println("Shipping dock at"+x.shipping());
        System.out.println("Receiving Dock at"+x.getReceving());
        System.out.println("Charger at"+x.getCharger());
        System.out.println("Belt area"+x.getBelt());
        
        for(int i=0;i<x.getShelf().size();i++){
            
          System.out.println("Shelf "+i+" is at"+x.getShelf().get(i));}//shelf
        
        
    }
  
   /**
 *
 * @author zhaoxinglu
 * @input int, which represents the total tick number
 * this method call create and show GUI method every tick
 */
  public void tick(int n){
          String display = "Tick %d";
	
         System.out.println(String.format(display,n));
        
         System.out.println("Robot now is at"+m.getLocation());
         
         //System.out.println("Item1 is at:"+b.TrackItem());
         createAndShowGUI();
         
         
    }
  
  /**
 *
 * @author zhaoxinglu
 * @param f is an instance of JFram class,it creates a window 
 * that has decorations such as a border, a title, and supports button components. 
 * we use a constructor that lets us set the frame title "warehouse"
 * we use add method to add the content designed by ourselves
 * The EXIT_ON_CLOSE operation exits the program when user closes the frame
 */

    private void createAndShowGUI() {
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        f.add(mypanel1); 
        f.setSize(400,400);
        f.repaint();
        f.setVisible(true);
        try {
            // Let the warehouse fully initialize before continuing
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } 
    
    
	/**
	 * @author zhaoxinglu 
	 * Performs an action. Extracts the method to be executed and parameters from the method strong and then executes these
	 * methods.
	 * @input - a string which contains a method name/key and parameters, seperated by commas
	 */
	
	 public void performAction(String Method){
            
             tick(count);
             count++;
         }
                
	
	/**
	 * @author zhaoxinglu
	 * Gets the event. Returns this object as an Event
	 */
	
	public Event getEvent(){
            return (Event)this;};
	
	/**
	 * @author zhaoxinglu
	 * Gets the parameters. Returns the parameter for the next call of this method.
	 */
	public String getPara(){return "";};

    
    
       
    }



/**
 *
 * @author zhaoxinglu
 * MyPanel class collect warehouse data and visualizes it on the panel
 */
class MyPanel extends JPanel {
    
    Floor m;
    MockBelt b=new MockBelt();
    Robot r1;
    
       
 /**
 *
 * @author zhaoxinglu
 * @param m is an object of the floor class
 * Mypanel can get information of warehouse from it
 * @param b is a object of MockBelt class, Mypanel class can get track of the item on the belt
 * @param r is a object of Robot class,Mypanel class can get coordinate of the robot
 * all mock class has been written for convinient testing
 */
  
    
    public MyPanel(Floor m1,Robot r2) {
        m=m1;
          
         r1=r2;
        
        
    }
    /**
 *
 * @author zhaoxinglu
 * @input Floor, which initialize the instance variable m
 * @input Robot, which initialize the instance variable of robot class r1
 * 
 */
    
    
    public void paintComponent(Graphics g) {
       ArrayList<Point> sf=m.getShelf();
       g.drawRect(10,10,m.floorSize()*20,m.floorSize()*20);//warehouse 
       g.drawRect(10, 10, 20, (m.getBelt().size()-1)*20);//belt
       for(int i=0;i<m.getShelf().size();i++){
          g.drawRect(sf.get(i).x*20+10, sf.get(i).y*20+10, 20, 20);}//shelf
      
       g.setColor(Color.green);
       g.drawLine(m.getReceving().x*20+10,m.getReceving().y*20+10,m.getReceving().x*20+30,m.getReceving().y*20+10);//draw for receiving dock
       g.setColor(Color.yellow);
       g.drawLine(10,10,30,10);//draw for shipping dock
       g.setColor(Color.blue);
       g.drawRect(m.getCharger().x*20+10,m.getCharger().y*20+10,20,20);//draw for robot charge
       g.setColor(Color.PINK);
       g.drawRect(m.getPicker().x*20+10, m.getPicker().y*20+10,20, 20);//picker
       g.drawRect(m.getPacker().x*20+10, m.getPacker().y*20+10, 20, 20);//packer
         g.setColor(Color.cyan);
       g.drawOval(r1.getLocation().x*20+13, r1.getLocation().y*20+13, 15, 15);//robot
       g.setColor(Color.GRAY);
       g.drawRect(b.TrackItem().x*20+10, b.TrackItem().y*20+10, 20, 20);//item
       
  }
    /*
*
* @author zhaoxinglu
* @param Graphics
* paint method mainly draw the warehouse according to the floor class 
*/
}



