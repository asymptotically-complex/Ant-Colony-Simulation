import java.util.*;

public class Forager extends Ant{
	private boolean carryingFood; 
	//stores history in stack..popped off every turn if carrying food
	private Stack<ColonyNode> history = new Stack<>();
	private ColonyNode central;
	private Random r = new Random();
	
	public Forager(LinkedList<Ant> antList, ColonyNode[][] colonyArray) {
		this.id = antList.size(); 
		type = 1; 
		age = 1; 
		maxAge = 3650; 
		x = 13; 
		y = 13; 
		isAlive = true;
		carryingFood = false;
		central = colonyArray[13][13];
		history.push(central); 
		antList.add(this); 
		colonyArray[x][y].increaseForagerNumber(); 
	}
	@Override
	public void setDead(int a, int b, ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		this.isAlive = false;
		//if dead while carrying food. increase food level of that node
		colonyArray[a][b].decreaseForagerNumber();
		if (carryingFood) {
			colonyArray[a][b].addFood(); 
		}
		antList.remove(this); 
	}
	//finds pheremone options and regular options and prefers pheremones if node is visible 
	public ArrayList<ColonyNode> possibleMoves(int X, int Y, ColonyNode[][] colonyArray) {
		ArrayList<ColonyNode> regularOptions = new ArrayList<>();
		ArrayList<ColonyNode> pheromoneOptions = new ArrayList<>();
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if ( i != 0 || j != 0) {
					int a = X + i; 
					int b = Y + j; 
					
					if (a >= 0 && a <= 26 && b >= 0 && b <=26) {
						ColonyNode temp = colonyArray[a][b];
						if (temp.nodeVisibility) {
							if (temp.pheromoneLevel > 0) {
									pheromoneOptions.add(temp);
							}else {
									regularOptions.add(temp);
							}
						}
					}
				}
			}
		}
		//preferance to pheromone options
		if (pheromoneOptions.size() > 0) {
			return pheromoneOptions; 
		}else {
			return regularOptions; 
		}
	}

	//regular move forward while pushing the nodes
	public void moveforward(ColonyNode[][] colonyArray) {
		ArrayList<ColonyNode> moves = possibleMoves(x, y, colonyArray); 
		
		if(!moves.isEmpty()) {
			colonyArray[x][y].decreaseForagerNumber(); 
			int rand = r.nextInt(moves.size()); 
		
			x = moves.get(rand).xcoordinate; 
			y = moves.get(rand).ycoordinate; 
		 
			colonyArray[x][y].increaseForagerNumber(); 
			history.push(colonyArray[x][y]);
		
			if (colonyArray[x][y].foodLevel > 0 && (x != 13 || y !=13)) {
				colonyArray[x][y].removeFood(); 
				carryingFood = true; 
			}
		}
	}
	//popping off nodes every turn until empty
	public void movebackward(ColonyNode[][] colonyArray) {
		if(!history.empty()) {
			ColonyNode yo = history.pop(); 
		
			if (yo.xcoordinate == 13 && yo.ycoordinate == 13) {
				colonyArray[x][y].decreaseForagerNumber(); 
				colonyArray[x][y].increasePheromoneLevel();
				carryingFood = false; 
				
				x = yo.xcoordinate; 
				y = yo.ycoordinate; 
		
				colonyArray[x][y].increaseForagerNumber();
				//need to push the central node if the forager is already at the place. 
				history.push(colonyArray[13][13]);
				colonyArray[13][13].addFood();
			}else {
				colonyArray[x][y].decreaseForagerNumber();
				colonyArray[x][y].increasePheromoneLevel();
			
				x = yo.xcoordinate; 
				y = yo.ycoordinate; 
		
				colonyArray[x][y].increaseForagerNumber();
			}
		}
	}
	@Override
	public void turnmove(ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		// TODO Auto-generated method stub
		if(age >= maxAge) {
			setDead(x, y, colonyArray, antList);
			
		}else {
			increaseAge(); 
			if (carryingFood) {
				movebackward(colonyArray);	
			}
			else {
				moveforward(colonyArray);
			}
		}
	}
}
