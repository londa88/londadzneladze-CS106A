/*
  * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */
import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {
private String name;
private int[] decadeArray;

 	public NameSurferEntry(String line) { //dividing given line into the name and the numbers and storing numbers into the array
		    decadeArray=new int [NDECADES];
	        name=line.substring(0,line.indexOf(" ")); 
	        line=line.substring(line.indexOf(" ")+1);
			StringTokenizer tokenizer=new StringTokenizer(line, " ");
			for(int i=0; i<NDECADES; i++) {
				decadeArray[i]=Integer.parseInt(tokenizer.nextToken());
			}
	}

	public String getName() { //this method returns the name
		return name;
	}

	public int getRank(int decade) { //this method returns decade
				return decadeArray[decade];
	}


	public String toString() { 
		return name+Arrays.toString(decadeArray);
	}
	
	}


