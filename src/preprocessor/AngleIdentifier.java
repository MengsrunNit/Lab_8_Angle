package preprocessor;
import java.util.Map;
import exceptions.FactException;
import geometry_objects.points.*;
import geometry_objects.*;
import geometry_objects.angle.Angle;
import geometry_objects.angle.AngleEquivalenceClasses;
import geometry_objects.angle.comparators.AngleStructureComparator;
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
	public AngleEquivalenceClasses getAngles() throws FactException
	{
		if (_angles != null) return _angles;
		_angles = new AngleEquivalenceClasses(new AngleStructureComparator());
		computeAngles();
		return _angles;
	}
	private void computeAngles() throws FactException
	{
		//Get your two segments
		for(Segment s1 : _segments.values()) {
			for (Segment s2 : _segments.values()) {
				//Could this combination be a valid angle? Must share a point, and not be overlapping.
				Point v = s1.sharedVertex(s2);
				if (v != null && !Segment.overlaysAsRay(s1, s2) && !(_angles.contains(new Angle(s1, s2)))) {
					_angles.add(new Angle(s1, s2));
				}
			}
		}		
	}
}