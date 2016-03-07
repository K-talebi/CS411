import java.util.*;

public class Board {
	public final static int BOARD_SIZE = 8;
	private String [][] board = new String [BOARD_SIZE][BOARD_SIZE];
	private Queen[] queens = new Queen[8];
	
	public Board() {
		resetBoard();
	}

	
	public Board(Board original){
		for(int i = 0; i < original.getListOfQueens().length; i++){
			this.queens[i] = original.getListOfQueens()[i];
		}
		for(int i = 0; i < BOARD_SIZE; i++){
			for (int n = 0; n < BOARD_SIZE; n++){
				this.board [i][n] = original.getBoard()[i][n];
			}
		}
	}
	
	
	public void shuffleBoard() {
		Random random = new Random();
		resetBoard();
		for(int i = 0; i < BOARD_SIZE;i++){
			boolean placed = false;
			while(!placed){
				int row; 
				int col;
				row = random.nextInt(BOARD_SIZE);
				col = i;
				if (placeQueen(col, row)){
					placed = true;
				}
				System.out.println(row +"    "+ col);
			}
		}
	}

	public void resetBoard(){
		for(int row = 0; row < BOARD_SIZE; row++){
			for(int col = 0; col < BOARD_SIZE; col++){
			board[row][col] = "0";	
			}
		}
	}
	
	public boolean noQueenThere(int x, int y){
		if(board[x][y] == "*"){
			return false;
		}
		else{
			return true;
		}
	}
	public boolean placeQueen(int x, int y){
		Queen newQueen = new Queen(x,y);
		if(board [x][y] == "0"){
			board[x][y] = "*";
			queens[x] = newQueen;
			return true;
		}
		else if(board[x][y] == "*"){
			System.out.println("Already a queen there");
			return false;
		}
		System.out.println("error when placing queen");
		return false;
	}
	
	public void removeQueen(int x, int y){
		Queen oldQueen = new Queen(x,y);
		
		if(board [x][y] == "*"){
			board [x][y] = "0";
			queens[x] = null;
		}
		else if(board [x][y] == "0"){
			System.out.println("There is no queen there");
		}
	}
	
	public int getHeuristic()
    {
		Queen curr, check;
        int result = 0;
        
        //checks to see if queen position conflicts with another queen
        for(int i = 0; i < queens.length; i++){
        	curr = queens[i];
        	for(int n = i+1; n < queens.length; n++){
        		check = queens[n];
        		if(curr.getX() == check.getX()){
        			result++;
        		}
        		if(curr.getY() == check.getY()){
        			result++;
        		}
        		if(Math.abs(curr.getX() - check.getX()) == Math.abs(curr.getY() - check.getY())){
        			result++;
        		}
        	}
        }
        return result;
    }
	
	
	public Queen[] getListOfQueens(){
		return this.queens;
	}
	
	public String[][] getBoard(){
		return this.board;
	}
	
	public void setListOfQueens(Queen [] queens){
		this.queens = queens;
	}
	
	public String toString(){
        String boardDisplay = "";
        for (int row = 0; row < BOARD_SIZE; row++){
            for (int col = 0; col < BOARD_SIZE; col++){
                boardDisplay += board[col][row] + " ";
            }
            boardDisplay +="\n";
        }
        return boardDisplay;
    }
}
