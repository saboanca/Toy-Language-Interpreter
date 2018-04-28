package Model.Statements;

import Model.PrgState;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Ultils.*;

public class AssignStmt implements IStmt{
	String id;
	Exp exp;
	
	public AssignStmt(String id, Exp exp){
		this.id = id;
		this.exp = exp;
	}
	
	@Override
	public String toString(){
		 return id + "=" + exp.toString(); 
	}
	
	public PrgState execute(PrgState state) throws MyException{
		MyIStack<IStmt> stk = state.getStk();
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		MyIHeap<Integer, Integer> heap = state.getHeap();
		int val = exp.eval(symTbl, heap);//pana in heap
		//System.out.println("val = "+Integer.toString(val));
		if (symTbl.isDefined(id)){
			//System.out.println("defined");
			symTbl.update(id, val);
		}
		else{
			//System.out.println("not defined");
			symTbl.add(id, val);
		}
		return null;
	}
}
