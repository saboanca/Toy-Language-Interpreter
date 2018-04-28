package Model.FileStatements;
import Model.Statements.*;
import Model.Expressions.*;
import Model.Ultils.*;
import Model.Exceptions.*;
import java.io.*;
import Model.PrgState;

public class OpenRFile implements IStmt{
	private String file_var_id;
	private String filename;
	static int count = 10;
	
	public OpenRFile(String file_var_id, String filename){
		this.file_var_id = file_var_id;
		this.filename = filename;
	}
	
	public PrgState execute(PrgState state) throws MyException, IOException{
		IFileTable<Integer, Pair<String, BufferedReader>> fileTable = state.getIFileTable();
		if (fileTable.isDefined(count)){
			throw new MyException("Filename exists!");
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		
		fileTable.add(count, new Pair(file_var_id, br));
		MyIDictionary<String, Integer> symTable = state.getSymTable();
		if (symTable.isDefined(file_var_id)){
			symTable.update(file_var_id, count);
		}
		else{
			symTable.add(file_var_id, count);
		}
		this.count++;
		return null;
	}
	
	@Override
	public String toString(){
		return "OpenRFile(" + this.file_var_id + ", " + this.filename + ")";
	}
}
