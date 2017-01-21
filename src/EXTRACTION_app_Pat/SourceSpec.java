package EXTRACTION_app_Pat;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author      Patrick Ernest <pernes2@uic.edu, taptrick47@gmail.com>
 * @version     1.0
 * @date        2016-04-08
 * 
 * SourceSpec.java
 * 
 * @see
 * This is the Junit test java program which tests the Source.java code for
 * side effects and the Junit test code(SourceSpec.java) has a coverage of approximately 91%
 */

public class SourceSpec {
	
	/**
	 * 
	 * @see
	 * First a file created for the purpose of testing. The methodology is
	 * going to be used in the testing program is that before every
	 * test case a file is created and data is written into it so as to 
	 * test the corresponding method. After the test has completed the 
	 * file is deleted to save space, therefore improving the space complexity.
	 */
	
	File testFileInput;
	//ByteArrayOutputStream outContent;
	
    @Before
    public void setUp() {
    	//outContent = new ByteArrayOutputStream();
    	testFileInput = new File("tempFile.csv");
    }

    @After
    public void tearDown() {
    	testFileInput.delete();
    	//System.setOut(null);
    }
    
    /**
	 * 
	 * @see
	 * Test for Empty file
	 */

	@Test
	public void emptyFileTest() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("");
			bw.close();
			Source source = new Source();
			source.processing("tempFile.csv");
		} catch (Exception e) {
			System.out.println("Empty Test");
			assertEquals("Empty File", e.getMessage());
		}	
	}
	
	/**
	 * 
	 * @see
	 * Test for number of extra columns
	 */
	
	@Test
	public void extraColumnTest(){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("something, something, something, something");
			bw.newLine();
			bw.close();
			Source source = new Source();
			source.processing("tempFile.csv");
		} catch (Exception e) {
			System.out.println("extraColumnTest");
			assertEquals("Invalid File", e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @see
	 * Test for missing columns
	 */
	
	@Test
	public void missingColumnTest(){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("something, something");
			bw.newLine();
			bw.close();
			Source source = new Source();
			source.processing("tempFile.csv");
		} catch (Exception e) {
			System.out.println("missingColumnTest");
			assertEquals("Invalid File", e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @see
	 * Test for student ID being an Integer
	 */

	@Test
	public void stringStudentIDTest(){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("something, something, something");
			bw.newLine();
			bw.close();
			Source source = new Source();
			source.processing("tempFile.csv");
		} catch (Exception e) {
			System.out.println("stringStudentIDTest");
			assertEquals("Student ID Column must be an Integer", e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @see
	 * Test for HashMap ProfToListOfClass
	 */
	
	@Test
	public void CheckProfToListOfClass(){
		FileOutputStream fos;
		Source source = new Source();
		Map<String, List<String>> MapTemp = new HashMap<String, List<String>>();
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.close();
			source.processing("tempFile.csv");
			List<String> TempList = new ArrayList<String>();
			TempList.add("Chemistry");
			MapTemp.put("Joseph", TempList);
			System.out.println("CheckProfToListOfClass");
			assertTrue(MapTemp.equals(source.ProfToListOfClass));

		} catch (Exception e) {
			fail("Exception");
			//assertEquals(true, mapA.euals(mapB););
		}
		
	}
	
	/**
	 * 
	 * @see
	 * Test for HashMap ClassToListOfSid
	 */
	
	@Test
	public void CheckClassToListOfSid(){
		FileOutputStream fos;
		Source source = new Source();
		Map<String, List<Integer>> MapTemp = new HashMap<String, List<Integer>>();
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.write("Chemistry,Joseph,569");
			bw.newLine();
			bw.close();
			source.processing("tempFile.csv");
			List<Integer> TempList = new ArrayList<Integer>();
			TempList.add(456789);
			TempList.add(569);
			MapTemp.put("Chemistry", TempList);
			System.out.println("CheckClassToListOfSid");
			assertTrue(MapTemp.equals(source.ClassToListOfSid));

		} catch (Exception e) {
			fail("Exception");
			//assertEquals(true, mapA.euals(mapB););
		}
		
	}
	
	/**
	 * 
	 * @see
	 * Test for HashMap SidToListOfClass
	 */
	
	@Test
	public void CheckSidToListOfClass(){
		FileOutputStream fos;
		Source source = new Source();
		Map<Integer, List<String>> MapTemp = new HashMap<Integer, List<String>>();
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.close();
			source.processing("tempFile.csv");
			List<String> TempList = new ArrayList<String>();
			TempList.add("Chemistry");
			MapTemp.put(456789, TempList);
			System.out.println("CheckSidToListOfClass");
			assertTrue(MapTemp.equals(source.SidToListOfClass));

		} catch (Exception e) {
			fail("Exception");
			//assertEquals(true, mapA.euals(mapB););
		}
		
	}
	
	/**
	 * 
	 * @see
	 * Test for q1 - The list of class sections being taught (I.e., unique Class/Professor pairs)
	 */
	
	@Test
	public void Checkq1(){
		FileOutputStream fos;
		Source source = new Source();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.close();
			source.processing("tempFile.csv");
			source.q1();
			String s="-----------------------------------------\n\nClass Sections Taught:\n\nChemistry, Joseph\n\n";
			assertEquals(s, outContent.toString());

		} catch (Exception e) {
			fail("Exception");
		}	
	}
	
	/**
	 * 
	 * @see
	 * Test for q2 - The list of classes being taken by each student
	 */
	
	@Test
	public void Checkq2(){
		FileOutputStream fos;
		Source source = new Source();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.write("History,Robert,456789");
			bw.newLine();
			bw.close();
			source.processing("tempFile.csv");
			source.q2();
			String s="-----------------------------------------\n\nClasses Taken By Each Student:\n\n456789 : Chemistry,History\n\n";
			assertEquals(s, outContent.toString());

		} catch (Exception e) {
			fail("Exception");
		}	
	}
	
	/**
	 * 
	 * @see
	 * Test for q3 - How many students are registered for each Class?  List them.
	 */
	
	@Test
	public void Checkq3(){
		FileOutputStream fos;
		Source source = new Source();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.close();
			source.processing("tempFile.csv");
			source.q3();
			String s="-----------------------------------------\n\nHow many students are registered for each class?\n\nChemistry:1\n\n";
			assertEquals(s, outContent.toString());

		} catch (Exception e) {
			fail("Exception");
		}	
	}
	
	/**
	 * 
	 * @see
	 * Test for q4 - How many students take more than one Class?  List them.
	 */
	
	@Test
	public void Checkq4(){
		FileOutputStream fos;
		Source source = new Source();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.write("History,Robert,456789");
			bw.newLine();
			bw.close();
			source.processing("tempFile.csv");
			source.q4();
			String s="-----------------------------------------\nHow many students take more than one class?\n\n1 : 456789 \n\n";
			assertEquals(s, outContent.toString());

		} catch (Exception e) {
			fail("Exception");
		}	
	}
	
	/**
	 * 
	 * @see
	 * Test for q4 - How many students take more than one Class?  List them. (When there are no students
	 * with more than one class)
	 */
	
	@Test
	public void Checkq4IfNoStudentsHaveTakenMoreThanOneClass(){
		FileOutputStream fos;
		Source source = new Source();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.close();
			source.processing("tempFile.csv");
			source.q4();
			String s="-----------------------------------------\nHow many students take more than one class?\n\nNothing to print\n\n";
			assertEquals(s, outContent.toString());

		} catch (Exception e) {
			fail("Exception");
		}	
	}
	
	/**
	 * 
	 * @see
	 * Test for q5 - How many professors teach more than one Class?
	 */
	
	@Test
	public void Checkq5(){
		FileOutputStream fos;
		Source source = new Source();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.write("History,Joseph,6709");
			bw.newLine();
			bw.close();
			source.processing("tempFile.csv");
			source.q5();
			String s="-----------------------------------------\nHow many professors teach more than one class?\n\n1 : Joseph \n\n";
			assertEquals(s, outContent.toString());

		} catch (Exception e) {
			fail("Exception");
		}	
	}
	
	/**
	 * 
	 * @see
	 * Test for q5 - How many professors teach more than one Class? (When there are no professors teaching
	 * more than one class)
	 */
	
	@Test
	public void Checkq5IfNoProfessorsTeachMoreThanOneClass(){
		FileOutputStream fos;
		Source source = new Source();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		try {
			fos = new FileOutputStream(testFileInput);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
			bw.write("Chemistry,Joseph,456789");
			bw.newLine();
			bw.close();
			source.processing("tempFile.csv");
			source.q5();
			String s="-----------------------------------------\nHow many professors teach more than one class?\n\nNothing to print\n\n";
			assertEquals(s, outContent.toString());

		} catch (Exception e) {
			fail("Exception");
		}	
	}	
}
