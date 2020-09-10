package assignment1;
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
		
		DFS dfs = new DFS(lines);
		BFS bfs = new BFS(lines);
		DFID dfid = new DFID(lines);
		long etime;
		long stime;
		
		// Search N-queens answer with DFS algorithm
		stime = System.currentTimeMillis();
		Board answer = dfs.searh();
		etime = System.currentTimeMillis();
		
		output.println(">DFS");
		if(answer != null) {
			String aString = answer.toString();
			output.println("Location : " + aString);
		}
		else {
			output.println("No solution");
		}
		output.println("Time : "+(etime - stime)/1000.0);
		output.println();
		
		// Search N-queens answer with BFS algorithm
		stime = System.currentTimeMillis();
		answer = bfs.searh();
		etime = System.currentTimeMillis();
		
		output.println(">BFS");
		if(answer != null) {
			String aString = answer.toString();
			output.println("Location : " + aString);
		}
		else {
			output.println("No solution");
		}
		output.println("Time : "+(etime - stime)/1000.0);
		output.println();
		
		// Search N-queens answer with DFID algorithm
		stime = System.currentTimeMillis();
		answer = dfid.searh();
		etime = System.currentTimeMillis();
		
		output.println(">DFID");
		if(answer != null) {
			String aString = answer.toString();
			output.println("Location : " + aString);
		}
		else {
			output.println("No solution");
		}
		output.println("Time : "+(etime - stime)/1000.0);
		output.println();
		
		output.close();
	}
}
