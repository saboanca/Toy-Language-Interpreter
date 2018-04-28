package Model;
import java.io.BufferedReader;
import java.io.IOException;

import Model.Exceptions.MyException;
import Model.Expressions.*;
import Model.FileStatements.*;
import Model.Ultils.*;
import Model.Statements.*;

public class PrgState {
	int id;
	MyIStack<IStmt> exeStack;
	MyIDictionary<String, Integer> symTable;
	MyIList<Integer> out;
	IFileTable<Integer, Pair<String, BufferedReader>> fileTable;
	MyIHeap<Integer, Integer> heap;
	IStmt originalProgram;
	
	public PrgState(int id, MyIStack<IStmt> stk, MyIDictionary<String, Integer> symtbl, MyIList<Integer> ot, IFileTable<Integer, Pair<String, BufferedReader>> fileTable, MyIHeap<Integer, Integer> heap, IStmt prg){
		this.id = id;
		this.exeStack = stk;
		this.symTable = symtbl;
		this.out = ot;
		this.fileTable = fileTable;
		this.heap = heap;
		this.originalProgram = prg;
		stk.push(prg);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MyIHeap<Integer, Integer> getHeap() {
		return heap;
	}

	public void setHeap(MyIHeap<Integer, Integer> heap) {
		this.heap = heap;
	}


	public IFileTable<Integer, Pair<String, BufferedReader>> getIFileTable() {
		return fileTable;
	}

	public void setIFileTable(IFileTable<Integer, Pair<String, BufferedReader>> fileTable) {
		this.fileTable = fileTable;
	}

	public MyIStack<IStmt> getStk() {
		return exeStack;
	}

	public void setStk(MyIStack<IStmt> exeStack) {
		this.exeStack = exeStack;
	}

	public MyIDictionary<String, Integer> getSymTable() {
		return symTable;
	}

	public void setSymTable(MyIDictionary<String, Integer> symTable) {
		this.symTable = symTable;
	}

	public MyIList<Integer> getOut() {
		return out;
	}

	public void setOut(MyIList<Integer> out) {
		this.out = out;
	}

	public IStmt getOriginalProgram() {
		return originalProgram;
	}

	public void setOriginalProgram(IStmt originalProgram) {
		this.originalProgram = originalProgram;
	}
	
	public boolean isNotCompleted(){
		if (this.exeStack.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public PrgState oneStep() throws MyException, IOException{
		if (exeStack.isEmpty()){
			throw new MyException("Empty stack!");
		}
		IStmt crtStmt = exeStack.pop();
		return crtStmt.execute(this);
	}

	@Override
	public String toString(){
		return "ID: " + id + "\n\nExecution stack: \n" + exeStack.toString() + "\n\nTable of symbols: "+ symTable.toString() + "\n\nHeap: " + heap.toString() + "\n\nOutput: " + out.toString() + "\n\nFileTable: " + fileTable.toString() + "\n\n";//print a string
	}
}
