import java.util.*; //FIXME fix repeated words and lack of variations
import java.io.*;

import org.apache.commons.io.*;

public class MarkovMath
{
	final int LENGTH_NUM = 2; // determines length of probability chains, higher means less mixed up (changing will also require addition of loop in analyze())
	FileUtils f = new FileUtils(); 
	int source_length;
	String[] fileContents;
	ArrayList<ProbVec> vectors = new ArrayList<ProbVec>();
	
	public MarkovMath(File file) throws IOException
	{
		this.source_length = (int) file.length();
		String s = "[ " + f.readFileToString(file) + " ]";
		s = s.replace("\n", "").replace("\r", "");
		fileContents = s.split(" ");
	}
	
	public String generate(int length) //REWRITE FIXME- Will now start looking from [ to first letters and create pairs
	{
		Random rand = new Random(System.currentTimeMillis()); //add random seed
		String lastWord = "[";
		String gen = "";
		ArrayList<String> stored_for_calc = new ArrayList<String>(); 
		int wordCount = 0;
		//vectors.randomize possibly
		while(wordCount < length - 1) //no random generation
		{
		for(int i = 0; i < vectors.size(); i++)	
		{	
			for(ProbVec vec : vectors)
				{
				if(vec.returnFirst().equals(lastWord))
					{
					stored_for_calc.add(vec.returnLast());	
					}
				}
	/*		for(String s : stored_for_calc){     //FIXME - For debugging only
				System.out.println(s);           //FIXME - For debugging only
			}                                    //FIXME - For debugging only
			System.out.println("_____________"); //FIXME - For debugging only
			System.out.println("GEN:" + gen);    //FIXME - For debugging only       */
			if(stored_for_calc.size() == 0)      //Find better solution
			{
				lastWord = vectors.get(rand.nextInt(vectors.size())).returnFirst().replace("]", "") + " ";
			}
			else
			{
				lastWord = stored_for_calc.get(rand.nextInt(stored_for_calc.size()));
		
			}
			gen += lastWord + " ";
			stored_for_calc.clear();
			wordCount++;
		}	
		}
		gen = gen.replace("]", "").replace("[", "");
		return gen;
	}
	
	public void analyze() 
	{
		//vectors.add(0, new ProbVec(LENGTH_NUM, "[ " + fileContents[0]));
		for(int i = 0; i < fileContents.length - 1; i++)
		{
			vectors.add(new ProbVec(LENGTH_NUM, fileContents[i] + " " + fileContents[i+1]));
		} 
		//vectors.add(new ProbVec(LENGTH_NUM, fileContents[fileContents.length - 1] + " ]")); //change later to - 1
		int prob_count, match_count;
		for(ProbVec vec : vectors)
		{
			prob_count = 0;
			match_count = 0;
			for(int i = 0; i < vectors.size(); i++)
			{
				if( vec.returnFirst().equals(vectors.get(i).returnFirst()))
				{
					prob_count++;		
				}
				if( vec.returnFirst().equals(vectors.get(i).returnFirst()) && vec.returnLast().equals(vectors.get(i).returnLast()))
				{
					match_count++;		
				}
				vec.changeFreq((double) match_count / prob_count);	
			}
			//System.out.println(vec.returnFirst() + " " + vec.returnLast() + " " + vec.returnProb()); 
		}
		
	}
	
	
	
}
