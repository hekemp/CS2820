package production;
/**
 *
 * @author zhaoxinglu
 * This is an interface for Visualizer
 */
public interface GetVisualizer {
   
    void setup();
    void tick(int n);
    public void performAction(String Method);
    Event getEvent();
    public String getPara();
    
    
}
