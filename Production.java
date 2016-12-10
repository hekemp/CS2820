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
        Floor newFloor = new Floor(6);/** @Xinglu Zhao 
         *Here the floor size is better equal to or greater than 7 because of some floor settings,eg.shelfs 
         */
        //Belt newBelt = new Belt(newFloor);
       // Order newOrder = new Order();
        Shelf newShelf = new Shelf(new Point(0, 5));
        Robot newRobot = new Robot(newFloor,newShelf,2,2,100);
        inventory newInventory = new inventory(newFloor, newRobot);
        MockOrder newMockOrder = new MockOrder(newRobot, newInventory, newFloor);
       /// Visualizer newVisualizer = new Visualizer();// Here Visualizer initilizer needs Floor and Robot
        
        Master myMaster = new Master();
       // myMaster.addBelt(newBelt);
        myMaster.addFloor(newFloor);
        myMaster.addInventory(newInventory);
      //  myMaster.addOrder(newOrder);
        myMaster.addRobot(newRobot);
        myMaster.addShelf(newShelf);
        myMaster.addMockOrder(newMockOrder);
       // myMaster.addVisualizer(newVisualizer);
        myMaster.runSimulation();
    }
}
