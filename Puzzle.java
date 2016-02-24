private final static  int PUZZLE_SIZE = 9;
	//save is used to solve the puzzle using both heuristics
	private Tile [] puzzle;
	private Tile [] save;
	private int currentCost, costToFinish;
	private boolean isManhattan;
	//used to keep track of which pieces can move
	private int movablePiece;
	private Puzzle parent;
	private int storeLastMove = 0;
	
	//The Goal Puzzle
	public Puzzle() {
		puzzle = new Tile[PUZZLE_SIZE];
		
		for (int i =0; i < PUZZLE_SIZE; i++){
			puzzle[i] = new Tile (i,i);
		}
		currentCost = 0;
		costToFinish = calculateCostToFinish();
		movablePiece = 0;
		parent = null;
	}
	
	public Puzzle (Tile [] puzzle){
		this.puzzle = new Tile[PUZZLE_SIZE];
		System.arraycopy(puzzle, 0, this.puzzle, 0, puzzle.length);
	}
	
	public void savePuzzle(){
			save = new Tile [9];
		System.arraycopy(puzzle, 0, save, 0, puzzle.length);
	}
	
	public void restorePuzzle(){
		System.arraycopy(save, 0, puzzle, 0, save.length);
	}
	
	public int getCurrentCost(){
		return currentCost;
	}
	
	public int getCostToFinish(){
		return costToFinish;
	}
	
	public boolean getHeurisitc(){
		return isManhattan;
	}
	
	public void setHeuristic(boolean isManhattan){
		this.isManhattan = isManhattan;
	}
	
	//check which heuristic is currently being used, then calculate the cost according to that heuristic
	public int calculateCostToFinish(){
		if (isManhattan){
			return calculateManhattanHeuristic();
		}
		else{
			return calculateMisplacedHeuristic();
		}
	}
	
	private int calculateManhattanHeuristic(){
		int cost = 0;
		for(int i = 0; i < puzzle.length; i++){
			cost += puzzle[i].getManhattanDistance();
		}
		return cost;
	}
	
	private int calculateMisplacedHeuristic(){
		int cost = 0;
		for(int i = 0; i < puzzle.length; i++){
			if (!puzzle[i].isProperPosition() && puzzle[i].getValue() != 0){
				cost ++;
			}
		}
		return cost;
	}
	
	//starts a puzzle solved, and shuffle it randomly 
	//so that it can be solved in k moves or less
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
	
	private void switchTiles(int p1, int p2){
		Tile temp = puzzle[p1];
		puzzle[p1] = puzzle[p2];
		puzzle[p2] = temp;
		
		puzzle[p1].setPosition(p2);
		puzzle[p2].setPosition(p1);
		
		//check for the movable piece
		if (puzzle[p1].getValue() == 0){
			movablePiece = p1;
		}
		//If the first tile was not the 0 value, it must be the 2nd!
		else{
			movablePiece = p2;
		}
		//update cost and the heuristic cost to finish
		costToFinish = calculateCostToFinish();
		currentCost ++;
	}
	
	//checks if the movable tile can be moved in the direction.
	//if it can, call switchTiles.
	public void moveUp(){
		if(canMoveUp()){
			switchTiles(movablePiece, movablePiece - 3);
		}
	}
	
	public void moveDown(){
		if (canMoveDown()){
			switchTiles(movablePiece, movablePiece + 3);
		}
	}
	
	public void moveLeft(){
		if (canMoveLeft()){
			switchTiles(movablePiece, movablePiece-1);
		}
	}
	
	public void moveRight(){
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
	
	//Used to compare puzzles
	public Tile[] getArray(){
		return puzzle;
	}
	
	//compares arrays of 2 tile arrays I.E. Puzzles
	public boolean equals(Object o){
		boolean equal = true;
		Tile[] puzzleObj = ((Puzzle) o).getArray();
		for(int i = 0 ; i < PUZZLE_SIZE;i++){
			if(puzzleObj[i].getValue() != puzzle[i].getValue()){
				equal = false;
			}
		}
		return equal;
	}
	
	//Used to create children nodes that are linked to the parent, that have the same tile layout as parent
	public Puzzle(Puzzle parent){
		puzzle = new Tile[PUZZLE_SIZE];
		System.arraycopy(parent.getTileArray(), 0, puzzle, 0, puzzle.length);
		this.parent = parent;
		currentCost = parent.getCurrentCost();
		costToFinish = parent.calculateCostToFinish();
		movablePiece = parent.getMovablePiece();
		this.isManhattan = parent.getHeurisitc();
	}
	
	//Used to tell child which piece can be moved
	public int getMovablePiece(){
		return movablePiece;
	}
	
	//Made so i can copy tiles from parent puzzle to child puzzle
	public Tile [] getTileArray(){
		return puzzle;
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
