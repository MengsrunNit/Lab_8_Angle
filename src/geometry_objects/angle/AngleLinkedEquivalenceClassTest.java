package geometry_objects.angle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.angle.comparators.AngleStructureComparator;
import geometry_objects.points.Point;

class AngleLinkedEquivalenceClassTest extends AngleStructureComparator{

	
	@Test
	void addTest() {
		Point A = new Point("A", 0, 0);
		Point B = new Point("B", 0, 10);
		Point C = new Point("C", 5, 5);
		Point D = new Point("D", 10, 10);
		Point E = new Point("E", 0, 5);
		Point F = new Point("F", 10, 0);
		Point G = new Point("G", 5, 0);
		
		Segment AB = new Segment(A, B);
		Segment AC = new Segment(A, C);
		Segment AD = new Segment(A, D);
		Segment AE = new Segment(A, E);
		Segment AF = new Segment(A, F);
		Segment AG = new Segment(A, G); 
		
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
		
		// Second test 
		Angle DAF = null;
		try {
			DAF = new Angle(AD, AF);
		} catch (FactException e) {
			System.out.println("Angle BAD");
		}
		
		
		Angle DAG = null;
		try {
			DAG = new Angle(AD, AG);
		} catch (FactException e) {
			System.out.println("Angle BAD");
		}
		
		
		Angle CAF = null;
		try {
			CAF = new Angle(AC, AF);
		} catch (FactException e) {
			System.out.println("Angle BAD");
		}
		
		
		Angle CAG = null;
		try {
			CAG = new Angle(AC, AG);
		} catch (FactException e) {
			System.out.println("Angle BAD");
		}
		
		
		AngleStructureComparator comparator = new AngleStructureComparator();
		
		AngleLinkedEquivalenceClass ALE1 = new AngleLinkedEquivalenceClass(comparator);
		
		// Adding the Angle to the Linked Equivalence classes 
		
		ALE1.add(EAC);
		ALE1.add(BAD);
		ALE1.add(BAC);
		ALE1.add(EAD);
		
		// adding invalid angle to the LinkedEquivalence clases 
		
		ALE1.add(CAG);
		ALE1.add(DAF);
		
		
		assertTrue(ALE1.canonical().equals(EAC));
		assertEquals(ALE1.size(), 3);
		assertTrue(ALE1.belongs(BAD));
		assertTrue(ALE1.belongs(BAC));
		assertTrue(ALE1.belongs(EAD));
		
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
		
		assertTrue(ALE2.canonical().equals(CAG));
		assertEquals(ALE2.size(), 3);
		assertTrue(ALE2.belongs(DAF));
		assertTrue(ALE2.belongs(DAG));
		assertTrue(ALE2.belongs(CAF));
		
		
	}

}