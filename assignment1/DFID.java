package assignment1;

/**
 * Class for DFID search algorithm.
 * It inherits DFS class to use expand method and variables.
 * 
 */
public class DFID extends DFS {
	
	public DFID(int lines) {
		super(lines);
	}
	
	/**
	 *  Search an answer of N-Queens problem.
	 *  Depth will be increasing gradually every time when fringe is empty.
	 *  
	 *  @return  return a board which is arranged as an answer of problem.
	 */
	public Board searh() {
		Board current;
		
		// Increase depth one by one when fringe is empty.
		for (int depth = 0; depth<lines; depth++) {
			current = new Board(lines);	// Make empty board
			expand(current);	// expand first nodes
			while(!fringe.empty()) {
				current = fringe.pop();		// get top node from fringe
				if(current.goal_Test()) {	// test the node is answer
					return current;			// return the node if it is answer
				}
				if(current.getPiecesNum() <= depth) {	// limit the tree depth
					expand(current);			// expands next nodes
				}
			}
		}
		return null;	// return null when there is no answer.
	}
}
