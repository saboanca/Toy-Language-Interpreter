package Model.FileStatements;
import Model.Statements.*;
import Model.Expressions.*;
import Model.Ultils.*;
import Model.Exceptions.*;

import java.io.*;

import Model.PrgState;


public class CloseRFile implements IStmt{
	private Exp exp_file_id;
	
	public CloseRFile(Exp exp_file_id){
		this.exp_file_id = exp_file_id;
	}
	
	public PrgState execute(PrgState state) throws IOException, MyException{
		MyIDictionary<String, Integer> symTable = state.getSymTable();
		MyIHeap<Integer, Integer> heap = state.getHeap();
		int value = exp_file_id.eval(symTable, heap);
		if (state.getIFileTable().isDefined(value)){
			Pair pair = state.getIFileTable().lookup(value);
			BufferedReader br = (BufferedReader) pair.getSecond();
			br.close();

			state.getIFileTable().delete(value);
		}
		return null;
	}
	
	@Override
	public String toString(){
		return "(Close(" + this.exp_file_id.toString() + "))";
	}
}
