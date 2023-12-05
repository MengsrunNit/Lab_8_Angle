package geometry_objects.angle;

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
		
		AngleEquivalenceClasses AEC = new AngleEquivalenceClasses(comparator);
		
		
	}

}
