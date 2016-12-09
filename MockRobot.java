package production;
import java.util.ArrayList;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zhaoxinglu
 * This class make a fake robot object
 */
public class MockRobot {
    Floor m;
    Point robot=new Point(6,0);
    ArrayList<Point> robotroute=new ArrayList<Point>();
   
    /**
 * @author zhaoxinglu
 * @param Point, indicate the coordinate of robot in the map
 * 
 */
    public MockRobot(Floor m1){
        
        m=m1;
        
       
        
       
        
    }//initialize where robot is originally and continuously move

   public void move(Point P){
       robot=P;
   }
    public Point TrackRobot(int n){
        robotroute=m.getRoute(m.getCharger(),m.getPicker());
        if (n<robotroute.size()){
            move(robotroute.get(n));
        
       }
        return robot;
   }
   /**
 * @author zhaoxinglu
 * @return Point, keep track of where robot is in the map
 */
   
    
}
