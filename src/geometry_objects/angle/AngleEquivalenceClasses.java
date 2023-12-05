package geometry_objects.angle;

import java.util.Comparator;
import java.util.List;

import geometry_objects.angle.comparators.AngleStructureComparator;
import utilities.EquivalenceClasses;
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
	public AngleStructureComparator _comparator;
	public List<AngleLinkedEquivalenceClass> _classes;
	public AngleEquivalenceClasses(AngleStructureComparator comparator) {
		super(comparator);
	}
	public boolean add(Angle element) {
		if(element == null) {
			return false;
		}
		//Only adds to a LEC where it belongs, checking indexOfClass to find index
		if(!(indexOfClass(element) == -1)){
			_classes.get(indexOfClass(element)).add(element);
			return true;
		}
		else {
			AngleLinkedEquivalenceClass lec = new AngleLinkedEquivalenceClass(_comparator);
			lec.add(element);
			return true;
		}
	}
	public boolean contains(Angle element) {
		for(AngleLinkedEquivalenceClass lec: _classes) {
			if(lec.contains(element)) return true;
		}
		return false;
	}
	public int size() {
		int totalElement = 0;
		for (AngleLinkedEquivalenceClass lec: _classes) {
			totalElement += lec.size();
			;		}
		return totalElement;
	}
	public int numClasses() {
		return _classes.size();
	}
	protected int indexOfClass(Angle element) {
		for (int i =0; i<_classes.size(); i++) {
			if(_classes.get(i).belongs(element)){
				return i;
			}
		}
		return -1;
	}
	public String toString() {
		String _string= "";
		for(AngleLinkedEquivalenceClass lec: _classes) {
			_string += lec.toString() + ", " ;
		}
		return _string;
	}
}