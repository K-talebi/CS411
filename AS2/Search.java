import java.util.*;

public class Search {

	private int nodeCounter = 0;
	private int temperature;
	
	public Board randomRestart(Board board){
		Board b = new Board(board);
		b.shuffleBoard();
		while(true){
			if(b.getHeuristic() == 0){
				return b;
			}
			else{
				b.shuffleBoard();
				b = hillClimbing(b);
				//System.out.println("RESET BOARD");
			}
		}
	}
	
	public Board hillClimbing(Board initial){
		//nodeCounter = 0;
		Board neighbor;
		Board current = new Board(initial);
		//System.out.println(current);
		
		//used to set whether to sidestep or not
		int sideSteps = 0;

		while(true){
			//System.out.println("INITIAL HEURISTIC: "+current.getHeuristic());
			neighbor = getLowestCostNeighbor(current);
			//System.out.println("NEIGHBOR HEURISTIC: "+neighbor.getHeuristic());
			//System.out.println("found a neighbor");
			if(neighbor.getHeuristic() > current.getHeuristic()){
				return current;
			}
			else if(neighbor.getHeuristic() == 0){
				return neighbor;
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
		Random random = new Random();
		ArrayList<Board> bestBoard = new ArrayList<>();
		Board testBoard = new Board(current);
		Queen[] queenList = testBoard.getListOfQueens();
		int bestHeuristic = current.getHeuristic();
		int bestBoardCounter = 0;
		int original;
		
		for(int i = 0; i < queenList.length; i ++){
			for(int n = 0; n<Board.BOARD_SIZE; n++){
				original = queenList[i].getY();
				if(n != original){
					testBoard.removeQueen(i, queenList[i].getY());
					testBoard.placeQueen(i, n);
					nodeCounter++;
					if(testBoard.getHeuristic() < bestHeuristic){
						bestBoard.clear();
						bestBoard.add(new Board(testBoard));
						bestHeuristic = testBoard.getHeuristic();
						//System.out.println("BLAH"+testBoard.getHeuristic());
						//System.out.println(testBoard);
					}
					else if(testBoard.getHeuristic() == bestHeuristic ){
						bestBoard.add(new Board(testBoard));
					}
				}
			}
			testBoard = new Board(current);
			queenList = testBoard.getListOfQueens();
		}
		
		if(bestBoardCounter == 0){
			bestBoardCounter++;
			bestBoard.add(current);
		}
		return bestBoard.get(random.nextInt(bestBoardCounter));
		/*System.out.println("Best Heuristic: "+bestHeuristic);
			for(int i = 0; i < queenList.length; i ++){
				for(int n = 0; n<Board.BOARD_SIZE; n++){
					testBoard.removeQueen(i, queenList[i].getY());
					testBoard.placeQueen(i, n);
					System.out.println("testBoard Heuristic: "+testBoard.getHeuristic());
					if(testBoard.getHeuristic() == bestHeuristic){
						bestBoard.add(new Board(testBoard));
						bestBoardCounter++;
						System.out.println(bestBoardCounter);
					}
				}
		}*/
			//System.out.println(bestBoard);
		/*
		 * loop
		 * remove queen
		 * try adding queen to each available spot on the board,
		 * calculate heuristic after every move
		 * if heuristic is lower, set that board placement as the new best spot
		 * after done iterating through all movement options of each queen return best board based on herusitic
		 */
	}
	
	public Board simulatedAnnealing(Board initial){
		Board current = new Board(initial);
		Board neighbor;
		double temp = 2000;
		int t = 0;
		double deltaE;
		double acceptedProbability;
		double probability;
		double coolingRate = .66;
		
		while(true){
			//temp = getTemp(t);
			if(temp == 0 || current.getHeuristic() == 0){
				System.out.println("WHY ARENT YOU HAPPENING PROPERLY");
				return current;
			}
			neighbor = getRandomNeighbor(current);
			deltaE = neighbor.getHeuristic() - current.getHeuristic();
			if(deltaE <= 0){
				current = neighbor;
			}
			else{
				acceptedProbability = Math.exp((-deltaE)
						/temp);
				probability = Math.random();
				
				//System.out.println("probability: "+probability+ "\tACEPTED PROB:"+ acceptedProbability+ "\ttemp: " + temp );
				if(acceptedProbability > probability){
					current = neighbor;
					t++;
				}
				//current = neighbor only with probability e^ deltaE/T 
			}
			temp = temp * .66 ;
		}
	}
	
	public double getTemp(int t){
		double coolingRate = 0.003;
		return (20 - ((double)t/20));
	}
	
	public Board getRandomNeighbor(Board b){
		Random random = new Random();
		ArrayList<Board> possibleNeighbors = new ArrayList<>();
		Board testBoard = new Board(b);
		Queen[] queenList = testBoard.getListOfQueens();
		int original;
		
		for(int i = 0; i < queenList.length; i ++){
			for(int n = 0; n<Board.BOARD_SIZE; n++){
				original = queenList[i].getY();
				if(n != original){
					testBoard.removeQueen(i, queenList[i].getY());
					testBoard.placeQueen(i, n);
					nodeCounter++;
					possibleNeighbors.add(new Board(testBoard));
				}
			}
			testBoard = new Board(b);
			queenList = testBoard.getListOfQueens();
		}
		return possibleNeighbors.get(random.nextInt(possibleNeighbors.size()));
	}
	
	public int getNodeCounter(){
		return nodeCounter;
	}

}
