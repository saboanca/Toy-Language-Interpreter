package Model.Ultils;

import java.util.Map;
import java.util.Set;

import Model.PrgState;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Statements.*;

public interface MyIHeap<K, V> {
	public boolean isDefinedHeap(K id);
	public boolean isEmpty();
	public int sizeHeap();
	public Set<K> heapKeySet();
	public V lookupHeap(K id);
	public void updateHeap(K id, V val);
	public void addHeap(K id, V val);
	public int getNewFreeLocation();
	public String toString();
	public Map<Integer, Integer> getContent();
	public void setContent(Map<Integer, Integer> conservativeGarbageCollector);
}
