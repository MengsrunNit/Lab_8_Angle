package utilities;

import java.util.Comparator;

import java.util.*;

public class EquivalenceClasses<T>{
	//Changes to public for testing purposes
	public Comparator<T> _comparator; 
	public List<LinkedEquivalenceClass<T>> _classes;
	
	public EquivalenceClasses(Comparator<T> comparator) {
		this._comparator = comparator;
		this._classes = new ArrayList<LinkedEquivalenceClass<T>>(); 
	}
	
	public boolean add(T element) {
		
		if(element == null) {
			return false; 
		}
		//Only adds to a LEC where it belongs, checking indexOfClass to find index
		if(!(indexOfClass(element) == -1)){
			_classes.get(indexOfClass(element)).add(element);
			return true; 
		}
		
		else {
			LinkedEquivalenceClass<T> lec = new LinkedEquivalenceClass<T>(_comparator);
			lec.add(element);
			return true; 
		} 
}
	public boolean contains(T element) {
		for(LinkedEquivalenceClass<T> lec: _classes) {
			if(lec.contains(element)) return true;
		}
		return false;
	}
	public int size() {
		int totalElement = 0;
		for (LinkedEquivalenceClass<T> lec: _classes) {
			totalElement += lec.size();
;		}
		return totalElement;
	}
	public int numClasses() {
		return _classes.size();
	}
	
	protected int indexOfClass(T element) {
		for (int i =0; i<_classes.size(); i++) {
			if(_classes.get(i).belongs(element)){
				return i; 
			}
		}
		
		return -1; 
	}
	public String toString() {
		String _string="";
		for(LinkedEquivalenceClass<T> lec: _classes) {
			_string += lec.toString() + ", " ; 
		}
		return _string;
	}
}