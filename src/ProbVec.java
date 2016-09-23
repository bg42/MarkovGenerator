
public class ProbVec 
{
	double freq = 0; //frequency instead of probability?
	String[] pair;
	
	public ProbVec(int vecSize, String space_separated_keys)
	{
		pair  = new String[vecSize];
		String[] keys = space_separated_keys.split(" ");
		for(int i = 0; i < vecSize; i++)
		{
			pair[i] = keys[i];
		}
	}
	
	void changeFreq(double new_prob)
	{
		this.freq = new_prob;
	}
	
	double returnFreq()
	{
		return freq;
	}
	
	String returnFirst()
	{
		return pair[0];
	}
	
	String returnLast()
	{
		return pair[1];
	}
	
	String read() 
	{
		return "{" + pair[0] + "," + pair[1] + "," + freq + "}";
	}
	
}
