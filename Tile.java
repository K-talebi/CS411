
public class Tile {

	private int value;
	private int manhattanDistance;
	private int position;
	
	public Tile(int value, int position) {
		this.value = value;
		this.position = position;
		this.manhattanDistance = calculateManhattanDistance();
	}
	
	public int getValue(){
		return value;
	}
	
	public int getPosition(){
		return position;
	}
	public int getManhattanDistance(){
		manhattanDistance = calculateManhattanDistance();
		return manhattanDistance;
	}
	
	public void setPosition(int position){
		this.position = position;
	}
	
	public boolean isProperPosition(){
		if (value == position){
			return true;
		}else{
			return false;
		}
	}
	
	private int calculateManhattanDistance(){
		int distance;
		
		//where the tile is suppose to be , final pos
		int xValue = this.value % 3;
		int yValue = this.value / 3;
		
		//where the tile is suppose to be, current pos
		int xPos = this.position % 3;
		int yPos = this.position / 3;
		// manhattan distance = abs(x1-x0) + abs(y1-y0)
		distance = Math.abs(xValue - xPos) + Math.abs(yValue - yPos);
		return distance;
	}
	
	public String toString() {
        return (""+this.value);
    }
	
	public boolean equals(Object o){
		Tile checkObj = (Tile)o;
		
		if(this.getValue() == (checkObj.getValue())){
			return true;
		}else{
			return false;
		}
	}
}
