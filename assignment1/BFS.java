package assignment1;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Class for BFS search algorithm.
 * 
 */
public class BFS {
	
	/**
	 * Constructor for the class.
	 * Make fringe with FIFO queue.
	 * @param lines The number of chess board's line to place queens.
	 */
	public BFS(int lines) {
		this.lines = lines;
		this.fringe = new LinkedList<Board>();
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
			fringe.offer(new Board(lines, current.getPieces(), i));
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
		
		while(!fringe.isEmpty()) {
			
			current = fringe.poll();		// get top node from fringe
			if(current.goal_Test()) {	// test the node is answer
				return current;			// return the node if it is answer
			}
			expand(current);			// expands next nodes
		}
		
		return null;	// return null when there is no answer.
	}
	
	
	private int lines;
	private Queue<Board> fringe;

}
