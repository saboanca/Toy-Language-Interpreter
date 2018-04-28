package Model.Statements;

import Model.PrgState;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Ultils.*;


public class PrintStmt implements IStmt{
	Exp exp;
	
	public PrintStmt(Exp exp){
		this.exp = exp;
	}
	
	@Override
	public String toString(){
		return "print(" + exp.toString() + ")";
	}
	
	public PrgState execute(PrgState state) throws MyException{
		//faceva
		MyIStack<IStmt> stk = state.getStk();
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		MyIHeap<Integer, Integer> heap = state.getHeap();
		int val = exp.eval(symTbl, heap);//poate mai trebe si un symTbl?PAI NORMAL CA TREBE
		
		MyIList<Integer> out = state.getOut();
		out.add(val);
		
		return null;//asta sigur ii ok
	}
}
