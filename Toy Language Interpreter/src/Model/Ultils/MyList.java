package Model.Ultils;

import java.util.ArrayList;
import java.util.List;

import Model.PrgState;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Statements.*;

public class MyList<T> implements MyIList<T> {
	List<T> list = new ArrayList<T>();
	
	public void add(T v){//exceptie la add:bool
		this.list.add(v);
	}
	
	public String toString(){
		//return "" + this.list;
		String str = "";
		for(T e:list){
			str = str + e.toString()+" ";
		}
		return str;
	}
	
	public boolean isEmpty(){
		return this.list.isEmpty();
	}
	
	public int sizeList() {
		return this.list.size();
	}
	
	public T getElemList(int index) {
		return this.list.get(index);
	}
}
