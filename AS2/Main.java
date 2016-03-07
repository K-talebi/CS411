public class Main {
	public static void main(String[] args) {
		Board board = new Board();
		board.shuffleBoard();
		Search search = new Search();
		
		
		final int NUMBER_OF_PUZZLES = 1000;
		
		int counter = 0;
		int solved = 0;
		
		double hillClimbingNodes = 0;
		
		
		//Hill Climbing Search
		for(int i = 0; i < NUMBER_OF_PUZZLES; i++ ){
			board.shuffleBoard();
			board = search.hillClimbing(board);
			if(board.getHeuristic() == 0){
				solved ++;
				counter++;
			}else{
				counter++;
			}
			hillClimbingNodes = (double) search.getNodeCounter();
		}
		double hillSolvePercent = (solved/counter) *100;
		hillClimbingNodes = hillClimbingNodes/NUMBER_OF_PUZZLES;
		System.out.println("Percent solved: " + hillSolvePercent + "%");
		System.out.println("Node Average: "+ hillClimbingNodes);
	
		search = new Search();
		board = new Board();
		counter = 0;
		solved = 0;
		
		//Random-Restart Hill Climbing Search
		/*for(int i = 0; i < NUMBER_OF_PUZZLES; i++){
			board.resetBoard();
			board = search.randomRestart();
			if(board.getHeuristic() == 0){
				solved ++;
				counter++;
			}else{
				counter++;
			}
		}
		double randomSolvePercent = (solved/counter) *100;
		double randomClimbingNodes = search.getNodeCounter()/100;
		System.out.println("Percent solved: " + randomSolvePercent + "%");
		System.out.println("Node Average: "+ randomClimbingNodes);
	*/}
}
