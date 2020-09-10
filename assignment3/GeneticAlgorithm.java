package assignment3;
import java.util.Random; 
import java.util.Arrays;

public class GeneticAlgorithm {
	
	public GeneticAlgorithm(int lines, int population) {
		this.lines = lines;
		this.population = population;
		this.generation = 0;
		this.random = new Random();
		this.boards = new Board[population];
		this.restart = 0;
		
		// Parent selection rate is 10%.
		this.selectionNum = population/10;
		// Mutation rate is 5%.
		this.mutationNum = population/20;
		
		this.candidatesNum = 100;
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
	 * Make first generation of chess boards with random placement of queens.
	 */
	private void makeRandomGeneration() {
		for (int i = 0; i< population; i++) {
			boards[i] = new Board(lines, makeRandom(),0);
		}
	}
	
	/**
	 * Cross over selected individuals to make next generation's individuals.
	 * Every gene will be selected randomly from father's or mother's.
	 * 
	 * @param father	One of selected individuals in previous generation.
	 * @param mother	Another selected individuals in previous generation.
	 * @return			Newly created individual by cross over operation.
	 */
	private Board crossOver(int[] father, int[] mother) {
		int selector;
		int newPieces[] = new int[lines];
		
		// Every gene will be selected randomly.
		for(int i=0; i< lines; i++) {
			selector = random.nextInt(2);
			if(selector == 0) {
				newPieces[i] = father[i];
			}
			else {
				newPieces[i] = mother[i];
			}
		}
		return new Board(lines, newPieces, generation);
	}
	
	private Board tournament() {
		Board candidates[] = new Board[candidatesNum];
		for(int i = 0; i < candidatesNum; i++) {
			candidates[i] = boards[random.nextInt(population)];
		}
		Arrays.sort(candidates);
		
		return candidates[0];
		
	}
	
	
	/**
	 * Mutate current generation's individuals.
	 */
	private void mutation() {
		
		int mutating;
		int pieces[] = new int[lines];
		
		// Choose individuals randomly and change one gene of chosen individual.
		for(int i = 0; i<mutationNum; i++) {
			mutating = random.nextInt(population);
			pieces = boards[mutating].getPieces();
			pieces[random.nextInt(lines)] = random.nextInt(lines);
			boards[mutating].setPieces(pieces);
		}
	}
	
	/**
	 * Make next generation's individuals with selection and cross over.
	 * 
	 * @return	Next generation's individuals.
	 */
	private Board[] nextGeneration() {
		Board father, mother; 
		
		Board newGeneration[] = new Board[population];
		
		// Select parents who have high fitness.
		// Array boards[] is sorted by fitness already.
		for(int i = 0; i < selectionNum; i++) {
			//newGeneration[i]= boards[i];
			newGeneration[i] = tournament();
		}
		
		// Choose father and mother randomly to cross over their genes.
		// Father and mother cannot be the same individual.
		for(int i = selectionNum; i < population; i++) {
			father = boards[random.nextInt(selectionNum)];
			do {
				mother = boards[random.nextInt(selectionNum)];
			}
			while(father == mother);
			newGeneration[i] = crossOver(father.getPieces(), mother.getPieces());
		}
		
		return newGeneration;
	}
	
	/**
	 * Search N-queens problem with genetic algorithm.
	 * 
	 * If too many generations are created, the individuals must be too similar.
	 * In that case, algorithm is trapped in local maximum.
	 * To solve it, restart from the start when generation is too high.
	 * 
	 * @return	An answer board of the problem if found.
	 * 			null if not.
	 */
	public Board searchSolution() {
		
		// Limiting the restart number to prevent infinite loop.
		while(restart<100) {
			// Make first generation.
			makeRandomGeneration();
			
			// Restart when generation is over 100.
			while(generation < 100) {	
				// Check if there is answer of the problem in current generation.
				for(int i = 0; i<population; i++) {
					if (boards[i].getScore() == 0) {
						return boards[i];
					}
				}
				
				// Create next generation and mutate the generation.
				boards = nextGeneration();
				mutation();
				generation++;
				//System.out.println(generation);
				
			}
			generation = 0;
			restart++;
		}
		// When if the answer couldn't be found.
		return null;
	}
	
	public int getRestart() {
		return restart;
	}
	
	public int getSelectionNum() {
		return selectionNum;
	}
	
	public int getMutationNum() {
		return mutationNum;
	}
	
	public int getCandidatesNum() {
		return candidatesNum;
	}
	
	
	
	private int lines;
	private Board[] boards;
	private int population;
	private int generation;
	private Random random;
	private int restart;
	
	private int selectionNum;
	private int mutationNum;
	private int candidatesNum;
	
}
