package at.opr.uebung05.test;

import at.opr.uebung05.BinarySearchTree;

public class PersonTest {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
		Person p1 = new Person("Tobias", 13);
		Person p2 = new Person("Sebastian", 63);
		Person p3 = new Person("Niklas", 84);
		Person p4 = new Person("Laura", 21);
		Person p5 = new Person("Conny", 4);
		Person p6 = new Person("Julian", 103);
		Person p7 = new Person("Sandra", 39);
		Person p8 = new Person("Antonia", 8);

		BinarySearchTree t = new BinarySearchTree();
		
		t.insert(p1);
		t.insert(p2);
		t.insert(p3);
		t.insert(p4);
		t.insert(p5);
		t.insert(p6);
		t.insert(p7);
		
		System.out.println();
		System.out.println(t.toString());
		
		System.out.println();
		System.out.println("Anzahl der Personen: " + t.size());
		
		if(t.find(p3)) {
			System.out.println();
			System.out.println("Person gefunden: " + p3.getName());
		} else {
			System.out.println();
			System.out.println("Person nicht gefunden: " + p3.getName());
		}
		
		if(t.find(p8)) {
			System.out.println();
			System.out.println("Person gefunden: " + p8.getName());
		} else {
			System.out.println();
			System.out.println("Person nicht gefunden: " + p8.getName());
		}
		
		t.remove(p4);
		
		System.out.println();
		System.out.println(t.toString());
		
		System.out.println();
		System.out.println("Anzahl der Personen: " + t.size());
		
		System.out.println();
		
		System.out.println("Parent von " + p7.getName() + " ist: " + t.getParent(p7));
		
	}

}
