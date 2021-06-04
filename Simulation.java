import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import javax.swing.Timer; 

public class Simulation implements ActionListener, SimulationEventListener{
	//attributes. 
	private int turns;  
	private AntSimGUI gui; 
	private Colony colony;
	//timer used for the continuous upgrades
	private Timer timer = new Timer(100, this);
	//type of test initialization
	String runtype; 
	
	public Simulation(AntSimGUI gui) {
		//Constructor
		this.turns = 0; 
		this.gui = gui; 
		//gui and colony both take view as the arguement
		ColonyView view = new ColonyView(27, 27);
		this.gui.initGUI(view);
		this.colony = new Colony(view);
		
	}
	//timer starts with "run" eventtype 5 button
	public void start() {
		timer.start(); 
	}
	
	//a single turn method with time setting
	public void singleTurn() {
		turns++; 
		gui.setTime(" Type: " + runtype + "    Day: " + turns/10 + "   Turn: " + turns % 10);
		//updates the grid (hatches bala, decreases pheromone, iterates throught the antlist, and so on)
		colony.updateGrid(turns);
	}
	
	@Override
	
	public void simulationEventOccurred(SimulationEvent simEvent) {
		// simulation event switchboard
		int event = simEvent.getEventType(); 
		timer.stop(); 
		switch(event) {
			case 0:  
				turns = 0; 
				runtype = "regular run";
				colony.regularRun();
				break; 
			case 1:  
				turns = 0; 
				runtype = "queen test";
				colony.queenTest();
				break; 
			case 2:  
				turns = 0; 
				runtype = "scout test";
				colony.scoutTest();
				break; 
			case 3: 
				turns = 0; 
				runtype = "forager test";
				colony.foragerTest(); 
				break; 
			case 4: 
				turns = 0; 
				runtype = "soldier test";
				colony.soldierTest(); 
				break; 
			case 5:
				//"run" the simulation forward cont. 
				timer.start();
				break;
			case 6:  
				//one step of simulation
				if (colony.queenAlive()) {
					singleTurn(); 
				}else {
					System.out.println("Queen Dead");
				}
				break;
			default: 
				System.out.println("Invalid event");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// check if queen alive otherwise dead for action event.  
		if (colony.queenAlive()) {
			singleTurn(); 
		}else {
			timer.stop(); 
			System.out.println("Queen dead..Simulation stopped");
		}
	} 
}
