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
        Floor newFloor = new Floor();
        //Belt newBelt = new Belt(newFloor);
        inventory newInventory = new inventory();
       // Order newOrder = new Order();
        // Please do not edit code that will not compile into a piece that will compile otherwise
        //Order newOrder = new Order(String asdf ,String asxcv ,String zxcvb, int p, OrderItem[] orderutemsdfg);
        Shelf newShelf = new Shelf();
        Robot newRobot = new Robot(newFloor,newShelf,2,2,100);
        //Visualizer newVisualizer = new Visualizer();
        
        Master myMaster = new Master();
        //myMaster.addBelt(newBelt);
        myMaster.addFloor(newFloor);
        myMaster.addInventory(newInventory);
        //myMaster.addOrder(newOrder);
        myMaster.addRobot(newRobot);
        myMaster.addShelf(newShelf);
        //myMaster.addVisualizer(newVisualizer);
        myMaster.runSimulation();
    }
}
