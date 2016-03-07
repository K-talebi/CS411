import java.util.*;

public class Search {

	private int nodeCounter = 0;
	
	public Board hillClimbing(Board initial){
		nodeCounter = 0;
		Board neighbor;
		Board current = new Board(initial);
		System.out.println(current);
		int sideSteps = 0;
		
		while(true){
			neighbor = getLowestCostNeighbor(current);
			if(neighbor.getHeuristic() > current.getHeuristic()){
				return current;
			}
			else if(neighbor.getHeuristic() == current.getHeuristic()){
				if(sideSteps == 0){
					return current;
				}
				else{
					sideSteps--;
				}
			}
			
			current = new Board(neighbor);
		}
		
		
	}
	
	private Board getLowestCostNeighbor(Board current){
		Board bestBoard = null;
		Board testBoard = new Board(current);
		Queen[] queenList = testBoard.getListOfQueens();
		
		for(int i = 0; i < queenList.length; i ++){
			for(int n = 0; n<Board.BOARD_SIZE; n++){
				testBoard.removeQueen(i, queenList[i].getY());
				testBoard.placeQueen(i, n);
				nodeCounter++;
				if(testBoard.getHeuristic() <= current.getHeuristic()){
					bestBoard = new Board(testBoard);
				}
			}
			testBoard = new Board(current);
			queenList = testBoard.getListOfQueens();
		}
		return bestBoard;
		/*
		 * loop
		 * remove queen
		 * try adding queen to each available spot on the board,
		 * calculate heuristic after every move
		 * if heuristic is lower, set that board placement as the new best spot
		 * after done iterating through all movement options of each queen return best board based on herusitic
		 */
	}
	
	public int getNodeCounter(){
		return nodeCounter;
	}
}
