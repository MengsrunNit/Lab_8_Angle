package geometry_objects;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
import geometry_objects.points.*;
import org.junit.jupiter.api.Test;

public class SegmentTest {

	public Set<Point> pointBuilder() {
		Set<Point> _points = new HashSet<Point>();
		_points.add(new Point(18, 6));
		_points.add(new Point(21, 8));
		_points.add(new Point(24, 11));
		_points.add(new Point(8, 9));
		_points.add(new Point(30, 36));
		return _points;
	}

	public Segment segBuilder() {
		Segment _segment = new Segment(new Point(15, 4), new Point(24, 10));
		return _segment;
	}

	@Test 
	public void hasSubSegmentTest() {
		Segment segment = segBuilder(); 
	
		// pont create the segment 
		Point pointA = new Point(15,4);
		Point pointB = new Point(24,10);
		
		// Initilize point on the Segment
		Point pointC = new Point(21, 8);
		Point pointD = new Point(24, 10);
		Point pointE = new Point(18, 6);
		
		// Initilize point not on the Segment
		Point pointF = new Point(3, -4);
		Point pointG = new Point(6, -2); 
		Point pointH = new Point(9, 0);
		
		// Initlize subsegment 
		Segment segment1 = new Segment(pointC, pointB); 
		Segment segment2 = new Segment(pointD, pointE);
		Segment segment3 = new Segment(pointA, pointD);
		
		//Initilaize unsubsegment
		Segment segment4 = new Segment(pointF, pointG); 
		Segment segment5 = new Segment(pointA, pointF); 
		Segment segment6 = new Segment(pointB, pointF);
		Segment segment7 = new Segment(pointH, pointD);

		// testing True Case
		assertTrue(segment.HasSubSegment(segment1));
		assertTrue(segment.HasSubSegment(segment2));
		assertTrue(segment.HasSubSegment(segment3));
		
		//testing False case
		assertFalse(segment.HasSubSegment(segment4));
		assertFalse(segment.HasSubSegment(segment5));
		assertFalse(segment.HasSubSegment(segment6));
		assertFalse(segment.HasSubSegment(segment7));
		

	}

	@Test
	public void coincideWithoutOverlapTest() {
		Segment segOne = segBuilder();
		Segment segTwo = new Segment(new Point(21, 8), new Point(30, 14));
		Segment segThree = new Segment(new Point(24, 10), new Point(30, 14));

		assertTrue(segOne.coincideWithoutOverlap(segThree));
		assertFalse(segOne.coincideWithoutOverlap(segTwo));
	}

	@Test
	public void collectOrderedPointsOnSegmentTest() {

		Set<Point> givenPoints = pointBuilder();
		Segment testSeg = segBuilder();

		Set<Point> testSS = new HashSet<Point>();
		testSS.add(new Point(15, 4));
		testSS.add(new Point(18, 6));
		testSS.add(new Point(21, 8));
		testSS.add(new Point(24, 10));

		assertEquals(testSeg.collectOrderedPointsOnSegment(givenPoints), testSS);
	}

}
