package preprocessor.delegates;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import geometry_objects.Segment;
import geometry_objects.delegates.intersections.SegmentIntersectionDelegate;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;

public class ImplicitPointPreprocessor
{
	/**
	 * It is possible that some of the defined segments intersect
	 * and points that are not named; we need to capture those
	 * points and name them.
	 * 
	 * Algorithm:
	 *    From the Segments given:
	 *    	Calculate the intersection of all permutations of two segments
	 *    	Check that the intersection is on both segments, using the following formula:
	 *    		for each segment AB, to check the potential point N, calculate
	 *     		(dist(A, N) + dist(N, B)) == dist(A, B)
	 *    	Then add it to the set of implicitPoints
	 */
	
	public static Set<Point> compute(PointDatabase givenPoints, List<Segment> givenSegments)
	{
		Set<Point> implicitPoints = new LinkedHashSet<Point>();
		
		//	for each segment in the database and the segment which follows
		for (int index_1 = 0; index_1 < givenSegments.size() -1; index_1++) {
			for (int index_2 = index_1 + 1; index_2 < givenSegments.size(); index_2++) {
				
				//	find the intersection of those to segments
				Point implicit = SegmentIntersectionDelegate.findIntersection(givenSegments.get(index_1), givenSegments.get(index_2));
				if (implicit != null && givenPoints.getPoint(implicit) == null) {
					
					//	if the intersection is not in the point database, and it is != null, add it to implicitPoints
					Point pt = givenPoints.put(Point.ANONYMOUS, implicit.getX(), implicit.getY());	
					implicitPoints.add(pt);
				} 
			}
		}
		return implicitPoints;
	}

}