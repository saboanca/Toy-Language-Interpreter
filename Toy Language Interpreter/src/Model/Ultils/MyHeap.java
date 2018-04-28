package Model.Ultils;

import java.util.*;

import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Statements.*;

public class MyHeap<K, V> implements MyIHeap<K, V>{
	Map<K, V> heap = new HashMap<K, V>();
	public static int newFreeLocation = 0;
	
	public boolean isEmpty() {
		return this.heap.isEmpty();
	}
	
	public Set<K> heapKeySet() {
		return this.heap.keySet();
	}
	
	public int sizeHeap() {
		return this.heap.size();
	} 
	
	public V lookupHeap(K id){
		return heap.get(id);
	}
	
	public int getNewFreeLocation(){
		this.newFreeLocation++;
		return this.newFreeLocation;
	}
	
	public boolean isDefinedHeap(K id){
		return this.heap.containsKey(id);
	}
	
	public void updateHeap(K id, V val){
		this.heap.replace(id, val);
	}
	
	public void addHeap(K id, V val){
		this.heap.put(id, val);
	}
	
	public String toString(){
		String str = "\n";
		for(K key:this.heap.keySet()){
			str = str + key.toString();
			str = str + "->";
			V value = lookupHeap(key);
			str = str + value.toString()+"\n";
		}
		return str;
	}

	@Override
	public Map<Integer, Integer> getContent() {
		return (Map<Integer, Integer>) this.heap;
	}

	@Override
	public void setContent(Map<Integer, Integer> conservativeGarbageCollector) {
		this.heap = (Map<K, V>) conservativeGarbageCollector;
	}
}