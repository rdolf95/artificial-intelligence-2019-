package assignment1;
import java.util.Stack;

/**
 * Class for DFS search algorithm.
 * 
 */
public class DFS {
	
	/**
	 * Constructor for the class.
	 * Make fringe with LIFO queue(stack).
	 * @param lines The number of chess board's line to place queens.
	 */
	public DFS(int lines){
		this.lines = lines;
		this.fringe = new Stack<Board>();
	}
	
	/**
	 *  Expand child nodes from current node.
	 *  @param		current current searching node.
	 *  @return		return true if expanding succeed, false otherwise.
	 */
	public void expand(Board current) {
		
		if (current.getPiecesNum() >= lines) {		// when last piece was placed already
			return;
		}
		
		for(int i = 0; i < lines; i++) {		// push boards which a piece is placed to next row
			fringe.push(new Board(lines, current.getPieces(), i));
		}
		return;
	}
	
	
	/**
	 *  Search an answer of N-Queens problem
	 *  
	 *  @return  return a board which is arranged as an answer of problem.
	 */
	public Board searh() {
		Board current = new Board(lines);
		expand(current);	// expand first nodes
		
		while(!fringe.empty()) {
			
			current = fringe.pop();		// get top node from fringe
			if(current.goal_Test()) {	// test the node is answer
				return current;			// return the node if it is answer
			}
			expand(current);			// expands next nodes
		}
		
		return null;	// return null when there is no answer.
	}
	
	protected int lines;
	protected Stack<Board> fringe;
}
