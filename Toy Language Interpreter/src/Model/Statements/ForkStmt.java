package Model.Statements;

import java.io.BufferedReader;

import Model.PrgState;
import Model.FileStatements.IFileTable;
import Model.FileStatements.Pair;
import Model.Ultils.*;

public class ForkStmt implements IStmt{
	public IStmt statement;
	
	public ForkStmt(IStmt statement){
		this.statement = statement;
	}
	
	@Override
	public String toString(){
		return "(fork(" + this.statement.toString() + "))";
	}
	
	public PrgState execute(PrgState state){
		MyIList<Integer> newOut = state.getOut();
		MyIHeap<Integer, Integer> newHeap = state.getHeap();
		IFileTable<Integer, Pair<String, BufferedReader>> newFileTable = state.getIFileTable();
		int newId = state.getId() * 10;
		MyIStack<IStmt> newStk = new MyStack<IStmt>();
		MyIDictionary<String, Integer> newSymTbl = new MyDictionary<String, Integer>();
		//populam symtbl
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		
		//for1
		for (String key: symTbl.listKeys()){
			Integer value = symTbl.lookup(key);
			newSymTbl.add(key, value);
		}
		
		PrgState newState = new PrgState(newId, newStk, newSymTbl, newOut, newFileTable, newHeap, statement);
		return newState;
	}
}
