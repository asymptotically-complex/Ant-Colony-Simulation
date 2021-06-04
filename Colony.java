import java.util.*;

public class Colony {
	//attributes of colony
	private Queen queen;
	//type of test initialization 
	private int runType;
	//masterlist is used and iterated every update.
	private LinkedList<Ant> antList;
	//the 27*27 is represented as an array. 
	private ColonyNode[][] colonyArray; 
	//to add all the colonynodeview's to. 
	private ColonyView colonyview; 
	//generate random number
	private Random r = new Random(); 
	
	
	public Colony(ColonyView colonyview) {
		//usual instantiation
		this.colonyview= colonyview;
		this.antList = new LinkedList<>();
		this.colonyArray = new ColonyNode[27][27];
		
		//every instantiation automatically creates the grid and places the queen ant 
		createGrid(); 
		this.queen = new Queen(antList, colonyArray); 
	}
	
	//key to running the upgrade on the model and the view. 
	public boolean queenAlive() {
		return queen.isAlive();
	}
	
	//creat the grid both model and view. nodeviews are not visible at first. 
	public void createGrid() {
		for (int i = 0; i < 27; ++i) {
			for (int j = 0; j < 27; ++j) {
				ColonyNodeView nodeview = new ColonyNodeView();
				// colonynodeview and x and y coordinates are passed. 
				colonyArray[i][j] = new ColonyNode(nodeview, i , j);
				colonyview.addColonyNodeView(nodeview, i, j);
			}
		}
	} 
	
	public void updateGrid(int turns) {
		//updates view and model every turn of the simulation
		
		int balaprobability = r.nextInt(100);
		//balas have 3% chance every turn	
		if(balaprobability < 3 && runType == 0) {
			new Bala (antList, colonyArray); 
		}
		//every turn of the day ants are hatched if they are in appropriate runtype
		if (turns % 10 == 0 && (runType == 0 || runType == 1)) {
			hatchAnt();
		}
		//pheremones are also halved in each node every day
		if (turns % 10 == 0) {
			for (ColonyNode[] y: colonyArray) {
				for (ColonyNode x: y) {
					x.decreasePheromoneLevel();
				}
			}
		}
		//every turn, all the ants move across the 2-D array 
		for (int i = 0; i < antList.size(); ++i) {
			((Ant)antList.get(i)).turnmove(colonyArray, antList);
		}	
	}
	
	//normal run with ants
	public void regularRun() {
		runType = 0; 
		//10 soldiers
		for(int i = 0; i < 10; i++) {
			Soldier soldier = new Soldier(antList, colonyArray);
		}
		
		//5 scouts
		for(int i = 0; i < 4; i++) {
			Scout scout = new Scout(antList, colonyArray);
		}
		//50 foragers
		for(int i = 0; i < 50; i++) {
			Forager forager = new Forager(antList, colonyArray);
		}
		//inital visible grid
		for (int x = 12; x < 15; x++) {
			for(int y = 12; y < 15; y++) {
				colonyArray[x][y].setVisibility(true);
				colonyArray[x][y].setFood(0);
			}
		}
		//foodlevel reset. 
		colonyArray[13][13].setFood(1000); 
	}
	//Init tests
	public void queenTest() {
		runType = 1; 
	}
	
	public void scoutTest() {
		runType = 2; 
		//scout and bala for test
		Scout scout = new Scout(antList, colonyArray); 
		Bala bala = new Bala (antList, colonyArray); 
	}
	
	public void foragerTest() {
		runType = 3; 
		
		//forager for test
		Forager forager = new Forager(antList, colonyArray); 
		
		for (int i = 11; i < 17; i++) {
			colonyArray[i][13].setFood(0);
			colonyArray[i][13].setVisibility(true);
		}
		colonyArray[13][13].setFood(1000);
		colonyArray[16][13].setFood(1000);
	}
	public void soldierTest() {
		runType = 4; 
		//soldier and bala added for test
		Soldier soldier = new Soldier(antList, colonyArray);
		Bala bala = new Bala(antList, colonyArray);

		for (int x = 0; x < 27; x++) {
			for(int y = 0; y < 27; y++) {
				colonyArray[x][y].setVisibility(true);
				colonyArray[x][y].setFood(0);
			}
		}
		colonyArray[13][13].setFood(1000);
	}
	//method used to hatch ant every turn
	public void hatchAnt() {
		int probability = r.nextInt(100);
		//proabilits of ants
		if (probability < 50) {
			Forager forager = new Forager(antList, colonyArray); 
		}
		
		else if (probability > 50 && probability < 75 ) {
			Soldier soldier = new Soldier(antList, colonyArray); 
		}
		else {
			Scout scout = new Scout(antList, colonyArray); 
		}
		
	}
}
