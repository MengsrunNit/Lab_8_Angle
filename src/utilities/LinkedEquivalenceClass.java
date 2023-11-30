package utilities;
import java.util.Comparator;
public class LinkedEquivalenceClass<T> {
	protected T _canonical;
	protected Comparator<T> _comparator;
	protected LinkedList<T> _rest;
	
	public LinkedEquivalenceClass(Comparator<T> comparator) {
		_canonical = null;
		_comparator =comparator;
		_rest = new LinkedList<T>();
	}
	public T canonical(){
		return _canonical;
	}
	public boolean isEmpty(){
		return (_rest.isEmpty() && _canonical==null);
	}
	public void clear() {
		_canonical=null;
		_rest.clear();
	}
	public void clearNonCanonical() {
		_rest.clear();
	}
	//different
	public int size() {
		return _rest.size();
	}
	public boolean add(T element) {
		if (isEmpty() || _canonical==null) {
			this._canonical=element;
			return true;
		}
		else if (belongs(element)) {
			_rest.addToFront(element);
			return true;
		}
		return false;
	}
	public boolean contains(T target) {
		return _rest.contains(target);
	}
	public boolean belongs(T target) {
		if (_comparator.compare(_canonical, target)==0) return true;
		return false;
		
	}
	public boolean remove(T target) {
		return _rest.remove(target);
	}
	public boolean removeCanonical(){
		_canonical=null;
		return true;
	}
	public boolean demoteAndSetCanonical(T element) {
		if(belongs(element)) {
			_canonical=element;
			return true;
		}
		_rest.clear();
		_canonical=element;
		return false;
	}
	
	public String toString() {
		return "" + _canonical.toString() + "|" + _rest.toString();
	}
}