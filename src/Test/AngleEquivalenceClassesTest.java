package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.angle.comparators.AngleStructureComparator;
import geometry_objects.points.Point;
import geometry_objects.angle.*;

class AngleEquivalenceClassesTest {

	@Test
	void Addtest() {
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
		
		
		// initialize the comapator
		AngleStructureComparator comparator = new AngleStructureComparator();
		
		// initialize the AngleEquivalence class
		AngleEquivalenceClasses AEC = new AngleEquivalenceClasses(comparator);
		
		// adding all possible angle from the figure above 
		AEC.add(BAD);
		AEC.add(BAC);
		AEC.add(EAC);
		AEC.add(EAD);
		
		AEC.add(DAG);
		AEC.add(DAF);
		AEC.add(CAG);
		AEC.add(CAF);
		
		AEC.add(BAG);
		AEC.add(BAF); 
		AEC.add(EAG);
		AEC.add(EAF); 
		
		// tsting how many class are there
		assertEquals(AEC.numClasses(),3);
		
		// testing whhether it contain all Angle in the linkedList
		assertTrue(AEC.contains(BAD));
		assertTrue(AEC.contains(BAC));
		assertTrue(AEC.contains(EAD));
		
		assertTrue(AEC.contains(DAG));
		assertTrue(AEC.contains(DAF));
		assertTrue(AEC.contains(CAF));
		
		assertTrue(AEC.contains(BAG));
		assertTrue(AEC.contains(BAF));
		assertTrue(AEC.contains(EAF));
		
		assertEquals(AEC.size(),9);
		
	
		
		
		
	}

}
