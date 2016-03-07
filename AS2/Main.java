
public class Main {
	public static void main(String[] args) {
		Board board = new Board();
		board.shuffleBoard();
		Search search = new Search();
		System.out.println(board);
		
		System.out.println(search.hillClimbing(board));
		System.out.println(search.getNodeCounter());

	}

}
