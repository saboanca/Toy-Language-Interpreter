package Model.Statements;

import Model.PrgState;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Ultils.*;


public class IfStmt implements IStmt{
	Exp exp;
	IStmt thenS;
	IStmt elseS;
	
	public IfStmt(Exp exp, IStmt thenStmt, IStmt elseStmt){
		this.exp = exp;
		this.thenS = thenStmt;
		this.elseS = elseStmt;
	}
	
	@Override
	public String toString(){
		 return "IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+")";
	}
	
	public PrgState execute(PrgState state) throws MyException{
		//faceva
		MyIStack<IStmt> stk = state.getStk();
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		MyIHeap<Integer, Integer> heap = state.getHeap();
		if (exp.eval(symTbl, heap) != 0){
			stk.push(thenS);
		}
		else{
			stk.push(elseS);
		}
		return null;
	}
}
