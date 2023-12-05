package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.angle.AngleLinkedEquivalenceClass;
import geometry_objects.angle.comparators.AngleStructureComparator;
import geometry_objects.points.Point;

class AngleLinkedEquivalenceClassTest extends AngleStructureComparator{

	
	@Test
	void AngleLinkedEquivalenceTest() {
		
		
		//	B        D
		//	|       /
		//	|	   /
		//	|	  /
		//	E	 C
		//	|	/
		//	|  /	
		//	| /
		//	A----G----F
		//
		
		// create a test point 
		Point A = new Point("A", 0, 0);
		Point B = new Point("B", 0, 10);
		Point C = new Point("C", 5, 5);
		Point D = new Point("D", 10, 10);
		Point E = new Point("E", 0, 5);
		Point F = new Point("F", 10, 0);
		Point G = new Point("G", 5, 0);
		
		// connect the A to each Ponit 
		Segment AB = new Segment(A, B);
		Segment AC = new Segment(A, C);
		Segment AD = new Segment(A, D);
		Segment AE = new Segment(A, E);
		Segment AF = new Segment(A, F);
		Segment AG = new Segment(A, G); 
		
		
		//
		// FIRST BIG UPPER ANGLE
		//
		Angle BAD = null;
		try {
			BAD = new Angle(AB, AD);
		} catch (FactException e) {
			System.out.println("Angle BAD");
		}
		
		Angle BAC = null;
		try {
			BAC = new Angle(AB, AC);
		} catch (FactException e) {
			System.out.println("Angle BAC");
		}
		
		Angle EAC = null;
		try {
			EAC = new Angle(AE, AC);
		} catch (FactException e) {
			System.out.println("Angle EAC");
		}
		
		Angle EAD = null;
		try {
			EAD = new Angle(AE, AD);
		} catch (FactException e) {
			System.out.println("Angle BAD");
		}
		
		//
		// Second SMALLER ANGLE delcaration
		//
		Angle DAF = null;
		try {
			DAF = new Angle(AD, AF);
		} catch (FactException e) {
			System.out.println("Angle DAF");
		}
		
		
		Angle DAG = null;
		try {
			DAG = new Angle(AD, AG);
		} catch (FactException e) {
			System.out.println("Angle DAG");
		}
		
		
		Angle CAF = null;
		try {
			CAF = new Angle(AC, AF);
		} catch (FactException e) {
			System.out.println("Angle CAF");
		}
		
		
		Angle CAG = null;
		try {
			CAG = new Angle(AC, AG);
		} catch (FactException e) {
			System.out.println("Angle CAG");
		}
		
		//
		//The right angle part of the traingle 
		//
		Angle BAF = null;
		try {
			BAF = new Angle(AB, AF);
		} catch (FactException e) {
			System.out.println("Angle DAF");
		}
		
		
		Angle BAG = null;
		try {
			BAG = new Angle(AB, AG);
		} catch (FactException e) {
			System.out.println("Angle DAG");
		}
		
		
		Angle EAF = null;
		try {
			EAF = new Angle(AE, AF);
		} catch (FactException e) {
			System.out.println("Angle CAF");
		}
		
		
		Angle EAG = null;
		try {
			EAG = new Angle(AE, AG);
		} catch (FactException e) {
			System.out.println("Angle CAG");
		}
		
		
		
		// create a AngleStrutureComparator comparator 
		AngleStructureComparator comparator = new AngleStructureComparator();
		
		// first AngleEquivalnceClass initation
		AngleLinkedEquivalenceClass ALE1 = new AngleLinkedEquivalenceClass(comparator);
		
		// Adding the Angle to the Linked Equivalence classes 
		
		ALE1.add(EAC);
		ALE1.add(BAD);
		ALE1.add(BAC);
		ALE1.add(EAD);
		
		// adding invalid angle to the LinkedEquivalence clases 
		
		ALE1.add(CAG);
		ALE1.add(DAF);
		
		// testing case 
		assertTrue(ALE1.canonical().equals(EAC));
		assertEquals(ALE1.size(), 3);
		assertTrue(ALE1.belongs(BAD));
		assertTrue(ALE1.belongs(BAC));
		assertTrue(ALE1.belongs(EAD));
		
		// Second AngleEquivalnceClass initation
		AngleLinkedEquivalenceClass ALE2 = new AngleLinkedEquivalenceClass(comparator);
		
		// adding all the belonged Angle in 
		ALE2.add(DAF);
		ALE2.add(DAG);
		ALE2.add(CAF);
		ALE2.add(CAG);
		
		// adding Invalid Angle 
		ALE2.add(EAC);
		ALE2.add(BAD);
		ALE2.add(BAC);
		ALE2.add(EAD);
		
		// Testing case
		assertTrue(ALE2.canonical().equals(CAG));
		assertEquals(ALE2.size(), 3);
		assertTrue(ALE2.belongs(DAF));
		assertTrue(ALE2.belongs(DAG));
		assertTrue(ALE2.belongs(CAF));
		
		// Third AngleEquivalnceClass initation
		AngleLinkedEquivalenceClass ALE3 = new AngleLinkedEquivalenceClass(comparator);
		
		// adding all belong Angle in 
		ALE3.add(BAF);
		ALE3.add(BAG);
		ALE3.add(EAF);
		ALE3.add(EAG);
		
		// adding Invalid Angle 
		ALE3.add(EAC);
		ALE3.add(BAD);
		ALE3.add(BAC);
		ALE3.add(EAD);
		
		// adding another invalid angle 
		ALE3.add(DAF);
		ALE3.add(DAG);
		ALE3.add(CAF);
		ALE3.add(CAG);
		
		// Testing case
		assertTrue(ALE3.canonical().equals(EAG));
		assertEquals(ALE2.size(), 3);
		assertTrue(ALE3.belongs(BAF));
		assertTrue(ALE3.belongs(BAG));
		assertTrue(ALE3.belongs(EAF));
		
		
		
	}

}
