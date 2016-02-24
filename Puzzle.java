import java.util.*;

public class Search {
	private int numberOfNodes;
	
	public Search() {
		numberOfNodes = 0;
	}

	public void resetNodeCounter(){
		numberOfNodes = 0;
	}
	
	public int getNodeTotal(){
		return numberOfNodes;
	}
	
	
	
	public Puzzle solve(Puzzle start, Puzzle goal){
		ArrayList<Puzzle> explored = new ArrayList<>();
		ArrayList<Puzzle> frontier = new ArrayList<>();
		resetNodeCounter();
		frontier.add(start);
		numberOfNodes = 1;
		
		while(frontier.size()>0){
			Puzzle progress  = getLowestCost(frontier);
			frontier.remove(progress);
			
			//check if solved
			if(progress.equals(goal)){
				return progress;
			}
			
			if (frontier.indexOf(progress) > -1 || explored.indexOf(progress) > -1) {
                continue;
            }
            
			//If not, get children of current node, add to frontier
			//add current to explored set, run again on frontier.
			ArrayList<Puzzle> childPuzzles = getChildren(progress);
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
		return null;
	}
	
	//find possible directions to go
	public ArrayList<Puzzle> getChildren(Puzzle parent){
		ArrayList<Puzzle> childList = new ArrayList<Puzzle>();
		Puzzle child;
		if(parent.canMoveUp()){
			child = new Puzzle(parent);
			child.moveUp();
			childList.add(child);
		}
		
		if(parent.canMoveDown()){
			child = new Puzzle(parent);
			child.moveDown();
			childList.add(child);
		}

		if(parent.canMoveLeft()){
			child = new Puzzle(parent);
			child.moveLeft();
			childList.add(child);
		}

		if(parent.canMoveRight()){
			child = new Puzzle(parent);
			child.moveRight();
			childList.add(child);
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
		System.out.println("SEARCH CHOSE THIS!");
		System.out.println(bestChoice);
		return bestChoice;
	}
}
