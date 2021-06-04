import java.util.*;

public class Bala extends Ant { 
	private static Random r = new Random();  
	
	public Bala(LinkedList<Ant> antList, ColonyNode[][] colonyArray) {
		//attributes
		this.id = antList.size(); 
		this.type = 4; 
		this.age = 1; 
		this.maxAge = 3650; 
		this.x = 0; 
		this.y = 0;  
		isAlive = true;
		//adding bala to the antlist
		antList.add(this); 
		//increasing bala count in the array (both model and view). bala is introduced from top-left corner, the colonyArray[0][0] node
		colonyArray[x][y].increaseBalaNumber(); 
	}
	
	public ArrayList<ColonyNode> possibleMoves(int X, int Y, ColonyNode[][] colonyArray) {
		ArrayList<ColonyNode> regularOptions = new ArrayList<>();
		ArrayList<ColonyNode> killerOptions = new ArrayList<>(); 
		
		// iterating through immediate vicinity options. 
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if ( i != 0 || j != 0) {
					int a = X + i; 
					int b = Y + j; 
					
					if (a >= 0 && a <= 26 && b >= 0 && b <=26) {
						ColonyNode temp = colonyArray[a][b]; 
						if (temp.containsQueen || temp.scoutNumber > 0 
						|| temp.foragerNumber > 0 || temp.soldierNumber >0) {
							killerOptions.add(temp); 	
						}else {
							regularOptions.add(temp); 
						}
					}
				}
			}
		}
		//if any non-bala ant are present in immidiate vicinity, killeroptions are returned. Otherwise regular options are returned
		if (killerOptions.size() > 0) {
			return killerOptions; 
		}else {
			return regularOptions; 
		}
	}
	public void killAnt(int x, int y, ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		int killChance = r.nextInt(100); 
		//iterates through the list to kill ants with same X-Y coordinates, dead ant is removed by setdead method, and the ants hatched earlier will be preferred. Queen will be easy to kill this way. 
		if (killChance > 50) {
			for(int i = 0; i < antList.size();i++) {
				Ant a = (Ant)antList.get(i);
				if(a.getX() == x && a.getY() == y) {
					a.setDead(x, y, colonyArray, antList);
					break; 
				}
			}
		}
	}
	
	public void move(ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		ArrayList<ColonyNode> moves = possibleMoves(x, y, colonyArray);
		
		colonyArray[x][y].decreaseBalaNumber(); 
		//bala symbolically moves into one of the random possible places and kills ants in the same turn.  
		int rand = r.nextInt(moves.size()); 
		
		x = moves.get(rand).xcoordinate; 
		y = moves.get(rand).ycoordinate; 
		
		colonyArray[x][y].increaseBalaNumber(); 
		
		if (colonyArray[x][y].containsQueen || colonyArray[x][y].scoutNumber > 0 
			|| colonyArray[x][y].foragerNumber > 0 || colonyArray[x][y].soldierNumber >0) {
			//if options returned had ants, this killmethod kills them 50 % of time
			killAnt(x, y, colonyArray, antList); 
		}					
	}
	@Override
	public void setDead(int a, int b, ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		//setDead removes the ant from the list, model and the view. 
		this.isAlive = false; 
		colonyArray[a][b].decreaseBalaNumber(); 
		antList.remove(this); 
	}
	
	@Override
	public void turnmove(ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		// if ant is attacked by soldier, it is already dead. It is also dead if it's age is greater than maxage during a turn. 
		if (age >= maxAge) {
			setDead(x, y, colonyArray, antList); 
		}else {
			//if bala is not dead, its age increases and moves
			increaseAge();
			move(colonyArray, antList); 
		}
	}
}

