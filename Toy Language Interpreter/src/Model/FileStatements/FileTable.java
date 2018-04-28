package Model.FileStatements;
import java.util.*;

import Model.FileStatements.*;

public class FileTable<Integer, Pair> implements IFileTable<Integer, Pair>{
	Map<Integer, Pair> fileTable = new HashMap<Integer, Pair>();
	
	
	public Pair lookup(Integer id){
		return this.fileTable.get(id);
	}
	
	public boolean isEmpty() {
		return this.fileTable.isEmpty();
	}
	
	public Set<Integer> fileTableKeySet(){
		return this.fileTable.keySet();
	}
	
	public boolean isDefined(Integer id){
		return this.fileTable.containsKey(id);
	}
	
	public void update(Integer id, Pair val){
		this.fileTable.replace(id, val);
	}
	
	public void delete(Integer id){
		this.fileTable.remove(id);
	}
	
	public void add(Integer id, Pair val){
		this.fileTable.put(id, val);
	}
	
	@Override
	public String toString(){
		String str = "\n";
		for(Integer key: fileTable.keySet()){
			str = str + key.toString();
			str = str + "->";
			Pair value = lookup(key);
			str = str + value.toString()+"\n";
		}
		return str;
	}
	@Override
	public Collection<Pair> values() {
		return this.fileTable.values();
	}
	
}
