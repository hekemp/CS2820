/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zhaoxinglu
 */
public interface GetVisualizer {
   
    void setup();
    void tick(int n);
    public void performAction(String Method);
    Event getEvent();
    public String getPara();
    
    
}
