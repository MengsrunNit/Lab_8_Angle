package preprocessor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.Triangle;

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
				for (Segment s3 : _segments.values()) {
					
					//Make a list of the segments, so it can be used in the triangle constructor
					List<Segment> t = new ArrayList<Segment>();
					t.add(s1);
					t.add(s2);
					t.add(s3);
					
					//Checks if the triangle is already in the set, triangle creation checks validity
					
						if (!_triangles.contains(new Triangle(t))) _triangles.add(new Triangle(t));
					
					//System.out.println(_triangles.size());
				}
			}
		}
	}
}