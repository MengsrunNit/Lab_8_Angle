package preprocessor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.Triangle;
import geometry_objects.points.Point;
public class TriangleIdentifier
{
	protected Set<Triangle>         _triangles;
	protected Map<Segment, Segment> _segments; // The set of ALL segments for this figure.
	public TriangleIdentifier(Map<Segment, Segment> segments)
	{
		_segments = segments;
	}
	/*
	 * Compute the figure triangles on the fly when requested;
	 * memoize results for subsequent calls.
	 */
	public Set<Triangle> getTriangles() throws FactException
	{
		if (_triangles != null) return _triangles;
		_triangles = new HashSet<Triangle>();
		computeTriangles();
		return _triangles;
	}
	private void computeTriangles() throws FactException
	{
		//Get your three segments for the triangles
		for (Segment s1 : _segments.values()) {
			for (Segment s2 : _segments.values()) {
				
				//Trying to weed out cases where a triangle cannot be formed
				Point p = s2.sharedVertex(s1);
				if (p != null && !(s1.HasSubSegment(s2) || s2.HasSubSegment(s1))) {
					
					for (Segment s3 : _segments.values()) {	
						
						//Makes sure the last segment connects the other two at their other points
						//Cannot be collinear
						
						if(s3.has(s1.other(p)) && s3.has(s2.other(p)) && !(s3.isCollinearWith(s1))) {
							
							//Makes list of segments to be put into the triangle constructor
							List<Segment> t = new ArrayList<Segment>();
							t.add(s1);
							t.add(s2);
							t.add(s3);
							_triangles.add(new Triangle(t));
						}
					}
				}
			}
		}
	}
}