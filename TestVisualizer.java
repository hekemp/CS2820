package production;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zhaoxinglu
 */
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
public class TestVisualizer {
 /**
 *
 * @author zhaoxinglu
 * @param Floor, which initialize the Floor size to be 7
 * two initializer,both of them call the setup method to 
 * print out the basic information
 */
   Floor x=new Floor(7);
   int count=0;
   MockRobot m;
   MockBelt b=new MockBelt(x);
   MyPanelTest mypanel;
   JFrame f = new JFrame("Warehouse");
    
  public TestVisualizer(Floor x1,MockRobot m1,MockBelt b1){
      x=x1;
      m=m1;
      b=b1;
      mypanel=new MyPanelTest(x,m,b);
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
              
  
         m.TrackRobot(n);
         b.TrackItem(n);
         
         System.out.println("Robot now is at"+m.robot);
         System.out.println("bin is at"+b.item);
        
         
         if(b.item.y==x.packer.y){
             System.out.println("bin is packed");
         }
         else if(b.item.y==x.shipping.y){
             System.out.println("bin is ready to ship");
         }
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
        
        f.add(mypanel); 
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
    public static void main(String[] argus){
        Floor x1=new Floor(7);
        MockRobot m1=new MockRobot(x1);
        MockBelt b1=new MockBelt(x1);
        TestVisualizer v=new TestVisualizer(x1,m1,b1);
        
        for (int i=0;i<=11;i++){
          v.tick(i);
        }
    }
    
}
class MyPanelTest extends JPanel {
    
    Floor m;
    MockBelt b=new MockBelt(m);
    MockRobot r1;
    
       
 /**
 *
 * @author zhaoxinglu
 * @param m is an interface implemented by the floor class
 * Mypanel can get information of warehouse from it
 * @param b is a object of MockBelt class, Mypanel class can get track of the item on the belt
 * @param r is a object of MockRobot class,Mypanel class can get coordinate of the robot
 * all mock class has been written for convinient testing
 */
  
    
    public MyPanelTest(Floor m1,MockRobot r2,MockBelt b1) {
        m=m1;
         b=b1;
         r1=r2;
        
        
    }
    /**
 *
 * @author zhaoxinglu
 * @input Floor, which initialize the instance variable m
 * MyPanel initializer set GUI border to be black
 * also calls setup method 
 */
    
    
    public void paintComponent(Graphics g) {
       
       g.drawRect(10,10,m.floorSize()*20,m.floorSize()*20);//warehouse 
       g.drawRect(10, 10, 20, (m.getBelt().size()-1)*20);//belt
       for(int i=0;i<m.shelf.length;i++){
          g.drawRect(m.shelf[i].x*20+10, m.shelf[i].y*20+10, 20, 20);}//shelf
      
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
       g.drawOval(r1.robot.x*20+13, r1.robot.y*20+13, 15, 15);//robot
       g.setColor(Color.GRAY);
       g.drawRect(b.item.x*20+10, b.item.y*20+10, 20, 20);//item
       
  }
}
    /*
    
    */
 
       


