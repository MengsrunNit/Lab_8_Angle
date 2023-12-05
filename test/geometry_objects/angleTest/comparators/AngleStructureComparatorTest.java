package geometry_objects.angle.comparators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.PointDatabase;
import input.InputFacade;
import input.components.FigureNode;

class AngleStructureComparatorTest {

	@Test
	void compareTest() {
		
		Point pointA = new Point("A", 0, 0); 
		Point pointB = new Point("B", 3, 0); 
		Point pointC = new Point("C", 6, 0); 
		Point pointD = new Point("D", 3, 3); 
		Point pointE = new Point("E", 6, 6); 
		 
		Segment segAC = new Segment(pointA, pointC); 
		Segment segAB = new Segment(pointA, pointB);
		
		Segment segAD = new Segment(pointA, pointC); 
		Segment segAE = new Segment(pointA, pointB);
		
		Angle angleLeft = new Angle(segAC, segAB ); 
		Angle angleRight = new Angle(segAE, segAD); 
		
		
		assertEquals(AngleStructureComparator.compare(angleLeft, angleRight), 1);
		
		
		
		
		
		
		
	}

}
