package database;

import java.util.List; 
import java.util.Vector;
import java.util.ArrayList; 

import util.annotations.Tags;
@Tags({"Table"})
public class ADatabase<KeyType, ValueType> implements Database<KeyType, ValueType> {
	
	protected List<KeyType> names = new Vector<KeyType>();
	protected List<ValueType> objects = new ArrayList<ValueType>();
        
	public void put(KeyType key, ValueType val){
		
		// function when key and object are not null
		if (key!=null && val!=null){
			
	        // check for duplicate
			if (!member(key)){
				names.add(key);
				objects.add(val);
			}
			else objects.set(indexOf(key),val);
		}
	}		
	
	public ValueType get(KeyType key){
		
		// return null object
		if(indexOf(key)==names.size()){
			names.add(null);
			objects.add(null);
			return objects.get(names.size()-1);
		}
		
		// return associative object
		else return objects.get(indexOf(key));
	}
    
    int indexOf(KeyType element) {
        int index = 0;
        while ((index < names.size()) && !((String) element).equalsIgnoreCase(((String)names.get(index))) )
        	index++;
        return index;
    } 
    
    public int size() { return names.size();}
	public int objectAt(KeyType key) { return indexOf(key);}
    public boolean member(KeyType element) { return indexOf(element) < names.size();}
    
}
