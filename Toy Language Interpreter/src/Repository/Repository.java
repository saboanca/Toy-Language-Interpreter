package Repository;
import Model.*;

import java.io.*;
import java.util.*;

public class Repository implements IRepository{
	//lista de prg state-uri
	//metodele din irepository
	
	//public Repository(){}
	
	List<PrgState> PrgStateList = new ArrayList<PrgState>();
	int current;
	String logFilePath;//the path to the text file, read from the keyboard
	
	public Repository(String logFilePath){
		this.PrgStateList = new ArrayList<PrgState>();
		this.current = 0;
		this.logFilePath = logFilePath;
	}
	
	public PrgState getCrtPrg(){
		return this.PrgStateList.get(current);
	}
	
	public void addPrgState(PrgState state){
		this.PrgStateList.add(state);
	}
	
	public List<PrgState> getPrgList(){
		return this.PrgStateList;
	}
	
	public void setPrgList(List<PrgState> newList){
		this.PrgStateList = newList;
	}
	
	//change to logPrgStateExec(PrgState state)
	public void logPrgStateExec(PrgState state) throws IOException {//saves the content of the prgstate in a text file
		PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));//creem fisieru
		//logFile.append(this.PrgStateList.get(current).toString());
		logFile.append(state.toString());
		logFile.flush();
		
	}
}
