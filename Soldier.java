import java.util.*; 

public class Soldier extends Ant{
	boolean scoutMode;
	private Random r = new Random(); 
	
	public Soldier(LinkedList<Ant> antList, ColonyNode[][] colonyArray) {
		//same attributes and operations as all ants. 
		this.id = antList.size(); 
		type = 3; 
		age = 1; 
		maxAge = 3650; 
		x = 13; 
		y = 13; 
		isAlive = true;
		antList.add(this); 
		colonyArray[x][y].increaseSoldierNumber(); 
	}
	
	//sets ant dead and removes it from the view, modeland the list
	public void setDead(int a, int b, ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		this.isAlive = false; 
		colonyArray[a][b].decreaseSoldierNumber(); 
		antList.remove(this); 
	}
	//same as bala ants but the killeroptions now look for bala 
	public ArrayList<ColonyNode> possibleMoves(int X, int Y, ColonyNode[][] colonyArray) {
		ArrayList<ColonyNode> regularOptions = new ArrayList<>();
		ArrayList<ColonyNode> killerOptions = new ArrayList<>(); 
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if ( i != 0 || j != 0) {
					int a = X + i; 
					int b = Y + j; 
					
					if (a >= 0 && a <= 26 && b >= 0 && b <=26) {
						ColonyNode temp = colonyArray[a][b]; 
						if (temp.balaNumber > 0) {
							killerOptions.add(temp); 	
						}else {
							regularOptions.add(temp); 
						}
					}
				}
			}
		}
		if (killerOptions.size() > 0) {
			return killerOptions; 
		}else {
			return regularOptions; 
		}
	}
	//same method as bala ant 
	public void killBala(int x, int y, ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		int killChance = r.nextInt(100); 
		
		if (killChance > 50) {
			for (int i = 0; i < antList.size(); i++) {
				Ant a = (Ant)antList.get(i);
				if (a.getX() == x && a.getY() ==y ) {
					a.setDead(x, y, colonyArray, antList); 
					break; 
				}
			}
		}
	}
	
	//same method as bala ant
	public void move(ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		ArrayList<ColonyNode> moves = possibleMoves(x, y, colonyArray);  
		
		colonyArray[x][y].decreaseSoldierNumber(); 
		int rand = r.nextInt(moves.size());
		
		x = moves.get(rand).xcoordinate; 
		y = moves.get(rand).ycoordinate; 
		
		colonyArray[x][y].increaseSoldierNumber(); 
		colonyArray[x][y].setVisibility(true);
		
		if (colonyArray[x][y].balaNumber > 0) {
			killBala(x, y, colonyArray, antList); 
		}	
	}
	//same for all ants
	
	@Override
	public void turnmove(ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		// TODO Auto-generated method stub
		if (age >= maxAge) {
			setDead(x, y, colonyArray, antList);  
		}else {
			move(colonyArray, antList);
			increaseAge(); 
		}
	}
}
