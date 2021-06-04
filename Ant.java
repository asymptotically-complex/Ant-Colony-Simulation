import java.util.*;

//Abstract Class ant with shared attributes and methods.
public abstract class Ant { 
	//common attribute of all ants
	int id; 
	int type; 
	int age; 
	int maxAge; 
	int x; 
	int y; 
	boolean isAlive;
	
	//getters and setters
	void setID(int id) {
		this.id = id; 
	}
	int getType() {
		return type; 
	}
	int getAge() {
		return age; 
	}
	void setAge(int a) {
		this.age = a; 
	}
	void increaseAge() {
		age++; 
	}
	int getMaxAge() {
		return maxAge; 
	}
	void setMaxAge(int b) {
		maxAge = b; 
	}
	
	int getX() {
		return x; 
	}
	void setX(int x){
		this.x = x; 
	}
	int getY() {
		return y; 
	}
	void setY(int y) {
		this.y = y; 
	}
	//method to check if Ant is alive
	boolean isAlive() {
		return isAlive; 
	}
	//method to set ants dead, overridden...
	public void setDead(int a, int b, ColonyNode[][] colonyArray, LinkedList<Ant> antList) {
		
	}
	//method to move ants across the 2-d array, also overridden
	void turnmove(ColonyNode[][] colonyArray, LinkedList<Ant> antList) { 
		
	}
}
