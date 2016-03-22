import java.util.*;
public class Search {
	Boolean max = true;
	ArrayList<GameTree> visited;
	public Search(){
		visited = new ArrayList<GameTree>();
		
	}
	
	public int aBSearch(GameTree current){
			return maxValue(current, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public int maxValue(GameTree current, int alpha, int beta){
		//check if leaf node, if it is, check if it is in visited list, if not add it
		if(terminalTest(current)){
			if(!(visited.contains(current))){
				visited.add(current);
			}
			return utility(current);
		}
		//set newVal as smallest possible number so it will be changed at start 100% of time
		int newVal = Integer.MIN_VALUE;
		//loop through each child of current tree and call minValue on it
		for(GameTree tree : current.getChildList()){
			
			if(newVal >= minValue(tree,alpha,beta)){
				newVal = minValue(tree,alpha,beta);
			}
			//if new val is greater than alpha set new alpha
			if(newVal >= alpha){
				current.setData(newVal);
				return newVal;
			}
			//if new val is smaller than current beta set new beta
			if(newVal <= beta){
				beta = newVal;
			}
		}
			current.setData(newVal);
			return newVal;	
	}
	
	public int minValue(GameTree current, int alpha, int beta){
		if(terminalTest(current)){
			if(!(visited.contains(current))){
				visited.add(current);
			}
			return utility(current);
		}
		int newVal = Integer.MAX_VALUE;
		for(GameTree tree : current.getChildList()){
			if(newVal >= maxValue(tree,alpha,beta)){
				newVal = maxValue(tree,alpha,beta);
			}
			//if newVal is smaller than alpha set newAlpha
			if(newVal <= alpha){
				current.setData(newVal);
				return newVal;
			}
			//if newVal is larger than current beta set newBeta
			if(newVal >= beta){
				beta = newVal;
			}
		}
			current.setData(newVal);
			return newVal;	
	}
	
	public int max(int v, int minVal){
		return minVal;
		
	}
	
	public int min(int v, int maxVal){
		return maxVal;
		
	}
	
	public boolean terminalTest(GameTree current){
		return !(current.hasChildren());
		
	}
	
	private int utility(GameTree current){
		return current.getData();
	}
}
