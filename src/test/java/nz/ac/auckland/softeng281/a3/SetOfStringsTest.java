package nz.ac.auckland.softeng281.a3;

// YOU SHOULD ADD NEW TEST CASES

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SetOfStringsTest {
	SetOfStrings set1, set2;

	@Before
	public void setUp() {
		set1 = new SetOfStrings();
		set2 = new SetOfStrings();
	}

	@After
	public void tearDown() {

	}

	//passing
	@Test
	public void testUnion() {

		set1.insertElement("a");
		set2.insertElement("b");

		SetOfStrings expected = new SetOfStrings();
		expected.insertElement("a");
		expected.insertElement("b");
		assertEquals(expected, set1.union(set2));
	}
	
	//passing
	@Test public void testUnionWithEmpty() {
		set1.insertElement("a");
		set1.insertElement("b");
		set1=set1.union(set2);
		SetOfStrings expected = new SetOfStrings();
		expected.insertElement("a");
		expected.insertElement("b");
		assertEquals(expected, set1);
	}

	//passing
	@Test public void testIntersection() {
		set1.insertElement("a");
		set1.insertElement("b");
		set2.insertElement("b");
		
		SetOfStrings expected = new SetOfStrings();
		expected.insertElement("b");
		assertEquals(expected, set1.intersection(set2));
	}
	
	//passing
	@Test public void testIntersectionEmpty() {
		set1.insertElement("a");
		SetOfStrings expected = new SetOfStrings();
		assertEquals(expected, set1.intersection(set2));
		
	}
	
	//passing 
	@Test public void testDifference() {
		set1.insertElement("a");
		set2.insertElement("a");
		set2.insertElement("b");
		
		SetOfStrings expected = new SetOfStrings();
		expected.insertElement("b");
		assertEquals(expected, set2.difference(set1));
	}
	
	// passing
	@Test public void testDifferenceEmpty() {
		set1.insertElement("a");
		set1.insertElement("b");
		
		SetOfStrings expected = new SetOfStrings();
		expected.insertElement("a");
		expected.insertElement("b");
		assertEquals(expected, set1.difference(set2));
	}
	
	//passing
	@Test public void testDifferenceEqual() {
		set1.insertElement("a");
		set2.insertElement("a");
		
		SetOfStrings expected = new SetOfStrings();
		assertEquals(expected, set1.difference(set2));
	}

	
}
