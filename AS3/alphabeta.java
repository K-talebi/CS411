public class alphabeta {

	public static void main (String [] args){
		GameTree gameTree = new GameTree();
		int [] treeVal = new int [9];
		
		//put args into an array to be made into a gametree
		for (int i = 0; i < 9; i++){
			treeVal [i] = Integer.parseInt(args[i]);
			//System.out.println(treeVal[i]);
		}
		gameTree.fillTree(treeVal);
		
		Search search = new Search();
		
		System.out.println(search.aBSearch(gameTree, Integer.MIN_VALUE, Integer.MAX_VALUE, true));
	}

}
