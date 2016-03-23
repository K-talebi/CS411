import java.util.*;
public class Search {
	Boolean max = true;
	ArrayList<GameTree> visited;
	public Search(){
		visited = new ArrayList<GameTree>();
		
	}
	
	public int aBSearch(GameTree current, int alpha, int beta, boolean isMax){
		if(terminalTest(current)){
			return utility(current);
		}
		
		if(isMax){
			int v = Integer.MIN_VALUE;
			for(GameTree tree : current.getChildList()){
				v = Math.max(v, aBSearch(tree, alpha,beta, false));
				alpha = Math.max(alpha, v);
				if(beta <= alpha){
					break;
				}
				return v;
			}
		}
		else{
			int v = Integer.MAX_VALUE;
			for(GameTree tree : current.getChildList()){
				v = Math.min(v, aBSearch(tree,alpha,beta, true));
				beta = Math.min(beta, v);
				if(beta <= alpha){
					break;
				}
			}
			return v;
		}
		System.out.println("ERROR");
		return 0;
	}
	
	public boolean terminalTest(GameTree current){
		if(current.getChild(0) == null){
			return true;
		}
		return !(current.hasChildren());
		
	}
	
	private int utility(GameTree current){
		return current.getData();
	}
}
