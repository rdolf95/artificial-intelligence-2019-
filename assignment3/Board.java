package assignment3;

public class Board implements Comparable<Board>{
	
	/**
	 *  Make empty board object for start of search.
	 *  
	 *  @param lines	lines of board
	 */
	public Board(int lines) {
		this.lines = lines;
		this.pieces = new int[lines];
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
	public Board(int lines, int[] pieces, int generation) {
		this.lines = lines;
		this.pieces = new int[lines];
		this.pieces = pieces.clone();
		calScore();
		
		this.generation = generation;
		
	}
	
	/**
	 * Test the board's queens arrangement is an answer of n-queens problem.
	 * 
	 * @return	True when arrangement is an answer. False when not.
	 */
	public boolean goal_Test() {
		
		// For every queen, there will be no another queen 
		// in same column or diagonal direction.
		for(int i=0; i<lines; i++) {
			int current = pieces[i];
			for(int j=i+1; j<lines; j++) {
				int compared = pieces[j];
				if(current == compared || current + (i-j) == compared 
						|| current - (i-j) == compared) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Get heuristic score of input arrangement of queens.
	 * The score goes up by one for each conflict. 
	 * Including horizontal, diagonal direction.
	 * 
	 * @param pieces	arrangement of queens
	 * 
	 * @return	heuristic score to the goal
	 */
	public int calScore() {
		int score = 0;
		
		// Each conflict will increase score by one.
		for(int i=0; i<lines; i++) {
			int current = pieces[i];
			for(int j=i+1; j<lines; j++) {
				int compared = pieces[j];
				if(current == compared) {
					score++;
				}
				if(current + (i-j) == compared) {
					score ++;
				}
				if(current - (i-j) == compared) {
					score ++;
				}
			}
		}
		this.score = score;
		return score;
	}
	
	
	/**
	 * Provide the result of queens arrangement.
	 */
	public String toString() {
		String result = "";
		for(int i=0; i<lines; i++) {
			result += pieces[i];
			result += " ";
		}
		result = result.substring(0, result.length()-1);
		
		return result;
	}
	
	// Compare board with score.
	public int compareTo(Board other) {
		return score - other.score;
	}
	
	
	// Getters and setters.
	
	public void setPieces(int[] newPieces) {
		this.pieces = newPieces;
		calScore();
	}

	public int[] getPieces(){
		return pieces;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getGeneration() {
		return generation;
	}
	
	// For each board, columns of queens will be stored in list.
	private int pieces[];
	private int lines;
	private int score;
	private int generation;

}
