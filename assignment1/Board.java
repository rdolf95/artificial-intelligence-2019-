package assignment1;
import java.util.ArrayList;

public class Board {
	
	/**
	 *  Make empty board object for start of search.
	 *  
	 *  @param lines	lines of board
	 */
	public Board(int lines) {
		this.lines = lines;
		this.pieces = new ArrayList<Integer>();
		this.piecesNum = 0;
	}
	
	/**
	 * Make new board object. 
	 * Parent node will provide previous pieces' columns.
	 * A column of new piece will be provided too.
	 * 
	 * @param lines		lines of board	
	 * @param pieces	previous pieces' columns	
	 * @param nPiece	column of new piece to be placed
	 */
	public Board(int lines, ArrayList<Integer> pieces, int nPiece) {
		this.lines = lines;
		this.pieces = new ArrayList<Integer>();
		this.pieces.addAll(pieces);
		this.pieces.add(nPiece);
		this.piecesNum = this.pieces.size();
		
	}
	
	/**
	 * Test the board's queens arrangement is an answer of n-queens problem.
	 * 
	 * @return	True when arrangement is an answer. False when not.
	 */
	public boolean goal_Test() {
		// Answer should have a queens on each line.
		if (piecesNum != lines) {	
			return false;
		}
		
		// For every queen, there will be no another queen 
		// in same column or diagonal direction.
		for(int i=0; i<lines; i++) {
			int current = pieces.get(i);
			for(int j=i+1; j<lines; j++) {
				int compared = pieces.get(j);
				if(current == compared || current + (i-j) == compared 
						|| current - (i-j) == compared) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Provide the result of queens arrangement.
	 */
	public String toString() {
		String result = "";
		for(int i=0; i<lines; i++) {
			result += pieces.get(i);
			result += " ";
		}
		result = result.substring(0, result.length()-1);
		
		return result;
	}
	
	public int getPiecesNum() {
		return piecesNum;
	}

	public ArrayList<Integer> getPieces(){
		return pieces;
	}
	
	// For each board, columns of queens will be stored in list.
	private ArrayList<Integer> pieces;
	private int lines;
	private int piecesNum;
	

}
