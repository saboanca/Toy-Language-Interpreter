package Model.FileStatements;
import Model.Statements.*;
import Model.Expressions.*;
import Model.Ultils.*;
import Model.Exceptions.*;

import java.io.*;

import Model.PrgState;

public class ReadFile implements IStmt{
	private Exp exp_file_id;
	private String var_name;
	
	public ReadFile(Exp exp_file_id, String var_name){
		this.exp_file_id = exp_file_id;
		this.var_name = var_name;
	}
	
	public PrgState execute(PrgState state) throws MyException, IOException{
		int value;
		MyIDictionary<String, Integer> symTable = state.getSymTable();
		MyIHeap<Integer, Integer> heap = state.getHeap();
		value = exp_file_id.eval(symTable, heap);
		if (state.getIFileTable().isDefined(value)){
			Pair pair = state.getIFileTable().lookup(value);
			BufferedReader br = (BufferedReader) pair.getSecond();
			String line = br.readLine();
			int createdValue;
			if (line == null){
				createdValue = 0;
			}
			else{
				createdValue = Integer.parseInt(line);
			}
			if (symTable.isDefined(var_name)){
				symTable.update(var_name, createdValue);
			}
			else{
				symTable.add(var_name, createdValue);
			}
		}
		else{
			throw new MyException("No lookup value for ReadFile!");
		}					
		return null;
	}

	@Override
	public String toString(){
		return "(ReadFile (" + this.var_name + ", " + this.exp_file_id + "))";
	}
}
