package Model.Ultils;

import Model.PrgState;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Statements.*;

public interface MyIList<T> {

	public void add(T v);
	public String toString();
	public boolean isEmpty();
	public int sizeList();
	public T getElemList(int index);
	//metode
	//add, remove, update
}
