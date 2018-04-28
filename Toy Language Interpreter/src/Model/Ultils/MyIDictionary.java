package Model.Ultils;

import java.util.Map;
import java.util.Set;

import Model.PrgState;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Statements.*;

public interface MyIDictionary<K, V> {

	public boolean isDefined(K id);
	//add, find, remove?, is defined, ...

	public V lookup(K id);

	public void update(K id, V val);

	public void add(K id, V val);
	public String toString();
	
	public Set<K> listKeys();
	public boolean isEmpty();

	public Map<Integer, Integer> getContent();
}
