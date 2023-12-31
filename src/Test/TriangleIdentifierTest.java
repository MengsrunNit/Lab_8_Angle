package Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.Triangle;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import input.components.FigureNode;
import preprocessor.Preprocessor;
import preprocessor.TriangleIdentifier;
import input.InputFacade;
class TriangleIdentifierTest
{
	protected PointDatabase _points;
	protected Preprocessor _pp;
	protected Map<Segment, Segment> _segments;
	
	protected void init(String filename)
	{
		FigureNode fig = InputFacade.extractFigure(filename);
		Map.Entry<PointDatabase, Set<Segment>> pair = InputFacade.toGeometryRepresentation(fig);
		_points = pair.getKey();
		_pp = new Preprocessor(_points, pair.getValue());
		_pp.analyze();
		_segments = _pp.getAllSegments();
	}
	
	//      A
	//     / \
	//    B___C
	//   / \ / \
	//  /   X   \  X is not a specified point (it is implied)
	// D_________E
	//
	// This figure contains 12 triangles
	//
	@Test
	void test_crossing_symmetric_triangle() throws FactException
	{
		init("crossing_symmetric_triangle.json");
		TriangleIdentifier triIdentifier = new TriangleIdentifier(_segments);
		Set<Triangle> computedTriangles = triIdentifier.getTriangles();
		//System.out.println(computedTriangles);
		assertEquals(12, computedTriangles.size());
		//
		// ALL original segments: 8 in this figure.
		//
		Segment ab = new Segment(_points.getPoint("A"), _points.getPoint("B"));
		Segment ac = new Segment(_points.getPoint("A"), _points.getPoint("C"));
		Segment bc = new Segment(_points.getPoint("B"), _points.getPoint("C"));
		Segment bd = new Segment(_points.getPoint("B"), _points.getPoint("D"));
		Segment ce = new Segment(_points.getPoint("C"), _points.getPoint("E"));
		Segment de = new Segment(_points.getPoint("D"), _points.getPoint("E"));
		Segment be = new Segment(_points.getPoint("B"), _points.getPoint("E"));
		Segment cd = new Segment(_points.getPoint("C"), _points.getPoint("D"));
		//
		// Implied minimal segments: 4 in this figure.
		//
		Point a_star = _points.getPoint(3,3);
		Segment a_star_b = new Segment(a_star, _points.getPoint("B"));
		Segment a_star_c = new Segment(a_star, _points.getPoint("C"));
		Segment a_star_d = new Segment(a_star, _points.getPoint("D"));
		Segment a_star_e = new Segment(a_star, _points.getPoint("E"));
		//
		// Non-minimal, computed segments: 2 in this figure.
		//
		Segment ad = new Segment(_points.getPoint("A"), _points.getPoint("D"));
		Segment ae = new Segment(_points.getPoint("A"), _points.getPoint("E"));
		//
		// Triangles we expect to find
		//
		List<Triangle> expectedTriangles = new ArrayList<Triangle>();
		try {
			expectedTriangles.add(new Triangle(Arrays.asList(ab, bc, ac)));
			expectedTriangles.add(new Triangle(Arrays.asList(bd, a_star_d, a_star_b)));
			expectedTriangles.add(new Triangle(Arrays.asList(bc, a_star_b, a_star_c)));
			expectedTriangles.add(new Triangle(Arrays.asList(ce, a_star_c, a_star_e)));
			expectedTriangles.add(new Triangle(Arrays.asList(de, a_star_d, a_star_e)));
			expectedTriangles.add(new Triangle(Arrays.asList(bd, cd, bc)));
			expectedTriangles.add(new Triangle(Arrays.asList(ce, be, bc)));
			expectedTriangles.add(new Triangle(Arrays.asList(bd, be, de)));
			expectedTriangles.add(new Triangle(Arrays.asList(ce, cd, de)));
			expectedTriangles.add(new Triangle(Arrays.asList(ab, be, ae)));
			expectedTriangles.add(new Triangle(Arrays.asList(ac, cd, ad)));
			expectedTriangles.add(new Triangle(Arrays.asList(ad, de, ae)));
		}
		catch (FactException te) { System.err.println("Invalid triangles in triangle test."); }
		assertEquals(expectedTriangles.size(), computedTriangles.size());
		
		for (Triangle computedTriangle : computedTriangles)
		{
			assertTrue(expectedTriangles.contains(computedTriangle));
		}
	}
	@Test
	void test_crossing_square() throws FactException
	{
		
		//
		//		  B-------C
		//	      |	 \ /  |
		//	 	  |	  X   |
		//	      |  / \  |
		//		  A-------D
		//
	    // *Implied point in the center
		
		init("src/crossing_square.json");
		TriangleIdentifier triIdentifier = new TriangleIdentifier(_segments);
		Set<Triangle> computedTriangles = triIdentifier.getTriangles();
		assertEquals(8, computedTriangles.size());
		
		Segment ab = new Segment(_points.getPoint("A"), _points.getPoint("B"));
		Segment ac = new Segment(_points.getPoint("A"), _points.getPoint("C"));
		Segment ad = new Segment(_points.getPoint("A"), _points.getPoint("D"));
		Segment bc = new Segment(_points.getPoint("B"), _points.getPoint("C"));
		Segment bd = new Segment(_points.getPoint("B"), _points.getPoint("D"));
		Segment cd = new Segment(_points.getPoint("C"), _points.getPoint("D"));
		//
		// Implied minimal segments: 4 in this figure.
		//
		Point a_star = _points.getPoint(4, 4);
		Segment a_star_a = new Segment(a_star, _points.getPoint("A"));
		Segment a_star_b = new Segment(a_star, _points.getPoint("B"));
		Segment a_star_c = new Segment(a_star, _points.getPoint("C"));
		Segment a_star_d = new Segment(a_star, _points.getPoint("D"));
		//
		// Triangles we expect to find
		//
		List<Triangle> expectedTriangles = new ArrayList<Triangle>();
		try {
			expectedTriangles.add(new Triangle(Arrays.asList(ab, a_star_b, a_star_a)));
			expectedTriangles.add(new Triangle(Arrays.asList(bc, a_star_b, a_star_c)));
			expectedTriangles.add(new Triangle(Arrays.asList(cd, a_star_c, a_star_d)));
			expectedTriangles.add(new Triangle(Arrays.asList(ad, a_star_a, a_star_d)));
			
			expectedTriangles.add(new Triangle(Arrays.asList(bc, ac, ab)));
			expectedTriangles.add(new Triangle(Arrays.asList(bd, ad, ab)));
			expectedTriangles.add(new Triangle(Arrays.asList(ac, cd, ad)));
			expectedTriangles.add(new Triangle(Arrays.asList(bd, bc, cd)));
			
			
		}
		catch (FactException te) { System.err.println("Invalid triangles in triangle test."); }
		assertEquals(expectedTriangles.size(), computedTriangles.size());
		
		for (Triangle computedTriangle : computedTriangles)
		{
			assertTrue(expectedTriangles.contains(computedTriangle));
		}
	}
	@Test
	void test_rectangular_figure() throws FactException
	{
		
		//
		//	    F----E----D
		//	    |   /|\   |
		//	    |  / | \  |
		//	    | /  |  \ |
		//	    |/   |   \|
		//	    A----B----C
		//
		
		init("src/rectangular_figure.json");
		TriangleIdentifier triIdentifier = new TriangleIdentifier(_segments);
		Set<Triangle> computedTriangles = triIdentifier.getTriangles();
		assertEquals(5, computedTriangles.size());
		
		Segment ab = new Segment(_points.getPoint("A"), _points.getPoint("B"));
		Segment ac = new Segment(_points.getPoint("A"), _points.getPoint("C"));
		Segment ae = new Segment(_points.getPoint("A"), _points.getPoint("E"));
		Segment af = new Segment(_points.getPoint("A"), _points.getPoint("F"));
		Segment bc = new Segment(_points.getPoint("B"), _points.getPoint("C"));
		Segment be = new Segment(_points.getPoint("B"), _points.getPoint("E"));
		Segment cd = new Segment(_points.getPoint("C"), _points.getPoint("D"));
		Segment ce = new Segment(_points.getPoint("C"), _points.getPoint("E"));
		Segment de = new Segment(_points.getPoint("D"), _points.getPoint("E"));
		Segment ef = new Segment(_points.getPoint("E"), _points.getPoint("F"));
		//
		// Triangles we expect to find
		//
		List<Triangle> expectedTriangles = new ArrayList<Triangle>();
		try {
			expectedTriangles.add(new Triangle(Arrays.asList(ab, be, ae)));
			expectedTriangles.add(new Triangle(Arrays.asList(af, ef, ae)));
			expectedTriangles.add(new Triangle(Arrays.asList(bc, ce, be)));
			expectedTriangles.add(new Triangle(Arrays.asList(cd, de, ce)));
			expectedTriangles.add(new Triangle(Arrays.asList(ac, ce, ae)));
		}
		catch (FactException te) { System.err.println("Invalid triangles in triangle test."); }
		assertEquals(expectedTriangles.size(), computedTriangles.size());
		
		for (Triangle computedTriangle : computedTriangles)
		{
			assertTrue(expectedTriangles.contains(computedTriangle));
		}
	}
	
}

