
public class GameTree {
	public final static int MAX_CHILDREN = 3;
	private int data;
	private GameTree parent;
	private GameTree[] childList = new GameTree[MAX_CHILDREN];
	
	public GameTree(){
		data = 0;
		parent = null;
	}
	
	//alt constructor when adding a child
	public GameTree(GameTree parent, int data){
		this.parent = parent;
		this.data = data;
	}
	
	//set Parent
	public void setParent(GameTree parent){
		this.parent = parent;
	}
	
	//get Parent
	public GameTree getParent(){
		return this.parent;
	}
	//set Data
	public void setData(int data){
		this.data = data;
	}
	//get Data
	public int getData(){
		return this.data;
	}
	
	//Add Children
	public GameTree addChildren(int [] newChildren){
		for(int i = 0; i < MAX_CHILDREN ; i++ ){
			childList[i] = new GameTree(this, newChildren[i]);
		}
		return this;
	}
	
	//get Child
	public GameTree getChild(int i){
		return childList[i];
	}
	
	//get child list
	public GameTree[] getChildList(){
		return childList;
	}
	
	//hasChildren
	public boolean hasChildren(){
		return this.childList.length > 0;
	}
	
	public void fillTree(int [] values){
		//used to set up the tree with blank nodes in the first lvl
		int [] placeHolder = new int [] {0,0,0};
		this.addChildren(placeHolder);
		int [] childrenToAdd;
		
		//create smaller arrays and then add the new array to the subtree
		for(int i = 0; i < MAX_CHILDREN; i++){
			 childrenToAdd = new int [MAX_CHILDREN];
			 int j = 0;
			for(int n = MAX_CHILDREN*i ; n < (MAX_CHILDREN*(i+1)); n++){
				childrenToAdd[j] = values[n];
				j++;
			}
			this.getChild(i).addChildren(childrenToAdd);
		}
	}
	
	//display children of current tree node
	public String toString(){
		String s = "";
		
		if(hasChildren()){
			s += "Child 1" + getChild(0)+"\tChild 2" +getChild(1) +"\tChild3" + getChild(2);
		}
		
		return s;
	}
	
}
