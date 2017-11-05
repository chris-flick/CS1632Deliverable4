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

	@Test
	public void testRemoveInvalidChar1(){
		String result = Helpers.removeNonCharacters("Tsar JarJar");

		assertEquals("TsarJarJar", result);
	}

	@Test
	public void testRemoveInvalidChar2(){
		String result = Helpers.removeNonCharacters("Tsar'@Me*ow");

		assertEquals("TsarMeow", result);
	}
}