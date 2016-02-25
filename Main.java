import java.util.*;
import java.io.*;

public class Main {
	public static void main (String [] args){
		final int END = 100;
		final int MIN_DEPTH = 2;
		final int MAX_DEPTH = 2;

		Puzzle puzzleToSolveMan = new Puzzle();
		Puzzle puzzleToSolveMisplaced;
		Puzzle goalPuzzle = new Puzzle();

		File file = new File("myPuzzles.txt");
		Search aStar = new Search();

		for (int n = MIN_DEPTH; n <= MAX_DEPTH; n+=2){
			int nodesGenMan = 0, nodesGenMisp = 0;
			for(int i = 0; i < END; i++){
				puzzleToSolveMan.shuffle(n);
				//System.out.println("SHUFFLED");
				//System.out.println(puzzleToSolveMan);
				//create the same puzzle to be used with the misplaced heuristic
				puzzleToSolveMisplaced = puzzleToSolveMan.dupe();

				//Solve with manhattan heurisitc
				puzzleToSolveMan.setHeuristic(true);
				//System.out.println(puzzleToSolveMan.calculateManhattanHeuristic());
				//aStar.solve(puzzleToSolveMan, goalPuzzle);
				
				System.out.println(aStar.solve(puzzleToSolveMan, goalPuzzle));
				nodesGenMan = aStar.getNodeTotal();
				System.out.println("NODE TOTAL" + aStar.getNodeTotal());
				//nodesGenMan += (aStar.getNodeTotal());

				//Solve with misplaced heuristic
				puzzleToSolveMisplaced.setHeuristic(false);
				aStar.solve(puzzleToSolveMisplaced, goalPuzzle);
				//System.out.println(aStar.getNodeTotal());
				nodesGenMisp = (aStar.getNodeTotal());
			}
			System.out.println("Puzzle Depth: " + n + " \tManhattan Node Average: " + (nodesGenMan / END) + "\t Misplaced Node Average: " + nodesGenMisp /END);
		}
	}
}
