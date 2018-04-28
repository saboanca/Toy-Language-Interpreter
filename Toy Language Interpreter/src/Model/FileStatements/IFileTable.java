package Model.FileStatements;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IFileTable<Integer, Pair> {
	public boolean isDefined(Integer id);
	
	public Pair lookup(Integer id);
	
	public void update(Integer id, Pair val);
	public void add(Integer id, Pair val);
	public void delete(Integer id);
	public String toString();
	public boolean isEmpty();
	public Set<Integer> fileTableKeySet();

	public Collection<Pair> values();
	
	
}
