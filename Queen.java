import java.util.LinkedList;

public class Queen extends Ant{ 
	public Queen(LinkedList<Ant> antList, ColonyNode[][] colonyArray) {
		//usual attributes. 
		this.id = antList.size(); 
		type = 0; 
		age = 1; 
		maxAge = 73000;
		x = 13; 
		y = 13; 
		isAlive = true; 
		//adds queen to the masterlist
		antList.add(this);
		//sets food automatically and sets queen presence and sets visibility
		colonyArray[13][13].setFood(1000);
		colonyArray[13][13].setQueenPresence(true);
		colonyArray[13][13].setVisibility(true);
	}
	
	@Override
	public void setDead(int a, int b, ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		//if queen dies simulation ends, so no need to iterate through list, 
		this.isAlive = false; 
		colonyArray[x][y].setQueenPresence(false);
	}
	
	@Override
	public void turnmove(ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		// TODO Auto-generated method stub
		//if foodlevel is zero ant dies
		if(age >= maxAge || colonyArray[13][13].foodLevel == 0) {
			setDead(x, y, colonyArray, antList);		
		}
		else {
			//ant symbolically eats food by removing every turn
			colonyArray[13][13].removeFood();
			increaseAge();
		}
	}
}
