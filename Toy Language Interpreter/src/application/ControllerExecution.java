package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import Controller.Controller;
import Model.PrgState;
import Model.FileStatements.FileTable;
import Model.FileStatements.Pair;
import Model.Statements.IStmt;
import Model.Ultils.MyDictionary;
import Model.Ultils.MyHeap;
import Model.Ultils.MyList;
import Model.Ultils.MyStack;
import Model.Ultils.PairStrStr;
import Repository.IRepository;
import Repository.Repository;
import View.ExitCommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Controller.Controller;
import application.GUIController;

public class ControllerExecution {
	
	@FXML
	private Label label1;
	
	@FXML
	private Button runButton;
	
	@FXML
	private Label labelNbPrgStates;
	
	@FXML 
	private TextField nbStatesTextField;
	
	@FXML
	private ListView outListView;
	
	@FXML
	private ListView idListView;
	
	@FXML
	private TableView<PairStrStr> heapTableView;
	
	@FXML
	private TableColumn<PairStrStr, String> heapAddress;
	
	@FXML
	private TableColumn<PairStrStr, String> heapValue;
		
	@FXML
	private TableView<PairStrStr> fileTableView;
	
	@FXML
	private TableColumn<PairStrStr, String> fileTableId;
	
	@FXML
	private TableColumn<PairStrStr, String> fileTableFilename;
	
	@FXML
	private ListView stackListView;
	
	@FXML
	private TableView<PairStrStr> symTableView;
	
	@FXML
	private TableColumn<PairStrStr, String> idSymTbl;
	
	@FXML
	private TableColumn<PairStrStr, String> valSymTbl;
	
	MyDictionary<String, Integer> symTable;
	MyList<Integer> out;
	FileTable<Integer, Pair<String, BufferedReader>> fileTable;
	MyHeap<Integer, Integer> heap;
	MyStack<IStmt> stk;
	PrgState prg;
	IRepository repo;
	Controller ctr;
	List<PrgState> prgList; 


	
	@FXML
	public void initialize() {

		IStmt stmt = GUIController.statementExecution;
		
		label1.setText(stmt.toString());
		nbStatesTextField.setText("0");
		heapAddress.setCellValueFactory(new PropertyValueFactory<PairStrStr, String>("str1"));
		heapValue.setCellValueFactory(new PropertyValueFactory<PairStrStr, String>("str2"));
		fileTableId.setCellValueFactory(new PropertyValueFactory<PairStrStr, String>("str1"));
		fileTableFilename.setCellValueFactory(new PropertyValueFactory<PairStrStr, String>("str2"));
		idSymTbl.setCellValueFactory(new PropertyValueFactory<PairStrStr, String>("str1"));
		valSymTbl.setCellValueFactory(new PropertyValueFactory<PairStrStr, String>("str2"));

		symTable = new MyDictionary<String, Integer>();
		out = new MyList<Integer>();
		fileTable = new FileTable<Integer, Pair<String, BufferedReader>>();
		heap = new MyHeap<Integer, Integer>();
		stk = new MyStack<IStmt>();

				
		prg = new PrgState(1, stk , symTable, out, fileTable, heap, stmt);
		repo = new Repository("log.txt");
		repo.addPrgState(prg);
		ctr = new Controller(repo);
		
	}
	
	@FXML
	public void handleRunButton(ActionEvent event) throws IOException{
		ctr.executor = Executors.newFixedThreadPool(2);
		prgList = ctr.removeCompletedPrg(repo.getPrgList());
		int nbOfPrgStates = prgList.size();
		String strNbPrgStates = Integer.toString(nbOfPrgStates);
		nbStatesTextField.setText(strNbPrgStates);

		if(prgList.size() > 0){
			try {
				ctr.oneStepForAllPrg(prgList);
			} 
			catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			Object ob = idListView.getSelectionModel().getSelectedItem();
			if (ob != null) {
				int myId = Integer.parseInt(ob.toString());
				for ( PrgState p : prgList) {
					if (p.getId() == myId) {
						stk = (MyStack<IStmt>) p.getStk();
						symTable = (MyDictionary<String, Integer>) p.getSymTable();
						setStack();
						setSymTable();
					}
				}
			}
			//remove the completed programs
			setOut();
			setIdList();
			setHeapTable();
			setFileTable();
			prgList = ctr.removeCompletedPrg(repo.getPrgList());
		}
		else {
			ctr.executor.shutdownNow();
			repo.setPrgList(prgList);
			
			label1.setText("Run Ended");
			//ExitCommand exitCommand = new ExitCommand("0", "exit");
			//exitCommand.execute();
		}
	}
	
