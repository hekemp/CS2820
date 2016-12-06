package production;

/**
 * 
 * @author Heather Kemp
 * The Production class is the true 'main' class which contains the Master object and tells it to run with the objects that it has created.
 * It contains only one method, it's static void main(String[] args) method, which will create the needed objects and then run the simulation.
 *
 */

public class Production
{
    public static void main(String[] args){
        Belt newBelt = new Belt();
        Floor newFloor = new Floor();
        inventory newInventory = new inventory();
        Order newOrder = new Order(String asdf ,String asxcv ,String zxcvb, int p, OrderItem[] orderutemsdfg);
        Robot newRobot = new Robot(0,0,1,100);
        Shelf newShelf = new Shelf();
        Visualizer newVisualizer = new Visualizer();
        
        Master myMaster = new Master();
        myMaster.addBelt(newBelt);
        myMaster.addFloor(newFloor);
        myMaster.addInventory(newInventory);
        myMaster.addOrder(newOrder);
        myMaster.addRobot(newRobot);
        myMaster.addShelf(newShelf);
        myMaster.addVisualizer(newVisualizer);
        myMaster.runSimulation();
    }
}
