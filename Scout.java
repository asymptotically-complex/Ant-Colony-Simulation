import java.util.*;

public class Scout extends Ant {
	private Random r = new Random(); 
	
	public Scout(LinkedList<Ant> antList, ColonyNode[][] colonyArray) {
		//same atrributes init
		this.id = antList.size(); 
		type = 2; 
		age = 1; 
		maxAge = 3650; 
		x = 13; 
		y = 13; 
		isAlive = true;
		antList.add(this); 
		colonyArray[x][y].increaseScoutNumber();
	}
	@Override
	public void setDead(int a, int b, ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		this.isAlive = false; 
		colonyArray[a][b].decreaseScoutNumber();
		antList.remove(this); 
	}
	//same as all ants
	public ArrayList<ColonyNode> possibleMoves(int X, int Y, ColonyNode[][] colonyArray) {
		ArrayList<ColonyNode> options = new ArrayList<>(); 
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if ( i != 0 || j != 0) {
					int a = X + i; 
					int b = Y + j; 
					
					if (a >= 0 && a <= 26 && b >= 0 && b <=26) {
						options.add(colonyArray[a][b]);
					}
					
				}
			}
		}
		return options; 
	}
	//same as all ants
	public void move(ColonyNode[][] colonyArray) {
		if (this.isAlive()) {
			ArrayList<ColonyNode> possibleMoves = possibleMoves(x, y, colonyArray); 
		
			if (possibleMoves.size() > 0 ) {
				colonyArray[x][y].decreaseScoutNumber(); 
		
				int random = r.nextInt(possibleMoves.size());
		
				x = possibleMoves.get(random).xcoordinate; 
				y = possibleMoves.get(random).ycoordinate; 
		
				colonyArray[x][y].increaseScoutNumber(); 
				colonyArray[x][y].setVisibility(true);
			}
		}
	}
	@Override
	public void turnmove(ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		// Same as all ants
		if (age >=maxAge) {
			setDead(x, y, colonyArray, antList); 
		}else {
			move(colonyArray);
			increaseAge();
		}
	}
}
