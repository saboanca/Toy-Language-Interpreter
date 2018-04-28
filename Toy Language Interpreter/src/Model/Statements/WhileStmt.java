package Model.Statements;

import Model.Expressions.*;
import Model.Ultils.MyIDictionary;
import Model.Ultils.MyIHeap;
import Model.Ultils.MyIStack;
import Model.PrgState;

public class WhileStmt implements IStmt{
	Exp exp;
	IStmt statement;
	
	public WhileStmt(Exp exp, IStmt statement){
		this.exp = exp;
		this.statement = statement;
	}
	
	@Override
	public String toString(){
		return "(while (" + this.exp.toString() + ")" + this.statement.toString() + ")"; 
	}
	
	public PrgState execute(PrgState state){
		MyIStack<IStmt> stk = state.getStk();
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		MyIHeap<Integer, Integer> heap = state.getHeap();
		WhileStmt whileStatement = new WhileStmt(exp, statement);
		if (this.exp.eval(symTbl, heap) == 0){
			return null;
		}
		else{
			stk.push(whileStatement);
			stk.push(statement);
			return null;
		}
	}
}
