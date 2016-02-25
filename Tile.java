
public class Tile {
	private int value, position, manhattanDistance;
	
	public Tile(int value, int position){
		this.value = value;
		this.position = position;
	}
	
	public int getValue(){
		return value;
	}
	
	public int getPosition(){
		return position;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public void setPosition(int position){
		this.position = position;
	}
	
	public int getManhattanDistance(){
		return (manhattanDistance = calculateManhattanDistance());
	}
	
	public int calculateManhattanDistance(){
		int xValue = value % 3;
		int xPos = position % 3;
		
		int yValue = value/3;
		int yPos = position/3;
		
		this.manhattanDistance = Math.abs(xValue - xPos) + Math.abs(yValue - yPos);
		
		return (manhattanDistance);
	}
	
	public boolean isInPosition(){
		return(value == position);
	}
	
	
	public boolean equals(Object object){
		Tile checkObject = (Tile) object;
		if(checkObject.getValue() == this.getValue()){
			return true;
		}
		return false;
	}
	
	public String toString() {
        return (""+this.value);
    }
}
