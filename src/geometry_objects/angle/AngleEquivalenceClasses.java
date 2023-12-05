package geometry_objects.angle;

import java.util.Comparator;
import java.util.List;

import geometry_objects.angle.comparators.AngleStructureComparator;
import utilities.EquivalenceClasses;
import utilities.LinkedEquivalenceClass;
import geometry_objects.angle.AngleLinkedEquivalenceClass;
import geometry_objects.angle.comparators.*;
/**
 * Given the figure below:
 * 
 *    A-------B----C-----------D
 *     \
 *      \
 *       \
 *        E
 *         \
 *          \
 *           F
 * 
 * Equivalence classes structure we want:
 * 
 *   canonical = BAE
 *   rest = BAF, CAE, DAE, CAF, DAF
 */
public class AngleEquivalenceClasses extends EquivalenceClasses<Angle>
{
		
	public AngleEquivalenceClasses(AngleStructureComparator comparator) {
		super(comparator);
	}
	
	@Override 
	public boolean add(Angle element) {	 
		// get the index of the class for the given element, 
		// immediately add it if the equivalence class already exists 
		int index = indexOfClass(element);
		if (index != -1) return _classes.get(index).add(element);
		
		// otherwise, add a new equivalence class and add the element 
		_classes.add(new AngleLinkedEquivalenceClass(new AngleStructureComparator()));
		return _classes.get(_classes.size() -1).add(element);
	}
}