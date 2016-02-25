import java.util.*;
public class Puzzle implements Comparable<Puzzle> {
	private Tile [] puzzle;
	boolean isManhattan;
	private int movablePiece;
	private int currentCost;
	private int costToFinish;
	private int storeLastMove;
	private Puzzle parent;
	
	//Create a solved puzzle
	public Puzzle(){
		puzzle = new Tile[9];
		for(int i = 0 ; i < 9; i++){
			puzzle [i] = new Tile(i,i);
		}
		movablePiece = 0;
		calculateCostToFinish();
	}
	
	//create a dupe of the parent for going down tree
	public Puzzle(Puzzle parent){
		puzzle = new Tile[9];
		Tile[] parentPuzzle = parent.getTileArray();
		for(int i =  0; i < 9; i++) {
			puzzle[i] = new Tile(parentPuzzle[i].getValue(), parentPuzzle[i].getPosition());
		}
		this.parent = parent;
		currentCost = parent.getCurrentCost();
		costToFinish = parent.calculateCostToFinish();
		this.movablePiece = parent.getMovablePiece();
		this.isManhattan = parent.getHeuristic();
	}
	
	public Puzzle(Tile [] dupedTiles){
		this.puzzle = new Tile[9];
		for (int i = 0; i < 9; i++) {
			Tile tile = dupedTiles[i];
			this.puzzle[i] = new Tile(tile.getValue(), tile.getPosition());
		}
	}
	
	//create an array du
	public Puzzle dupe(){
		Puzzle dupedPuzzle;
		return dupedPuzzle = new Puzzle(this.getTileArray());
		
	}
	
	private void setMovablePiece(int movablePiece){
		this.movablePiece = movablePiece;
	}
	
	public int getMovablePiece(){
		this.movablePiece = this.findMovablePiece();
		return this.movablePiece;
	}
	
	public int findMovablePiece() {
		Tile [] test = this.getTileArray();
		int movePos = 0;
		for(int i = 0 ; i < 9; i ++){
			if(test[i].getValue() == 0){
				movePos = test[i].getPosition();
			}
		}
		return movePos;
	}

	public void setHeuristic(boolean isManhattan){
		this.isManhattan = isManhattan;
	}
	
	public boolean getHeuristic(){
		return this.isManhattan;
	}
	
	public boolean isManhattan(){
		return this.isManhattan;
	}
	
	public Tile [] getTileArray(){
		return puzzle;
	}
	
	//Cost calculations
	public int calculateCostToFinish(){
		if(isManhattan){
			return calculateManhattanHeuristic();
		}
		else{
			return calculateMisplacedHeuristic();
		}
	}
	
	public int calculateManhattanHeuristic(){
		int cost = 0;
		for(int i = 0; i < 9; i++){
			if(puzzle[i].getValue()!= 0){
				cost += puzzle[i].calculateManhattanDistance();
			}
		}
		return cost;
	}
	
	public int calculateMisplacedHeuristic(){
		int cost = 0;
		for ( int i = 0; i < 9; i++){
			if(puzzle[i].isInPosition()){
			//do nothing, this is good	
				//cost++;
			}
			else{
				cost++;
			}
		}
		return cost;
	}
	
	public int getCurrentCost(){
		return currentCost;
	}
	
	public int getCostToFinish(){
		costToFinish = calculateCostToFinish();
		return costToFinish;
	}
	
	public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) return false;

        boolean eq = true;

        Tile[] objPuz = ((Puzzle) obj).getTileArray();
        for (int i = 0; i < this.puzzle.length && eq; i++) {
            if (!this.puzzle[i].equals(objPuz[i])) eq = false;
        }

        return eq;
    }
	
	//Functions to shuffle puzzle and switch tiles
	public void shuffle(int k){
		//Used when randomizing puzzles so that a move is not directly countered
		Random rng = new Random();
		for (int i = 0 ; i < k; i++){
			int random = rng.nextInt(4);
			while(random == storeLastMove){
				random = rng.nextInt(4);
			}

			switch(random){
			case 0:
				storeLastMove = 1;
				moveUp();
				break;

			case 1:
				storeLastMove = 0;
				moveDown();
				break;

			case 2:
				storeLastMove = 3;
				moveLeft();
				break;

			case 3:
				storeLastMove = 2;
				moveRight();
				break;
			}
		}
		currentCost = 0;
	}

	private void switchTiles(int tile1, int tile2){
//		System.out.println(tile1 + " " + tile2);
		if((puzzle[tile1].getValue() == 0) || (puzzle[tile2].getValue() == 0)){
//			System.out.println("before " + this);
			puzzle[tile1].setPosition(tile2);
			puzzle[tile2].setPosition(tile1);
			
			Tile temp = puzzle[tile1];
			puzzle[tile1] = puzzle[tile2];
			puzzle[tile2] = temp;

//			System.out.println("after " + this);
			
//			int positionTemp = puzzle[tile1].getPosition();
//			puzzle[tile1].setPosition(puzzle[tile2].getPosition());
//			puzzle[tile2].setPosition(positionTemp);

			//find which tile was the movablePiece
			if(puzzle[tile1].getValue() == 0){
				movablePiece = puzzle[tile1].getPosition();
			}
			else if(puzzle[tile2].getValue() == 0){
//				this.setMovablePiece(puzzle[tile2].getPosition());
				movablePiece = puzzle[tile2].getPosition();
			}
			else{
				System.out.println("ERROR! movable piece is lost.");
			}
			//update cost and the heuristic cost to finish
			currentCost++;
			//in search always call getCostToFinish();
			this.calculateCostToFinish();
		}
		else{
//			System.out.println("HOW");
		}
	}
	
	public String toString() {
		String result = "";

		for (int i = 0; i < puzzle.length; i++) {
			result += puzzle[i] + " ";
			if (i % 3 == 2 && i != 8) {
				result += "\n";
			}
		}
		return result;
	}
	//movement functions
		//checks if the movable tile can be moved in the direction.
		//if it can, call switchTiles.
		public void moveUp(){
			getMovablePiece();
			if(canMoveUp()){
				switchTiles(movablePiece, movablePiece - 3);
			}
		}

		public void moveDown(){
			getMovablePiece();
			if (canMoveDown()){
				switchTiles(movablePiece, movablePiece + 3);
			}
		}

		public void moveLeft(){
			getMovablePiece();
			if (canMoveLeft()){
				switchTiles(movablePiece, movablePiece-1);
			}
		}

		public void moveRight(){
			getMovablePiece();
			if (canMoveRight()){
				switchTiles(movablePiece, movablePiece + 1);
			}
		}

		//Methods used to check if the 0 tile can move I.E. can't move up if in the top 3 spots.
		public boolean canMoveUp(){
			if(movablePiece != 0 && movablePiece != 1  && movablePiece !=2 ){
				return true;
			}
			else{
				return false;
			}
		}

		public boolean canMoveDown(){
			if(movablePiece != 6 && movablePiece != 7 && movablePiece != 8){
				return true;
			}
			else{
				return false;
			}
		}

		public boolean canMoveLeft(){
			if (movablePiece != 0 && movablePiece != 3 && movablePiece != 6){
				return true;
			}
			else{
				return false;
			}
		}

		public boolean canMoveRight(){
			if (movablePiece != 2 && movablePiece != 5 && movablePiece != 8 ){
				return true;
			}
			else{
				return false;
			}
		}

		@Override
		public int compareTo(Puzzle arg0) {
			if(this.equals(arg0)){
				return 0;	
			}else if(this.getCostToFinish() > arg0.getCostToFinish()){
				return 1;
			}
			else{
				return -1;
			}
		}
}
