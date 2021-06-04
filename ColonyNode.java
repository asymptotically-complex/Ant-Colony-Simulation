import java.util.Random; 


public class ColonyNode{
	
	//colonynode is parallel to the colonynodeview 
	int xcoordinate; 
	int ycoordinate;
	
	int foodLevel;
	int pheromoneLevel;
	
	boolean containsQueen;
	int foragerNumber; 
	int scoutNumber; 
	int soldierNumber; 
	int balaNumber;
	
	String nodeID;  
	ColonyNodeView nodeview;
	boolean nodeVisibility; 
	Random r = new Random(); 
	
	//constructor has nodeview, x coord, and y coord as a parameter, 
	public ColonyNode(ColonyNodeView nview, int x, int y) {
		this.xcoordinate = x; 
		this.ycoordinate = y; 
		nodeview = nview; 
		//initial food is placed randomly
		setFood(); 
		pheromoneLevel = 0; 
		
		containsQueen = false; 
		foragerNumber = 0; 
		scoutNumber   = 0; 
		soldierNumber = 0; 
		balaNumber    = 0; 

		setNodeID(); 
		setVisibility(false);
	}
	
	//methods to set, increase, decrease attributes. 
	public void setNodeID() {
		nodeID = " " + xcoordinate + " " + ycoordinate; 
		nodeview.setID(nodeID);
	}

	public void setVisibility(boolean a) {
		nodeVisibility = a; 
		if (a) {
			nodeview.showNode();
		}else{;
			nodeview.hideNode();
		}
	}

	
	public void setFood() {
		int chance = r.nextInt(100); 
		
		if (chance < 25) {
			foodLevel = 500 + r.nextInt(500); 
			nodeview.setFoodAmount(foodLevel);
		}
	}
	
	public void setFood(int x) {
		foodLevel = x; 
		nodeview.setFoodAmount(x); 
	}
	
	
	//increase and decrease methods for colonynode attributes
	public void addFood() {
		++foodLevel;
		nodeview.setFoodAmount(foodLevel);
	}
	
	public void removeFood() {
		if (foodLevel > 0) {
		--foodLevel;
		nodeview.setFoodAmount(foodLevel);
		}
	}
	
	public void increasePheromoneLevel() {		
		if (pheromoneLevel < 1000) {
			pheromoneLevel += 10;
			nodeview.setPheromoneLevel(pheromoneLevel);
		}
	}
	
	public void decreasePheromoneLevel() {		
			pheromoneLevel /= 2;
			nodeview.setPheromoneLevel(pheromoneLevel);
	}
	
	
	public boolean getQueenPresence() {
		return containsQueen;
	}
	
	public void setQueenPresence(boolean queenPresence) {
		containsQueen = queenPresence;
		nodeview.setQueen(queenPresence);
		
		if (queenPresence) {
			nodeview.showQueenIcon();
		}else {
			nodeview.hideQueenIcon(); 
		}
	}
	
	public void increaseForagerNumber() {
		if (foragerNumber == 0) {
			nodeview.showForagerIcon();
		}
		
		++foragerNumber;
		nodeview.setForagerCount(foragerNumber);
	}
	
	public void decreaseForagerNumber() {
		if (foragerNumber == 1) {
			nodeview.hideForagerIcon();
		}
		
		--foragerNumber;
		nodeview.setForagerCount(foragerNumber);
	}
	
	public void increaseScoutNumber() {
		if (scoutNumber == 0){
			nodeview.showScoutIcon();
		}
		++scoutNumber;
		nodeview.setScoutCount(scoutNumber);
	}
	
	public void decreaseScoutNumber() {
		if (scoutNumber == 1){
			nodeview.hideScoutIcon();
		}
		--scoutNumber;
		nodeview.setScoutCount(scoutNumber);	
	}

	public void increaseSoldierNumber() {
		if (soldierNumber == 0){
			nodeview.showSoldierIcon();
		}
		++soldierNumber;
		nodeview.setSoldierCount(soldierNumber);
	}

	public void decreaseSoldierNumber() {
		if (soldierNumber == 1){
			nodeview.hideSoldierIcon();
		}
		--soldierNumber;
		nodeview.setSoldierCount(soldierNumber);
	}

	
	public void increaseBalaNumber() {
		if (balaNumber == 0){
			nodeview.showBalaIcon();
		}
		++balaNumber;
		nodeview.setBalaCount(balaNumber);
	}
	
	public void decreaseBalaNumber() {
		if (balaNumber == 1){
			nodeview.hideBalaIcon();
		}
		--balaNumber;
		nodeview.setBalaCount(balaNumber);
	}
}
