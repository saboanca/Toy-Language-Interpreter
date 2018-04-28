package Model.Statements;
import java.io.IOException;

import Model.PrgState;
import Model.Exceptions.MyException;
import Model.Expressions.*;
import Model.Ultils.*;

public class NewStmt implements IStmt{
	public String var_name;
	public Exp expression;
	
	public NewStmt(String var_name, Exp expression){
		this.var_name = var_name;
		this.expression = expression;
	}

	public PrgState execute(PrgState state) throws MyException, IOException {
		MyIStack<IStmt> stk = state.getStk();
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		MyIHeap<Integer, Integer> heap = state.getHeap();
		int val = expression.eval(symTbl, heap);
		int newfreelocation = heap.getNewFreeLocation();
		heap.addHeap(newfreelocation, val);
		if (symTbl.isDefined(var_name)){
			symTbl.update(var_name, newfreelocation);
		}
		else{
			symTbl.add(var_name, newfreelocation);
		}
		return null;
	}
	
	@Override
	public String toString(){
		return "new(" + this.var_name + ", " + this.expression.toString() + ")";
	}
}	
