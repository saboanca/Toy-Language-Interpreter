package Model.Ultils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Model.PrgState;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Statements.*;

public class MyDictionary<K, V> implements MyIDictionary<K, V>{
	//metodele alea
	Map<K, V> dictionary = new HashMap<K, V>();
	
	public V lookup(K id){
		return this.dictionary.get(id);
	}
	
	public Set<K> listKeys(){
		return this.dictionary.keySet();
	}
	
	public boolean isDefined(K id){
		return this.dictionary.containsKey(id);
	}
	
	public boolean isEmpty() {
		return this.dictionary.isEmpty();
	}
	
	public void update(K id, V val){
		this.dictionary.replace(id, val);
	}
	
	public void add(K id, V val){
		this.dictionary.put(id, val);
	}
	
	public String toString(){
		String str = "\n";
		for(K key:dictionary.keySet()){
			str = str + key.toString();
			str = str + "->";
			V value = lookup(key);
			str = str + value.toString()+"\n";
		}
		return str;
		//return ""+dictionary;
	}

	public Map<Integer, Integer> getContent() {
		return (Map<Integer, Integer>) this.dictionary;
	}
}
