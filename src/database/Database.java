package database;

public interface Database<KeyType, ValueType> {
	public void put(KeyType key, ValueType val);
	public ValueType get(KeyType key);
	public int objectAt(KeyType key); 
    public boolean member(KeyType element);
    public int size();
}