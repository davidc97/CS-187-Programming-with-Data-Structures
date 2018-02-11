package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {
	private final List<T> predecessors;
	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
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
		Queue<T> queue = new LinkedList<T>();
		List<T> path = new ArrayList<T>();
		visited.add(start);
		predecessors.add(start);
		queue.add(start);
		while(!queue.isEmpty()){
			T elem = queue.remove();
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
					queue.add(neighbor);
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
