package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	private final List<T> predecessors;
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
		predecessors = new ArrayList<T>();
	}

	@Override
	public List<T> findSolution() {
		// TODO
		if(solution != null){
			return solution;
		}
		T start = searchProblem.getInitialState();
		Stack<T> stack = new Stack<T>();
		List<T> path = new ArrayList<T>();
		visited.add(start);
		predecessors.add(start);
		stack.push(start);
		while(!stack.isEmpty()){
			T elem = stack.pop();
			if(searchProblem.isGoal(elem)){
				visited.add(elem);
				path.add(elem);
				while(!elem.equals(searchProblem.getInitialState())){
					T predecessor = predecessors.get(visited.indexOf(elem));
					path.add(predecessor);
					elem = predecessor;
				}
				break;
			}
			for(T neighbor: searchProblem.getSuccessors(elem)){
				if (!visited.contains(neighbor)){
					visited.add(neighbor);
					stack.push(neighbor);
					predecessors.add(neighbor);
					predecessors.set(visited.indexOf(neighbor), elem);
					
				}
			}
		}
		Collections.reverse(path);
		if(path.size() > 0){
			if (!isValidSolution(path)){
				throw new RuntimeException("searcher should never find an invalid solution!");
			}
		}
		return path;
	}
}
