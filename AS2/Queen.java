public class Queen {
    private int x, y;
    public Queen (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //returns the x value on the board
    public int getX() {
        return x;
    }
    //returns the y value on the board
    public int getY() {
        return y;
    }
    
    public String toString(){
    	return"(x: " + x +" y: " + y+")";
    }
}
