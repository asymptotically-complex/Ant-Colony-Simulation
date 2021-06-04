
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//instance of gui 
		AntSimGUI gui = new AntSimGUI();
		//instance of sim
		Simulation sim = new Simulation(gui);
		//adding sim to the linked list of simulation-event listeners 
		gui.addSimulationEventListener(sim);
	}
}
