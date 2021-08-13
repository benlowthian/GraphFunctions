package nz.ac.auckland.softeng281.a3;

//*******************************

//YOU SHOULD MODIFY THIS CLASS FOR TASK 1
//- you can add all the methods that you need.
//- you cannot modify the method signature (return type, method name and parameters) of the existing methods
//- you can change the code of the existing methods but at your own risk! the program might not work properly
//*******************************

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * A class to create a Set of Strings and to support set operations on these
 * SetOfStrings
 *
 * @author PARTHA ROOP
 */

public class SetOfStrings {
	private List<String> elements;

	/**
	 * create an empty SetOfStrings -- constructor
	 */
	public SetOfStrings() {
		elements = new ArrayList<>();
	}

	public void clear() {
		elements.clear();
	}

	public Enumeration<String> elements() {
		return Collections.enumeration(elements);
	}
	/**
	 * returns true if the SetOfStrings is an empty set false otherwise
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	/**
	 * returns the size of the set
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * Checks if a given String argument is a member of the set. return true if
	 * 'element' is a member of the set; false otherwise.
	 *
	 * @param element a String
	 */
	public boolean isMember(String element) {
		return elements.contains(element);
	}

	/**
	 * return an enumeration of the elements of this SetOfStrings
	 */
	public List<String> getElements() {
		return elements;
	}

	/**
	 * insets a String 'newElement' to the set. care should be taken to insert so
	 * that the property of set is not violated after insertion.
	 *
	 * @param newElement a String
	 */
	public void insertElement(String newElement) {
		if (!isMember(newElement)) {
			elements.add(newElement);
		}
	}

	/**
	 * deletes a string 'element' from the set if 'element' is not a member of the
	 * set then an exception called NoSuchElementException is thrown.
	 *
	 * @param element a String
	 * @throws NoSuchElementException if the String 'element' is not a member of the
	 *                                set
	 */
	public void deleteElement(String element) throws NoSuchElementException {
		elements.remove(element);
	}

	private void addAllElements(SetOfStrings set1) {
		Enumeration<String> enum1 = set1.elements();
		while (enum1.hasMoreElements()) {
			insertElement((String) enum1.nextElement());
		}
	}

	/**
	 * determines the union of the current set with 'other' and returns the new set
	 * which is the union of these two sets. This operation SHOULDN'T MODIFY EITHER
	 * OF THE INPUT SETS.
	 *
	 * @param other a SetOfStrings
	 */
	public SetOfStrings union(SetOfStrings other) {
		SetOfStrings out = new SetOfStrings();
		out.addAllElements(this);
		out.addAllElements(other);
		return out;

	}

	/**
	 * determines the intersection of the current set with 'other' and returns the
	 * new set which is the intersection of these two sets. This operation SHOULDN'T
	 * MODIFY EITHER OF THE INPUT SETS.
	 *
	 * @param other a SetOfStrings
	 */

	// intersection method
	public SetOfStrings intersection(SetOfStrings other) {
		SetOfStrings out = new SetOfStrings();
		// checking for the smaller set
		if (this.size() >= other.size()) {
			// running through the smaller set
			for (String element : other.getElements()) {
				// checking if element is in both sets and adding to output set if true
				if (isMember(element)) {
					out.insertElement(element);
				}
			}
		} else {
			// if the other set is the smaller one
			for (String element : other.getElements()) {
				if (isMember(element)) {
					out.insertElement(element);
				}
			}
		}
		return out;
	}

	/**
	 * determines the difference of the current set with 'other' and returns a new
	 * set that is composed of all the elements that are in the current set but not
	 * in "other" set. This operation SHOULDN'T MODIFY EITHER OF THE INPUT SETS.
	 *
	 * @param other a SetOfStrings
	 * @return difference of the current object and other
	 */

	// difference method
	public SetOfStrings difference(SetOfStrings other) {
		SetOfStrings out = new SetOfStrings();

		// running through the set given
		for (String element : this.getElements()) {
			// checking if the element is not in the other set
			if (!other.isMember(element)) {
				// if not then add to output set
				out.insertElement(element);
			}
		}

		return out;
	}

	/**
	 * determines the product of the current set with other and returns a new
	 * SetOfStrings representing the product. In the product, the pairs are denoted
	 * by a new string "a,b" where "a" is a member of the first set and "b" is a
	 * member of the second set.
	 *
	 * @param other a SetOfStrings
	 * @return product of the current object and other
	 */

	public SetOfStrings product(SetOfStrings other) {
		SetOfStrings out = new SetOfStrings();
		for (String elThis : elements) {
			for (String elOther : elements) {
				out.insertElement(elThis + "," + elOther);
			}
		}
		return out;
	}

	/**
	 * determines if this is a super set of other (i.e., if other is a subset of
	 * this) It returns true when every member of other is present in the current
	 * set
	 *
	 * @param other a SetOfStrings
	 * @return true if the current object is a superset of other, false otherwise
	 */
	public boolean isSupersetOf(SetOfStrings other) {
		for (String elOther : other.elements) {
			if (!isMember(elOther)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Displays the contents of the SetOfStrings
	 */
	public void displaySet() {
		for (String el : elements) {
			System.out.println("next element: " + el);
		}
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SetOfStrings)) {
			return false;
		}
		SetOfStrings set = (SetOfStrings) other;
		return set.isSupersetOf(this) && this.isSupersetOf(set);
	}

}
