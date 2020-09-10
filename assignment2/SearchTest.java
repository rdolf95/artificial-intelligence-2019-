package assignment2;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Class for test and make result of solving n-queens problem.
 *
 */
public class SearchTest {
	
	public static void main(String[] args) throws IOException{
		
		// Get first argument for getting 'N'
		int lines = Integer.parseInt(args[0]);
		
		// Get second argument for getting file absolute path.
		// Make and open file with name "resultN.txt"
		File result = new File(args[1], "result" + lines + ".txt");
		PrintWriter output = new PrintWriter(result.getAbsolutePath());
		
		HillClimbing localSearch = new HillClimbing(lines,10000);
		search(localSearch,output,lines);
		
		output.close();
		
	}
	
	/**
	 * Search n-queens problem with hill climbing algorithm.
	 * 
	 * @param localSearch	Object performing hill climbing.
	 * @param output		Output file object.
	 * @param lines			N of N-queens problem.
	 */
	public static void search(HillClimbing localSearch, 
			PrintWriter output, int lines) {
		long etime;
		long stime;
		int[] answer;
		
		stime = System.currentTimeMillis();
		answer = localSearch.searchSolution();
		etime = System.currentTimeMillis();
		
		if(answer != null) {
			String result ="";
			for(int i=0; i<lines; i++) {
				result += answer[i];
				result += " ";
			}
			output.println("Hill Climbing");
			output.println(result);
			output.println("Time : "+(etime - stime)/1000.0);

		}
		else {
			output.println("No solution");
		}
	}
	
}
