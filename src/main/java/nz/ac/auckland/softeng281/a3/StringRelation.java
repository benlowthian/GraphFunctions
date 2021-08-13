package nz.ac.auckland.softeng281.a3;

import java.util.List;

//*******************************
//YOU SHOULD MODIFY THIS CLASS FOR TASK2, TASK3, TASK4, TASK5, and TASK6.
//- you can add all the methods that you need.
//- you cannot modify the method signature (return type, method name and parameters) of the existing methods
//- you can change the code of the existing methods but at your own risk! the program might not work properly
//*******************************

/**
 * A class for binary relations over a set of strings.
 *
 * @author Partha Roop
 */
public class StringRelation extends SetOfStrings {
	// This is a set used to store the members of the binary relation
	private SetOfStrings setMembers;


	/**
	 * create an empty StringRelation when no arguments are given -- constructor In
	 * this event the setMembers is a new SetOfStrings with no members yet
	 */
	public StringRelation() {
		this(new SetOfStrings());
	}

	/**
	 * create a StringRelation object
	 */
	public StringRelation(SetOfStrings setMembers) {
		super();
		this.setMembers = setMembers;
	}

	public void setSetMemberElements(SetOfStrings set) {
		setMembers = set;
	}

	public boolean isValid() {
		SetOfStrings product = setMembers.product(setMembers);
		return product.isSupersetOf(this);
	}

	// method to check if reflexive
	public boolean isReflexive() {
		if (!isValid()) {
			return false;
		}
		if (this.isEmpty()) {
			return false;
		}

		// running through the set
		for (String member : setMembers.getElements()) {
			// splitting the string at the comma
			String point = (member + "," + member);
			if (!isMember(point)){
				// if (a,a) is not a member then return false
				return false;
			}	
		}

		return true;
	}

	// method to check if symmetric
	public boolean isSymmetric() {
		if (!isValid()) {
			return false;
		}
		// running through the set
		for (String member : this.getElements()) {
			// splitting the member at the comma
			String [] set = member.split(",");
			// creating a temporary variable to check for symmetry
			String v = (set[1] + "," + set[0]);
			if (!isMember(v)) {
				// if (a,b) and (b,a) are not both present then return false
				return false;
			}
		}
		return true;
	}

	// method to check if transitive
	public boolean isTransitive() {
		if (!isValid()) {
			return false;
		}
		List <String> relations = this.getElements();
		int i,j;
		String v1,v2,v3,v4;

		i = 0;
		
		// creating nested while loop
		while(i<relations.size()) {
			// running through the points in the set
			String[] relation1 = relations.get(i).split(",");
			v1 = relation1[0];
			v2 = relation1[1];

			j = 0;
			
			// running through the points again
			while(j<relations.size()) {
				String[] relation2 = relations.get(j).split(",");
				v3 = relation2[0];
				v4 = relation2[1];

				// checking if last point of the first vertex is the same as the first point in the second vertex
				if (v2.equals(v3)) {
					if(!isMember(v1 + "," + v4)) {
						// if transitivity does not hold return false
						return false;
					}
				}
				j++;
			}
			i++;
		}

		return true;
	}
	
	// function to check if equivalence
	public boolean isEquivalence() {
		return isReflexive() && isSymmetric() && isTransitive();
	}

	/**
	 * do not change this method
	 * 
	 * @param node
	 * @return
	 */
	
	public SetOfStrings eqClass(String node) {
		if (!isEquivalence()) {
			System.out.println("Can't compute equivalence class.. NOT an equivalence relation");
			return new SetOfStrings();
		}
		return computeEqClass(node);
	}

	// method to compute equivalence class
	public SetOfStrings computeEqClass(String node) {
		SetOfStrings out = new SetOfStrings();

		for (String relations : this.getElements()) {
			for (String member : setMembers.getElements()) {
				// checking if they are in the same class
				if (relations.startsWith(node) && relations.endsWith(member)) {
					out.insertElement(member);
				}
			}
		}
		return out;
	}
}