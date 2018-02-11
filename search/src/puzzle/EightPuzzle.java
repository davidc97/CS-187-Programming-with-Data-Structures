package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {
	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	List<Integer> board;
	public EightPuzzle(List<Integer> startingValues) throws IllegalArgumentException{
		// TODO
		for(int i =0;i<startingValues.size();i++){
			if(i > 8 || !startingValues.contains(i)){
				throw new IllegalArgumentException();
			}
		}
		board = startingValues;
	}

	@Override
	public List<Integer> getInitialState() {
		// TODO
		return board;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		// TODO
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		int zeroIndex = currentState.indexOf(0);
		boolean top = false,bottom = false,left = false,right = false;
		if(zeroIndex % 3 == 0){
			left = true;
		}
		if(zeroIndex % 3 == 2){
			right = true;
		}
		if(zeroIndex - 3 < 0){
			top = true;
		}
		if(zeroIndex + 3 > 8){
			bottom = true;
		}
		if(left){
			List<Integer> leftTrue = new ArrayList<Integer>(currentState);
			Collections.swap(leftTrue, zeroIndex, zeroIndex+1);
			list.add(leftTrue);
		}
		if(right){
			List<Integer> rightTrue = new ArrayList<Integer>(currentState);
			Collections.swap(rightTrue, zeroIndex, zeroIndex-1);
			list.add(rightTrue);
		}
		if(top){
			List<Integer> topTrue = new ArrayList<Integer>(currentState);
			Collections.swap(topTrue, zeroIndex, zeroIndex + 3);
			list.add(topTrue);
		}
		if(bottom){
			List<Integer> bottomTrue = new ArrayList<Integer>(currentState);
			Collections.swap(bottomTrue, zeroIndex, zeroIndex - 3);
			list.add(bottomTrue);
		}
		if(!top && !bottom){
			List<Integer> notTop = new ArrayList<Integer>(currentState);
			List<Integer> notBottom = new ArrayList<Integer>(currentState);
			Collections.swap(notTop, zeroIndex, zeroIndex-3);
			Collections.swap(notBottom, zeroIndex, zeroIndex+3);
			list.add(notTop);
			list.add(notBottom);
		}
		if(!left && !right){
			List<Integer> notLeft = new ArrayList<Integer>(currentState);
			List<Integer> notRight = new ArrayList<Integer>(currentState);
			Collections.swap(notLeft, zeroIndex, zeroIndex-1);
			Collections.swap(notRight, zeroIndex, zeroIndex+1);
			list.add(notLeft);
			list.add(notRight);
		}
		return list;
	}

	@Override
	public boolean isGoal(List<Integer> state) {
		// TODO
		List<Integer> goal = Arrays.asList(new Integer[] {1,2,3,4,5,6,7,8,0});
		return state.equals(goal);
	}

	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
				4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}
}
