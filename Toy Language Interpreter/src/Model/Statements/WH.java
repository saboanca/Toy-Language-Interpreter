package Model.Statements;
import java.io.IOException;

import Model.PrgState;
import Model.Exceptions.MyException;
import Model.Expressions.*;
import Model.Ultils.*;

public class WH implements IStmt{
	public String var;
	public Exp exp;
	
	public WH(String var, Exp exp){
		this.var = var;
		this.exp = exp;
	}
	
	@Override
	public String toString() {
		return "wH(" + this.var + ", " + this.exp.toString() + ")";
	}

	public PrgState execute(PrgState state) throws MyException, IOException {
		MyIStack<IStmt> stk = state.getStk();
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		MyIHeap<Integer, Integer> heap = state.getHeap();
		if (symTbl.isDefined(this.var)){
			int addr = symTbl.lookup(this.var);
			int val = this.exp.eval(symTbl, heap);
			if (heap.isDefinedHeap(addr)){
				heap.updateHeap(addr, val);
			}
			else{
				throw new MyException("Invalid Heap Address!");
			}
		}
		else{
			throw new MyException("Variable not in SymTbl!");
		}
		return null;
	}
}