	private ObservableList<Integer> getOutList(){
		ObservableList<Integer> outList  = FXCollections.observableArrayList();
		if (out.isEmpty()) {
			return null;
		}
		else {
			int i = 0;
			while (i < out.sizeList()) {
				outList.add(out.getElemList(i));
				i++;
			}
			return outList;
		}
	}
	
	private ObservableList<String> getStackList(){
		if (stk.isEmpty()) {
			return null;
		}
		else {
			List<String> stmts = stk.getStack().stream().map(s->s.toString()).collect(Collectors.toList()); 
			ObservableList<String> stackList = FXCollections.observableArrayList(stmts);
			return stackList;
		}
		//stackListView.setItems(stackList);

	}
	
	private ObservableList<Integer> getIdList(){
		ObservableList<Integer> idList = FXCollections.observableArrayList();
		if (prgList.isEmpty()) {
			return null;
		}
		else {
			for(PrgState prgState : prgList) {
				if (idList.contains(prgState.getId()) == false) {
					idList.add(prgState.getId());
				}
			}
		
			return idList;
		}
	}
	
	private ObservableList<PairStrStr> getHeapTable(){
		
		ObservableList<PairStrStr> heapTable = FXCollections.observableArrayList();
		if (heap.isEmpty()) {
			return null;
		}
		else {
			for (Integer key : heap.heapKeySet()) {
				int value = heap.lookupHeap(key);
				PairStrStr p = new PairStrStr(Integer.toString(key), Integer.toString(value));
				heapTable.add(p);
			}
			return heapTable;
		}
	}
	
	private ObservableList<PairStrStr> getSymTable(){
		ObservableList<PairStrStr> symTbl = FXCollections.observableArrayList();
		
		if (symTable.isEmpty()) {
			return null;
		}
		else {
			for (String key : symTable.listKeys()) {
				int value = symTable.lookup(key);
				PairStrStr p = new PairStrStr(key, Integer.toString(value));
				symTbl.add(p);
			}
			return symTbl;
		}
	}
	private ObservableList<PairStrStr> getFileTable(){
		ObservableList<PairStrStr> filetable = FXCollections.observableArrayList();
		if (fileTable.isEmpty()) {
			return null;
		}
		else {
			for (Integer key : fileTable.fileTableKeySet()) {
				String fileStr = fileTable.lookup(key).getFirst();
				PairStrStr p = new PairStrStr(Integer.toString(key), fileStr);
				filetable.add(p);
			}
			return filetable;
		}
	}
	
	
	@FXML
	public void setOut() {
		outListView.setItems(getOutList());
	}
	
	@FXML
	public void setIdList() {
		idListView.setItems(getIdList());
	}
	
	@FXML
	public void setHeapTable() {
		heapTableView.setItems(getHeapTable());

	}
	
	@FXML
	public void setFileTable() {
		fileTableView.setItems(getFileTable());
	}
	
	@FXML
	public void setStack() {
		stackListView.setItems(getStackList());
	}
	
	@FXML
	public void setSymTable() {
		this.symTableView.setItems(getSymTable());
	}
	
	@FXML
	public void handleIdStack(MouseEvent event) {
		//1.luam prg id
		//2.mergem in prg list si cand gasim prg cu id
		//3.this.stk = prg cu id.get stk
		Object ob = idListView.getSelectionModel().getSelectedItem();
		int myId = Integer.parseInt(ob.toString());
		for ( PrgState p : prgList) {
			if (p.getId() == myId) {
				stk = (MyStack<IStmt>) p.getStk();
				symTable = (MyDictionary<String, Integer>) p.getSymTable();
				setStack();
				setSymTable();
			}
		}
	}
}
