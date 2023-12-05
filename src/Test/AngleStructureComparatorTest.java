package Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.util.Set;
import geometry_objects.angle.*;
import geometry_objects.angle.comparators.AngleStructureComparator;
import org.junit.jupiter.api.Test;
import exceptions.FactException;
import geometry_objects.points.*;
import geometry_objects.Segment;
import geometry_objects.points.PointDatabase;
import input.InputFacade;
import input.components.FigureNode;
class AngleStructureComparatorTest extends AngleStructureComparator{
	@Test
	void compareTest1() throws FactException {
		
		//
		//           E
		//          /
		//         /
		//        /
		//       /
		//      D
		//     /
		//    /
		//   /
		//  /
		// A------B-----C
		//
		
		Point pointA = new Point("A", 0, 0);
		Point pointB = new Point("B", 3, 0);
		Point pointC = new Point("C", 6, 0);
		Point pointD = new Point("D", 3, 3);
		Point pointE = new Point("E", 6, 6);
		
		Segment segAC = new Segment(pointA, pointC);
		Segment segAB = new Segment(pointA, pointB);
		
		Segment segAD = new Segment(pointA, pointD);
		Segment segAE = new Segment(pointA, pointE);
		
		Angle angleLeft= new Angle(segAD, segAB);
		Angle angleRight = new Angle(segAC, segAE);
		
		assertEquals(compare(angleLeft, angleRight), -1);
		
		
		
		Angle angleLeft1 = new Angle(segAE, segAB);
		Angle angleRight1 = new Angle(segAD, segAB );;
		
		assertEquals(compare(angleLeft1, angleRight1), 1);
		assertEquals(compare(angleRight1, angleLeft1), -1);
		
	}
	@Test
	void comapreTest2() throws FactException {
		
		//
		// B         D
		// |        /
		// |       /
		// |      /
		// |     /
		// E    C
		// |   /
		// |  /
		// | /
		// |/
		// A---------F
		//
		
		//Building figure shown above
		
		Point A = new Point("A", 0, 0);
		Point B = new Point("B", 0, 10);
		Point C = new Point("C", 5, 5);
		Point D = new Point("D", 10, 10);
		Point E = new Point("E", 0, 5);
		Point F = new Point("F", 10, 0);
		
		Segment AB = new Segment(A, B);
		Segment AC = new Segment(A, C);
		Segment AD = new Segment(A, D);
		Segment AE = new Segment(A, E);
		Segment AF = new Segment(A, F);
		
		Angle BAD = new Angle(AB, AD);
		Angle EAC = new Angle(AE, AC);
		Angle BAF = new Angle(AB, AF);
		Angle EAD = new Angle(AE, AD);
		Angle BAC = new Angle(AB, AC);
		Angle DAF = new Angle(AD, AF);
		Angle CAF = new Angle(AC, AF);
		
		//Checking angles with different rays
		assertEquals(compare(BAD, BAF), Integer.MAX_VALUE);
		assertEquals(compare(BAD, DAF), Integer.MAX_VALUE);
		
		//Checking the unknown difference case
		assertEquals(compare(BAC, EAD), 0);
		assertEquals(compare(EAD, BAC), 0);
		
		//Left is larger
		assertEquals(compare(BAD, EAC), 1);
		assertEquals(compare(BAC, EAC), 1);
		
		//Right is larger
		assertEquals(compare(EAC, BAC), -1);
		assertEquals(compare(EAC, BAD), -1);
		assertEquals(compare(CAF, DAF), -1);
	}
}