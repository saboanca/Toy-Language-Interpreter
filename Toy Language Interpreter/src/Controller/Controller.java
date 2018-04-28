package Controller;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Repository.*;
import Model.Ultils.*;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Statements.*;
import Model.PrgState;
import Model.FileStatements.*;

public class Controller {
	IRepository repo;//public, private?
	public ExecutorService executor;
	
	public Controller(IRepository repo){
		this.repo = repo;
	}
	
	public IRepository getRepo(){
		return this.repo;
	}
	
	/*public PrgState oneStep(PrgState state) throws MyException, IOException{
		MyIStack<IStmt> stk = state.getStk();
		if (stk.isEmpty()){
			throw new MyException("Empty stack!");
		}
		IStmt crtStmt = stk.pop();
		return crtStmt.execute(state);
	}*/
	
	Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap){
		return heap.entrySet().stream()
				.filter(e->symTableValues.contains(e.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
		return inPrgList.stream()
				.filter(p->p.isNotCompleted())
				.collect(Collectors.toList());
	}
	
	/*public void allStep(){
		PrgState prg = repo.getCrtPrg();
		try{
			while (true){
				oneStep(prg);
				prg.getHeap().setContent(conservativeGarbageCollector(prg.getSymTable().getContent().values(), prg.getHeap().getContent()));
				this.repo.logPrgStateExec();
				//System.out.println(displayCrtPrgState());
				//here you can display the prg state
			}
		}
		catch(MyException e){
			System.out.println(e.getMessage());
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		finally{
			prg.getIFileTable().values().stream().forEach(pair->{
					try {
						pair.getSecond().close();
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
			});
		}
	}*/
	
	public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException{
		//before the execution, print the PrgState List into the log file
		
		prgList.forEach(prg->{
			try{
				repo.logPrgStateExec(prg);	
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		});
		//Run concurrently one step for each of the existing PrgStates
		//----
		//prepare the list of callables
		List<Callable<PrgState>> callList = prgList.stream()
				.map((PrgState p)->(Callable<PrgState>)(()->{return p.oneStep();}))
				.collect(Collectors.toList());
		
		//start the execution of the callables
		//it returns the list of new created PrgStates (namely threads)
		//executor = Executors.newFixedThreadPool(2);
		List<PrgState> newPrgList;
			newPrgList = executor.invokeAll(callList).stream()
					.map(future->{try {return future.get();}
						catch(Exception e){
							System.out.println(e.getMessage());
						}
					return null;})
					.filter(p->p!=null)
					.collect(Collectors.toList());
		//add the new created threads to the list of existing threads
		prgList.addAll(newPrgList);
		//after the execution, print the PrgState List into the log file
		//prgList.forEach(prg->repo.logPrgStateExec(prg));
		prgList.forEach(prg->{
			try{
				repo.logPrgStateExec(prg);	
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		});
		//save the current programs in the repository
		repo.setPrgList(prgList);
	}

	public void allStep(){
		executor = Executors.newFixedThreadPool(2);
		//remove the completed programs
		List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
		while(prgList.size() > 0){
			try {
				oneStepForAllPrg(prgList);
			} catch (InterruptedException e) {
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
			//remove the completed programs
			prgList = removeCompletedPrg(repo.getPrgList());
		}
		executor.shutdownNow();
		//here the repository still contains at least one CompletedPrg
		//and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
		//setPrgList of repository in order to change the repository
		
		//update the repository state
		repo.setPrgList(prgList);
	}
	
	public PrgState displayCrtPrgState(){
		return this.repo.getCrtPrg();
	}
}
