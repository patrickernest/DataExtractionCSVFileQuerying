package EXTRACTION_app_Pat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * 
 * @author      Patrick Ernest <pernes2@uic.edu, taptrick47@gmail.com>
 * @version     1.0
 * @date        2016-04-08
 * 
 * Source.java
 */

public class Source {
	
	/**
	 * 
	 * @see
	 * The HashMaps that are going to be used in the Application are declared
	 */
	
	public Map<String, List<String>> ProfToListOfClass = new HashMap<String, List<String>>();
	public Map<Integer, List<String>> SidToListOfClass = new HashMap<Integer, List<String>>();
	public Map<String, List<Integer>> ClassToListOfSid = new HashMap<String, List<Integer>>();
	
	/**
	 * 
	 * @see
	 * The main method contains a menu which has the
	 * options of the questions asked. This function
	 * essentially contains a switch case which is
	 * used to navigate to the methods which are used to
	 * print the correct output. 
	 * @throws
	 * Error handling has been implemented when a String 
	 * value is entered instead of a Integer value for 
	 * continuing the while loop
	 */
	
	public static void main(String[] args) throws Exception {
		System.out.println("Data Processing...");
		Source so=new Source();
		String filename="d2.csv";
		so.processing(filename);
		int number=0;
		Scanner sc = new Scanner(System.in);
		while (number==0)
		{
			System.out.println("Select one of the following:-");
			System.out.println("1) The list of class sections being taught (I.e., unique Class/Professor pairs)");
			System.out.println("2) The list of classes being taken by each student");
			System.out.println("3) How many students are registered for each Class?  List them.");
			System.out.println("4) How many students take more than one Class?  List them.");
			System.out.println("5) How many professors teach more than one Class?");
			int i = sc.nextInt();
			switch (i) {
            case 1:  so.q1();
                     break;
            case 2:  so.q2();
                     break;
            case 3:  so.q3();
                     break;
            case 4:  so.q4();
                     break;
            case 5:  so.q5();
                     break;
            default: System.out.println("Invalid input");
            		 break;
			}
			int j=0;
			System.out.println("Enter 0 if you would like to continue, else enter anyother number to exit");
			try{
				j = sc.nextInt();
			}
			catch (NumberFormatException e)
			{
				System.out.println("Invalid input");
			}
			if(j!=0)
			{
				number=j;
			}
			
		}
		sc.close();
	}
	
	/**
	 * 
	 * @see
	 * The processing the method fetches all the data from the
	 * .csv file and stores it in the HashMaps so that it is easier
	 * to process the extracted data. This extensively improves 
	 * the time complexity.
	 * @param 
	 * The string format of the filename is passed as a parameter
	 * ie. "filename.csv"
	 * @throws 
	 * Error handling is also implemented in this method, where if
	 * an Empty file is input, it throws an Error, and also if the
	 * number of columns in any line is not equal to 3, it throws
	 * Invalid File, and when the last column, which is the Student ID,
	 * is not an Integer, it throws an Error.
	 */
	
	public void processing(String filename) throws Exception
	{
		File f=new File(filename);
		if (f.length() == 0) {
			throw new Exception("Empty File");
		}

		FileInputStream fis = new FileInputStream(f);
		 
		//Construct BufferedReader from InputStreamReader
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String data = null;
		while ((data = br.readLine()) != null) {
			String[] ranArray = data.split(",");
			for (int i=0;i<ranArray.length;i++)
			{
				ranArray[i]=ranArray[i].trim();
			}
			if (ranArray.length!=3)
			{
				throw new Exception("Invalid File");
			}
			try{
				Integer.parseInt(ranArray[2]);
			}
			catch (NumberFormatException e)
			{
				throw new Exception("Student ID Column must be an Integer");
			}
			for (int i=0;i<ranArray.length;i++)
			{
				ranArray[i]=ranArray[i].trim();
			}
			if(ProfToListOfClass.containsKey(ranArray[1]))
			{
				if(!(ProfToListOfClass.get(ranArray[1]).contains(ranArray[0])))
					ProfToListOfClass.get(ranArray[1]).add(ranArray[0]);
			}
			else
			{
				List<String> val = new ArrayList<String>();
				val.add(ranArray[0]);
				ProfToListOfClass.put(ranArray[1], val);
			}
			if(SidToListOfClass.containsKey(Integer.parseInt(ranArray[2])))
			{
				if(!(SidToListOfClass.get(Integer.parseInt(ranArray[2])).contains(ranArray[0])))
					SidToListOfClass.get(Integer.parseInt(ranArray[2])).add(ranArray[0]);
			}
			else
			{
				List<String> val = new ArrayList<String>();
				val.add(ranArray[0]);
				SidToListOfClass.put(Integer.parseInt(ranArray[2]), val);
			}
			
			if(ClassToListOfSid.containsKey(ranArray[0]))
			{
				if(!(ClassToListOfSid.get(ranArray[0]).contains(Integer.parseInt(ranArray[2]))))
					ClassToListOfSid.get(ranArray[0]).add(Integer.parseInt(ranArray[2]));
			}
			else
			{
				List<Integer> val = new ArrayList<Integer>();
				val.add(Integer.parseInt(ranArray[2]));
				ClassToListOfSid.put(ranArray[0], val);
			}
		}
	 
		br.close();
	}
	
