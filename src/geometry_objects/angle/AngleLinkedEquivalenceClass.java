package geometry_objects.angle;

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
    // TODO
	public AngleLinkedEquivalenceClass(AngleStructureComparator comparator) {
		super(comparator); 
	}
	
	@Override
	public Angle canonical(){
		return _canonical;
	}
	
	@Override
	public boolean isEmpty(){
		return (_rest.isEmpty() && _canonical==null);
	}
	
	@Override
	public void clear() {
		_canonical=null;
		_rest.clear();
	}
	
	@Override
	public void clearNonCanonical() {
		_rest.clear();
	}
	
	@Override
		public int size() {
			return _rest.size();
		}
	
	// TODO
	@Override 
	public boolean add(Angle element) {
	
	}
	@Override 
	public boolean remove(Angle target) {
		return _rest.remove(target);
	}
	
	@Override 
	public boolean removeCanonical(){
		_canonical=null;
		return true;
	}
	@Override 
	public boolean demoteAndSetCanonical(T element) {
		if(belongs(element)) {
			_canonical=element;
			return true;
		}
		_rest.clear();
		_canonical=element;
		return false;
	}
	@Override 
	public String toString() {
		return "" + _canonical.toString() + "|" + _rest.toString();
	}
	
	
	
}