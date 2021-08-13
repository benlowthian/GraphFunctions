package nz.ac.auckland.softeng281.a3;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringRelationTest {

	private StringRelation relation;
	private SetOfStrings setMembers;

	@Before
	public void setUp() {
		setMembers = new SetOfStrings();
		relation = new StringRelation(setMembers);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testReflexive() {
		relation.insertElement("1,1");
		setMembers.insertElement("1");
		assertTrue(relation.isReflexive());
	}
	@Test
	public void testReflexiveTwoElemIncorrectRel() {
		relation.insertElement("1,1");
		relation.insertElement("2,1");

		setMembers.insertElement("1");
		setMembers.insertElement("2");
		
		assertEquals(false, relation.isReflexive());
	}
	@Test
	public void testReflexiveMultipleElem() {
		relation.insertElement("1,1");
		relation.insertElement("2,2");
		relation.insertElement("3,3");
		relation.insertElement("4,4");

		setMembers.insertElement("1");
		setMembers.insertElement("2");
		setMembers.insertElement("3");
		setMembers.insertElement("4");
		assertTrue(relation.isReflexive());
	}
	
	@Test
	public void testReflexiveEmptySet() {
		relation.insertElement("1,1");
		
		// false because cannot build relation on empty set
		assertEquals(false, relation.isReflexive()); 
	}
	@Test
	public void testReflexiveEmptyRelation() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		
		// false because there is no (v, v) in the relation for every element v in the set
		assertEquals(false, relation.isReflexive());
	}
	@Test
	public void testReflexiveEmptySetEmptyRelation() {
		
		assertTrue(relation.isReflexive());
	}
	@Test
	public void testSymmetric() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");

		relation.insertElement("1,2");
		relation.insertElement("2,1");
		
		assertTrue(relation.isSymmetric());

	}
	@Test
	public void testSymmetricNotObvious() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");

		relation.insertElement("1,1");
		relation.insertElement("2,1");
		relation.insertElement("1,2");
		
		assertTrue(relation.isSymmetric());

	}
	
	@Test
	public void testSymmetricEmptySet() {
		relation.insertElement("1,1");
		relation.insertElement("2,1");
		
		assertEquals(false, relation.isSymmetric());

	}
	@Test
	public void testSymmetricEmptyRelation() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		
		assertTrue(relation.isSymmetric());

	}
	@Test
	public void testSymmetricEmptySetEmptyRelation() {
		assertTrue(relation.isSymmetric());
	}
	@Test
	public void testTransitive() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		setMembers.insertElement("3");
		
		relation.insertElement("1,2");
		relation.insertElement("2,3");
		relation.insertElement("1,3");

		assertTrue(relation.isTransitive());
	}
	@Test
	public void testTransitiveNotObvious1() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		setMembers.insertElement("3");
		
		relation.insertElement("1,1");
		relation.insertElement("2,2");
		relation.insertElement("3,3");

		assertTrue(relation.isTransitive());
	}
	@Test
	public void testTransitiveNotObvious2() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		setMembers.insertElement("3");
		
		relation.insertElement("1,1");
		relation.insertElement("2,2");
		relation.insertElement("3,3");
		relation.insertElement("1,2");
		
		assertTrue(relation.isTransitive());
	}
	@Test
	public void testTransitiveFailingCase() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		setMembers.insertElement("3");
		
		relation.insertElement("1,1");
		relation.insertElement("2,3");
		relation.insertElement("1,2");
		
		assertEquals(false, relation.isTransitive());
	}
	@Test
	public void testTransitiveEmptySet() {
		relation.insertElement("1,1");
		relation.insertElement("2,1");
		assertEquals(false, relation.isTransitive());

	}
	@Test
	public void testTransitiveEmptyRelation() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		
		assertTrue(relation.isTransitive());

	}
	@Test
	public void testTransitiveEmptySetEmptyRelation() {
		assertTrue(relation.isTransitive());
	}
	@Test
	public void testEquivalence() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		setMembers.insertElement("3");
		
		relation.insertElement("1,1");
		relation.insertElement("2,2");
		relation.insertElement("3,3");
		
		assertTrue(relation.isEquivalence());

	}
	
	@Test
	public void testEquivalenceNotObvious() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		setMembers.insertElement("3");
		
		relation.insertElement("1,1");
		relation.insertElement("2,2");
		relation.insertElement("3,3");
		relation.insertElement("1,2");
		relation.insertElement("2,1");
		assertTrue(relation.isEquivalence());

	}
	public void testEquivalenceEmptySetEmptyRelation() {
		assertTrue(relation.isEquivalence());
	}
	public void testEquivalenceEmptySet() {
		relation.insertElement("1,1");
		relation.insertElement("2,1");
		assertEquals(false, relation.isEquivalence());

	}
	@Test
	public void testEquivalenceEmptyRelation() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		
		assertEquals(false, relation.isEquivalence());

	}
	@Test
	public void testEqClass() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		setMembers.insertElement("3");
		
		relation.insertElement("1,1");
		relation.insertElement("2,2");
		relation.insertElement("3,3");
		
		SetOfStrings expected = new SetOfStrings();
		expected.insertElement("1");
		assertEquals(expected, relation.eqClass("1"));

	}
	
	@Test
	public void testEqClassNotObvious1() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		setMembers.insertElement("3");
		
		relation.insertElement("1,1");
		relation.insertElement("2,2");
		relation.insertElement("3,3");
		relation.insertElement("1,2");
		relation.insertElement("2,1");

		SetOfStrings expected = new SetOfStrings();
		expected.insertElement("1");
		expected.insertElement("2");
		
		assertEquals(expected, relation.eqClass("1"));

	}
	
	@Test
	public void testEqClassNotObvious2() {
		setMembers.insertElement("1");
		setMembers.insertElement("2");
		setMembers.insertElement("3");
		
		relation.insertElement("1,1");
		relation.insertElement("2,2");
		relation.insertElement("3,3");
		relation.insertElement("1,2");
		relation.insertElement("2,1");
		relation.insertElement("1,3");
		relation.insertElement("3,1");
		relation.insertElement("3,2");
		relation.insertElement("2,3");

		SetOfStrings expected = new SetOfStrings();
		expected.insertElement("1");
		expected.insertElement("2");
		expected.insertElement("3");

		assertEquals(expected, relation.eqClass("1"));

	}


}
