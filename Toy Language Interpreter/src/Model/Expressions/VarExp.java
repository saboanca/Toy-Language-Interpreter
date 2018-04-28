package Model.Expressions;

import Model.Ultils.*;
import Model.Statements.*;
import Model.Exceptions.*;


public class VarExp extends Exp{
	String id;
	
	public VarExp(String id){
		this.id = id;
	}
	
	public int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer, Integer> heap) throws MyException{
		if (tbl.lookup(id) == null){
			throw new MyException("Variable not in SymTable!");
		}
		return tbl.lookup(id);
	}
	@Override
	public String toString() {
		return this.id;
	}
}