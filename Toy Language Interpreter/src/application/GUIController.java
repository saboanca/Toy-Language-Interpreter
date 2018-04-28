package application;

import java.io.IOException;

import Model.Expressions.ArithExp;
import Model.Expressions.BoolExp;
import Model.Expressions.ConstExp;
import Model.Expressions.RH;
import Model.Expressions.VarExp;
import Model.FileStatements.CloseRFile;
import Model.FileStatements.OpenRFile;
import Model.FileStatements.ReadFile;
import Model.Statements.AssignStmt;
import Model.Statements.CompStmt;
import Model.Statements.ForkStmt;
import Model.Statements.IStmt;
import Model.Statements.IfStmt;
import Model.Statements.NewStmt;
import Model.Statements.PrintStmt;
import Model.Statements.WH;
import Model.Statements.WhileStmt;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIController {
	

	@FXML
	private ListView<IStmt> listView;
	
	@FXML
	private Label label;
	
	@FXML
	private Button button1;
	
	public static IStmt statementExecution;
	
	//event listener on button1.onAction
	
	
	@FXML
	public void handleButtonAction(ActionEvent event) throws IOException{
		statementExecution = getIStmtList().get(listView.getSelectionModel().getSelectedIndex());
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Execution.fxml"));
		stage.setTitle("Execution");
		stage.setScene(new Scene(root, 400,400));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		
	}
	
	
	private ObservableList<IStmt> getIStmtList(){
		IStmt ex1= new CompStmt(new AssignStmt("v",new ConstExp(2)), new PrintStmt(new VarExp("v")));
		IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithExp(new ConstExp(2), new ConstExp(2),2)),new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
		IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp(new ConstExp(2),new ArithExp(new ConstExp(3), new ConstExp(0),3),1)), new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ConstExp(1),1)), new PrintStmt(new VarExp("b"))));
    	IStmt ex4 = new CompStmt(new CompStmt(new CompStmt(new OpenRFile("var_f","test.in"),new ReadFile(new VarExp("var_f"), "var_c")),new CompStmt(new PrintStmt(new VarExp("var_f")),new IfStmt(new VarExp("var_c"),new PrintStmt(new VarExp("var_c")),new PrintStmt(new ConstExp(0))))),new CloseRFile(new VarExp("var_f")));
		IStmt ex5 = new CompStmt(new NewStmt("v",new ConstExp(2)), new PrintStmt(new VarExp("v")));
		IStmt ex6 = new CompStmt(new AssignStmt("v", new ConstExp(10)),new CompStmt(new NewStmt("v",new ConstExp(2)), new PrintStmt(new VarExp("v"))));
		IStmt ex7 = new CompStmt(new AssignStmt("v",new ConstExp(10)),new CompStmt(new NewStmt("v",new ConstExp(20)), new CompStmt(new NewStmt("a",new ConstExp(22)),new CompStmt(new WH("a",new ConstExp(30)),new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new RH("a")))))));
		IStmt ex8 = new CompStmt(new AssignStmt("v",new ConstExp(10)),new CompStmt(new NewStmt("v",new ConstExp(20)), new CompStmt(new NewStmt("a",new ConstExp(22)),new CompStmt(new WH("a",new ConstExp(30)),new CompStmt(new PrintStmt(new VarExp("a")),new CompStmt(new PrintStmt(new RH("a")),new AssignStmt("a",new ConstExp(0))))))));
		IStmt ex9 = new CompStmt(new AssignStmt("a", new BoolExp(new ConstExp(2), new ConstExp(4), "<")), new PrintStmt(new VarExp("a")));
		IStmt ex10 = new CompStmt(new AssignStmt("v", new ConstExp(6)), new CompStmt(new WhileStmt(new ArithExp(new VarExp("v"), new ConstExp(4), 2), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ConstExp(1), 2)))), new PrintStmt(new VarExp("v"))));
		IStmt ex11 = new CompStmt(
				new AssignStmt("v", new ConstExp(10)),
				new CompStmt(
						new NewStmt("a", new ConstExp(22)),
						new CompStmt(
								new ForkStmt(
										new CompStmt(
												new WH("a", new ConstExp(30)),
												new CompStmt(
														new AssignStmt("v", new ConstExp(32)),
														new CompStmt(
																new PrintStmt(new VarExp("v")),
																new PrintStmt(new RH("a"))
																)
														)
												)
										),
								new CompStmt(
										new PrintStmt(new VarExp("v")),
										new PrintStmt(new RH("a"))
										)
								)
						)
				);
		//create an observable list
		ObservableList<IStmt> statements = FXCollections.observableArrayList(ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11);
		
		return statements;
	}
	
	@FXML
	public void initialize() {
		listView.setItems(getIStmtList());
		
		//to set selection model
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		//select item at index = 0
		listView.getSelectionModel().selectIndices(0);
		
		//focus
		listView.getFocusModel().focus(2);
		
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IStmt>(){
			@Override
			public void changed(ObservableValue<? extends IStmt> observable, IStmt oldValue, IStmt newValue) {
				label.setText("OLD: " + oldValue + ", NEW: " + newValue);
			}
		});
	}
}
