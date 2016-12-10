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
 * This class is used to test only
 */
public class MockBelt {
    Floor m=new Floor(7);
    Point item;
    int location=6;
    /**
 *
 * @author zhaoxinglu
 * @param Point, which represent the coordinates of item on the belt
 * It provide useful information to Visulaizer 
 */
    public MockBelt(Floor m1){
        m=m1;
        item=new Point(0,6);
        
    }
    /**
 *
 * @author zhaoxinglu
 * A constructor for mock belt 
 * 
 */public void move(Point P){
       item=P;
   }
    public void TrackItem(int n){
        if (n<6){
            location=location-1;
           Point x1=new Point(0,location);
        move(x1);}
       
   
}
    
 /**
 *
 * @author zhaoxinglu
 * @return Point, the coordinate of item
 * 
 */
    
}

