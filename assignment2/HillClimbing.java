package assignment2;

import java.util.Random; 

/**
 * Solving n-queens with HillClimbing algorithm.
 * The class's constructor accepts 'N' and maximum number of restart count.
 */
public class HillClimbing {
	private int lines;
	private int maxRestart;
	private int restart;
	private int[] maxSuccessor;
	Random random;
	
	public HillClimbing(int lines, int maxRestart) {
		this.lines = lines;
		this.maxRestart = maxRestart;
		this.restart = 0;
		this.random = new Random();
		
	}
	
	/**
	 * Make random arrangement for initial state, and restart.
	 * 
	 * @return	Randomly generated arrangement to start with.
	 */
	private int[] makeRandom() {
		int[] randomBoard = new int[lines];
		
		for(int i = 0; i<lines; i++) {
			randomBoard[i] = random.nextInt(lines);
		}
		
		return randomBoard;
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
	public int get_score(int[] pieces) {
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
		
		return score;
	}
	
	/**
	 * Check the input arrangement of queens is solution of problem.
	 * In this problem, get_score can be used instead of this function.
	 * Score 0 means it is goal.
	 * 
	 * @param pieces	Arrangement of queens
	 * 
	 * @return	True if arrangement of queens is solution. False if not.
	 */
	public boolean goal_Test(int[] pieces) {
		
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
	 * choose next node from successors. 
	 * Implemented with first-choice hill climbing.
	 * 
	 * @return	True if there is appropriate successor
	 * 			False if current state is local maximum
	 */
	private boolean nextSuccessor() {
		int[] compared = new int[lines];
		int currentScore = get_score(maxSuccessor);
		int[] currentPieces = maxSuccessor;
		
		for(int i=0; i<lines; i++) {
			for(int j=0; j<lines; j++) {
				// One of queens can move anywhere in it's own column.
				System.arraycopy(currentPieces, 0, compared, 0, lines);
				compared[i] = j;
				if(get_score(compared) < currentScore) {
					maxSuccessor = compared;
					return true;
				}
			}
		}
		// If every next successor has lower score than current state,
		// we should restart with random state.
		return false;
	}
	
	
	/**
	 * Search n-queens problem's solution.
	 * 
	 * @return 	Return answer queens' arrangement of the problem.
	 * 			If answer couldn't be found, return null.
	 */
	public int[] searchSolution() {
		// Make random arrangement of queens to start with.
		maxSuccessor = makeRandom();
		while(restart <= maxRestart) {
			// Check selected successor is solution.
			if(goal_Test(maxSuccessor)) {
				return maxSuccessor;
			}
			// If nextSuccessor() returns false, we should restart
			if(!nextSuccessor()) {
				restart++;
				maxSuccessor = makeRandom();
			}
		}
		// Return null if restart count is over maxrestart.
		return null;
	}
	
	public int getRestart() {
		return restart;
	}

}





