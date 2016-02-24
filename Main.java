import java.util.*;
import java.io.*;
public class Main {

	public static void main (String [] args){
		//Constants used for calculating current depth, and how many puzzles to solve
		final int END = 1;
		final int MIN_DEPTH = 2;
		final int MAX_DEPTH = 2;
		
		Puzzle solvedPuzzle = new Puzzle();
		Puzzle puzzle = new Puzzle();
		
		File file = new File("Puzzles.txt");
		
		//Create Search object to search puzzles
		Search aStar = new Search();
		
		//Generate Puzzles to solve
		
		
		//Solve Puzzles according to depth and print results
		for (int n = MIN_DEPTH; n <= MAX_DEPTH; n+=2){
			int nodesGenMan = 0, nodesGenMisp = 0;
			for (int i = 0; i < END; i++){
				
				//shuffle puzzle using n steps
				puzzle.shuffle(n);
				System.out.println("Shuffled Puzzle\n" + puzzle);
				//create save to use puzzle with both heurisitcs
				puzzle.savePuzzle();
				//System.out.println("saved puzzle"+puzzle);
				
				//solve with manhattan heuristic
				//puzzle.setHeuristic(true);
				System.out.println(aStar.solve(puzzle, solvedPuzzle));
				nodesGenMan += aStar.getNodeTotal();

				//reset puzzle
				puzzle.restorePuzzle();
				//System.out.println("restored Puzzle"+puzzle);
				
				
				//Solve with misplaced
				
				puzzle.setHeuristic(false);
				aStar.solve(puzzle, solvedPuzzle);
				//System.out.println(aStar.solve(puzzle, solvedPuzzle));
				nodesGenMisp += aStar.getNodeTotal();
				//System.out.println("DONE2");
				
			}
				//Display data after completetion of 100 nodes at n depth
				System.out.println("Puzzle Depth: " + n + " \tManhattan Node Average: " + nodesGenMan / END + "\t Misplaced Node Average: " + nodesGenMisp / END);
		}
	}
}
