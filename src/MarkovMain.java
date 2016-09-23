import java.util.*;
import java.io.*;

public class MarkovMain {

	public static void main(String[] args) throws IOException
	{
	File source_file = new File("c:\\users\\b\\desktop\\sourcedoc.txt");	
	Scanner fileScanner = new Scanner(source_file);
	MarkovMath math = new MarkovMath(source_file);
	fileScanner.close();
	math.analyze();
	System.out.println(math.generate(10)); 

	
	
	
	}

}