	/**
	 * 
	 * @see
	 * This is the method which is used to display the result for
	 * The list of class sections being taught (I.e., unique Class/Professor pairs)
	 * 
	 * It gets the unique Class and Professor from the ProfToListOfClass Hashmap
	 */
	
	public void q1()
	{
		System.out.print("-----------------------------------------\n\n");
		System.out.print("Class Sections Taught:\n\n");
		Iterator<Entry<String, List<String>>> it = ProfToListOfClass.entrySet().iterator();
		while (it.hasNext()) {
	        @SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry)it.next();
	        String key = (String) pair.getKey();
	        @SuppressWarnings("unchecked")
			List<String> values = (List<String>) pair.getValue();
	        for (int i=0;i<values.size();i++)
            {
            	System.out.print(values.get(i)+", "+key+"\n");
            }
	    }
		System.out.print("\n");
	}
	
	/**
	 * 
	 * @see
	 * This is the method which is used to display the result for
	 * The list of classes being taken by each student
	 * 
	 * It gets the list of classes from the SidToListOfClass HashMap
	 */
	
	public void q2()
	{
		System.out.print("-----------------------------------------\n\n");
		System.out.print("Classes Taken By Each Student:\n\n");
		Iterator<Entry<Integer, List<String>>> it = SidToListOfClass.entrySet().iterator();
		while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
   			Map.Entry pair = (Map.Entry)it.next();
   	        int key = (Integer) pair.getKey();
            @SuppressWarnings("unchecked")
			List<String> values = (List<String>) pair.getValue();
            System.out.print(key+" : ");
            System.out.print(values.get(0));
            if (values.size()>0)
            {
            for (int i=1;i<values.size();i++)
            {
            	System.out.print(","+values.get(i));
            }
            System.out.print("\n");
            }
        }
		System.out.print("\n");
	}
	
	/**
	 * 
	 * @see
	 * This is the method which is used to display the result for
	 * How many students are registered for each Class?  List them.
	 * 
	 * It gets the number of students by finding the count of the List values of each key in the 
	 * ClassToListOfSid HashMap
	 */
	public void q3()
	{
		System.out.print("-----------------------------------------\n\n");
		System.out.print("How many students are registered for each class?\n\n");
		Iterator<Entry<String, List<Integer>>> it = ClassToListOfSid.entrySet().iterator();
		while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry pair = (Map.Entry)it.next();
	   	    String key = (String) pair.getKey();
			System.out.print(key+":"+ClassToListOfSid.get(key).size()+"\n");
		}
		System.out.print("\n");
	}
	
	/**
	 * 
	 * @see
	 * This is the method which is used to display the result for
	 * How many students take more than one Class?  List them.
	 * 
	 * It gets the number of students who have taken more than one Class from the
	 * SidToListOfClass HashMap
	 */
	
	public void q4()
	{
		System.out.print("-----------------------------------------\n");
		System.out.print("How many students take more than one class?\n\n");
		int NumOfStudents=0;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		Iterator<Entry<Integer, List<String>>> it = SidToListOfClass.entrySet().iterator();
        while (it.hasNext()) {
        	@SuppressWarnings("rawtypes")
            Map.Entry pair = (Map.Entry)it.next();
      	    Integer key = (Integer) pair.getKey();
            if (SidToListOfClass.get(key).size()>1)
            {
            	NumOfStudents++;
            	temp.add(key);
            }
		}
        if (NumOfStudents==0)
        {
        	System.out.print("Nothing to print");
        }
        else
        {
        System.out.print(NumOfStudents+" : ");
        for (int i = 0; i < temp.size(); i++) {
			System.out.print(temp.get(i)+" ");
		}
        }
		System.out.print("\n\n");
	}
	
	/**
	 * 
	 * @see
	 * This is the method which is used to display the result for
	 * How many professors teach more than one Class?
	 * 
	 * It gets the number of professors who are teaching more than one Class from the
	 * ProfToListOfClass HashMap
	 */
	
	public void q5()
	{
		System.out.print("-----------------------------------------\n");
		System.out.print("How many professors teach more than one class?\n\n");
		int NumberOfProfs=0;
		ArrayList<String> temp = new ArrayList<String>();
		Iterator<Entry<String, List<String>>> it = ProfToListOfClass.entrySet().iterator();
		while (it.hasNext()) {
	        @SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry)it.next();
	        String key = (String) pair.getKey();
            if (ProfToListOfClass.get(key).size()>1)
            {
            	NumberOfProfs++;
            	temp.add(key);
            }
		}
		if (NumberOfProfs==0)
		{
			System.out.print("Nothing to print");
		}
		else
		{
		System.out.print(NumberOfProfs+" : ");
		for (int i = 0; i < temp.size(); i++) {
			System.out.print(temp.get(i)+" ");
		}
		}
		System.out.print("\n\n");
	}
}

