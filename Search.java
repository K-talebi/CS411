import java.util.ArrayList;
import java.util.PriorityQueue;

public class Search {
	private int numberOfNodes = 0;
	
	public Search() {
	}

	public void resetNodeCounter(){
		numberOfNodes = 0;
	}
	
	public int getNodeTotal(){
		return numberOfNodes;
	}
	
	
	
	public Puzzle solve(Puzzle start, Puzzle goal){
		ArrayList<Puzzle> explored = new ArrayList<>();
		PriorityQueue<Puzzle> frontier = new PriorityQueue<>();
		resetNodeCounter();
		frontier.add(start);
		numberOfNodes = 1;
		
		//System.out.println(start);
		while(frontier.size()>0){
			Puzzle progress  = frontier.poll();
//			System.out.println("PROGRESS ON PUZZLE");
//			System.out.println(progress);
			
			//check if solved
			if(progress.equals(goal)){
				return progress;
			}
            
			//If not, get children of current node, add to frontier
			//add current to explored set, run again on frontier.
			ArrayList<Puzzle> childPuzzles = getChildren(progress);
//			System.out.println(childPuzzles);
			for(int i = 0; i < childPuzzles.size(); i++){
				if((frontier.contains(childPuzzles.get(i))) || ((explored.contains(childPuzzles.get(i))))){
					//System.out.println("Already had");
					//System.out.println("THIS\n" +childPuzzles.get(i));
				}else{
					frontier.add(childPuzzles.get(i));
					numberOfNodes++;
					//System.out.println("# of Nodes"+numberOfNodes);
				}
			}
			explored.add(progress);	
		}
		System.out.println();
		return null;
	}
	
	//find possible directions to go
	public ArrayList<Puzzle> getChildren(Puzzle parent){
		ArrayList<Puzzle> childList = new ArrayList<Puzzle>();
		//Puzzle child;
		if(parent.canMoveUp()){
			Puzzle child = new Puzzle(parent);
			child.moveUp();
			childList.add(child);
//			System.out.println("MOVED UP");
//			System.out.println(child);
		}
		
		if(parent.canMoveDown()){
			Puzzle child = new Puzzle(parent);
			child.moveDown();
			childList.add(child);
//			System.out.println("Moved down");
//			System.out.println(child);
		}

		if(parent.canMoveLeft()){
			Puzzle child = new Puzzle(parent);
			child.moveLeft();
			childList.add(child);
//			System.out.println("MOVED LEFT");
			//System.out.println(child);
		}

		if(parent.canMoveRight()){
			
			Puzzle child = new Puzzle(parent);
			child.moveRight();
			childList.add(child);
//			System.out.println("Move Right");
			//System.out.println(child);
		}
		
		return childList;
	}
	
	//find best choice of movement
	public Puzzle getLowestCost(ArrayList<Puzzle> puzzleList){
		Puzzle bestChoice = null;
		Puzzle checkingPuzzle;
		int bestCost = Integer.MAX_VALUE;
		for(int i = 0 ; i < puzzleList.size(); i++){
			checkingPuzzle = puzzleList.get(i);
			int totalCost = checkingPuzzle.getCurrentCost() + checkingPuzzle.getCostToFinish();
			//System.out.println(checkingPuzzle +"Current COST:"+ checkingPuzzle.getCurrentCost());
			if(bestCost > totalCost){
				bestCost = totalCost;
				bestChoice = checkingPuzzle;
			}
		}
		//System.out.println("Best Choicee");
		//System.out.println(bestChoice);
		return bestChoice;
	}
}
