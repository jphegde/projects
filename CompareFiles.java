import java.io.*;
import java.io.File.*;
import java.util.*;
import java.util.HashMap.*;
import java.util.HashSet.*;
import java.util.Map.*;

public class CompareFiles
{
	 public void populate(HashMap<String, HashSet<String>> h, File f)
	 {
		  String line, words[];
	      int colNums;//get number of columns
		 try
		 {
				BufferedReader br = new BufferedReader(new FileReader(f));
	           
	            line = br.readLine();
	            words = line.split(",");
				colNums = words.length;
				
				
	            while((line = br.readLine()) != null) 
	            {
	                 // splitting the line to get elements. 
	            	
	            	words = line.split(",");
	            	colNums = words.length;
	            	
	            	HashSet<String> temp = new HashSet<String>();
	            	for(int i = 1; i < colNums; i++)
	            	{
	            	    char[] chars = words[i].toCharArray();

						for (int j = 0; j < chars.length; j++) 
						{
							if(Character.isLetter(chars[j])) 
							{
								chars[j] = Character.toUpperCase(chars[j]);
							}
						}
						
	            	    temp.add(new String(chars));
	            	  
	            	}
	            	
	            	h.put(words[0], temp);
	            	Arrays.fill(words, null);	
	            }
	           br.close();
	        }
	         
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            System.out.println("Error: Reading files.");
	        }
	        
	 }
	public int compare(HashMap<String, HashSet<String>> h1, HashMap<String, HashSet<String>> h2)
	{
	   
	    int flag = 1;
	    for(String keys2: h2.keySet())
	    {
	         
	        if(h1.containsKey(keys2))
	        {
	            
	            if(h2.get(keys2).containsAll(h1.get(keys2)))
	            {
	               
	               continue;
	             }
	             else
	             {
					flag = 0;
					System.out.println(keys2+" Expected: "+h1.get(keys2)+" Found: "+h2.get(keys2));
				 }	
	        }
	        else
	        {
				System.out.println(keys2+" is an invalid id.");
	        }
	        
	    }
	    
	    return flag;  	
	}
	public static void main(String args[])
	{
		
		int res = 0;
		String file1 = null, file2 = null;
		File f1 = null, f2 = null;
		CompareFiles cf = new CompareFiles();
		HashMap<String, HashSet<String>> h1 = new HashMap<String, HashSet<String>>();
		HashMap<String, HashSet<String>> h2 = new HashMap<String, HashSet<String>>();
		try
		{
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    System.out.print("Enter file1: ");
		    file1 = br.readLine();
		    System.out.print("Enter file1: ");
		    file2 = br.readLine();
		    br.close();
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		}
		try
		{
		    f1 = new File(file1);
		    f2 = new File(file2);
		}
		catch(Exception e)
		{   
		    System.out.println("Error: Cannot load files.");
		}
		cf.populate(h1, f1);
		cf.populate(h2, f2);
	
		
		res = cf.compare(h1, h2);
		if(res == 1)
		{
		    System.out.println("Same");
		    
		}
		else
		{
		    System.out.println("Not Same");
		 }
	}
}
