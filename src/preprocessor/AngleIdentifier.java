package preprocessor;

import java.util.List;
import java.util.Map;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.angle.AngleEquivalenceClasses;
import geometry_objects.angle.comparators.*;

public class AngleIdentifier
{
	protected AngleEquivalenceClasses _angles;
	protected Map<Segment, Segment> _segments; // The set of ALL segments for this figure

	public AngleIdentifier(Map<Segment, Segment> segments)
	{
		_segments = segments;
	}

	/*
	 * Compute the figure triangles on the fly when requested; memoize results for subsequent calls.
	 */
	public AngleEquivalenceClasses getAngles()
	{
		if (_angles != null) return _angles;
		_angles = new AngleEquivalenceClasses(new AngleStructureComparator());
		computeAngles();
		return _angles;
	}
	private void computeAngles()
	{
		//Get your two segments
		for (Segment s1 : _segments.values()) {
			for (Segment s2 : _segments.values()) {
				
				//Could this combination be a valid angle? Must share a point, and not be subsegments of each other
				if( s2.has(s1.getPoint1()) || s2.has(s1.getPoint2()) && !(s2.HasSubSegment(s1) || s1.HasSubSegment(s2))) {
				
					//Could this combination be in the AEC already? Check it and its countarpart.
					try {
						if (!(_angles.contains(new Angle(s1, s2)) || _angles.contains(new Angle(s2, s1)))) {
							_angles.add(new Angle(s1, s2));
						}
					} catch (FactException e) {
						System.out.println("computeAngles issue.");
					}
				}
			}		
		}
	}
}