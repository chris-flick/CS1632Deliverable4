import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.*;

import java.util.*;
import java.io.*;

public class HelpersTest {

	ArrayList<String> elements;
	HashMap<String, String> elementsDict;
	File elementsFile = new File("elements.txt");

	@Before
	public void setup(){
		elements = Helpers.readFile(elementsFile);
		elementsDict = Helpers.createHashMap(elements);
	}

	/*
	*	result should remove the space from the string
	*/
	@Test
	public void testRemoveInvalidChar1(){
		String result = Helpers.removeNonCharacters("Tsar JarJar");

		assertEquals("TsarJarJar", result);
	}

	/*
	*	result should remove all characters that aren't from the alphabet
	*/
	@Test
	public void testRemoveInvalidChar2(){
		String result = Helpers.removeNonCharacters("Tsar'@Me*ow");

		assertEquals("TsarMeow", result);
	}

	/*
	*	test to make sure that the first element found is Ts
	*/
	@Test
	public void testCheckElements1(){
		ArrayList<String> result = Helpers.checkElements("Tsar Ra", elementsDict);

		assertEquals("ts", result.get(0));
	}

	/*
	*	test to make sure that the second element found is Ar
	*/
	@Test
	public void testCheckElements2(){
		ArrayList<String> result = Helpers.checkElements("Tsar Ra", elementsDict);

		assertEquals("ar", result.get(1));
	}

	/*
	*	test to make sure that third element found is Ra and that space is skipped over
	*/
	@Test
	public void testCheckElements3(){
		ArrayList<String> result = Helpers.checkElements("Tsar Ra", elementsDict);

		assertEquals("ra", result.get(2));
	}

	// test that correct string is produced when given arraylist of elements
	@Test
	public void testPrintAbbr1(){
		ArrayList<String> elements = new ArrayList<String>();
		elements.add("ts");
		elements.add("ar");
		elements.add("ra");

		String print = Helpers.generateElementAbbr(elements);

		assertEquals("ts - ar - ra", print);
	}

	// test that null is returned when given null arraylist
	@Test
	public void testPrintAbbr2(){
		ArrayList<String> elements = null;

		String print = Helpers.generateElementAbbr(elements);

		assertNull(print);
	}

	// test that correct string of element names is produced when given arraylist of elements
	@Test
	public void testPrintName1(){
		ArrayList<String> elements = new ArrayList<String>();
		elements.add("ts");
		elements.add("ar");
		elements.add("ra");

		String print = Helpers.generateElementName(elements, elementsDict);

		assertEquals("Tennessine - Argon - Radium", print);
	}

	// test that null is returned when given null arraylist
	@Test
	public void testPrintName2(){
		ArrayList<String> elements = null;

		String print = Helpers.generateElementName(elements, elementsDict);

		assertNull(print);
	}
}