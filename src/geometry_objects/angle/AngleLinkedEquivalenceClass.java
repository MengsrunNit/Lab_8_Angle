package geometry_objects.angle;

import java.util.Comparator;
import utilities.LinkedList;

import geometry_objects.angle.comparators.AngleStructureComparator;
import utilities.LinkedEquivalenceClass;


/**
 * This implementation requires greater knowledge of the implementing Comparator.
 * 
 * According to our specifications for the AngleStructureComparator, we have
 * the following cases:
 *
 *    Consider Angles A and B
 *    * Integer.MAX_VALUE -- indicates that A and B are completely incomparable
                             STRUCTURALLY (have different measure, don't share sides, etc. )
 *    * 0 -- The result is indeterminate:
 *           A and B are structurally the same, but it is not clear one is structurally
 *           smaller (or larger) than another
 *    * 1 -- A > B structurally
 *    * -1 -- A < B structurally
 *    
 *    We want the 'smallest' angle structurally to be the canonical element of an
 *    equivalence class.
 * 
 * @author XXX
 */
public class AngleLinkedEquivalenceClass extends LinkedEquivalenceClass<Angle>
{
    
	
	public AngleLinkedEquivalenceClass(AngleStructureComparator comparator) {
		super(comparator); 
	}
	
	@Override 
	public boolean add(Angle element) {
		if (isEmpty() || _canonical == null) {
			_canonical = element;
			return true;
		}
		else if (belongs(element)) {
			if(_comparator.compare(_canonical, element) == 1) {
				_rest.addToFront(_canonical);
				_canonical = element;
			}
			else if(_comparator.compare(_canonical, element) == -1 || _comparator.compare(_canonical, element) ==0) {
				_rest.addToFront(element);
			}
			
			return true;
		}
		return false;
	}
	
	@Override 
	public boolean demoteAndSetCanonical(Angle element) {
		if(belongs(element)) {
			return add(element);
			
		}
		_rest.clear();
		_canonical=element;
		return false;
	}
	
	public boolean belongs(Angle target) {
		if(target == null) {
			return false; 
		}
		if (_comparator.compare(_canonical, target) == 1 || _comparator.compare(_canonical, target)==-1 || _comparator.compare(_canonical, target)==0) return true;
		return false;
		
	}
	
	
}