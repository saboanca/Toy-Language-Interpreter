package View;

import java.io.BufferedReader;

import Controller.Controller;
import Model.PrgState;
import Model.Expressions.ArithExp;
import Model.Expressions.BoolExp;
import Model.Expressions.ConstExp;
import Model.Expressions.RH;
import Model.Expressions.VarExp;
import Model.Statements.AssignStmt;
import Model.Statements.CompStmt;
import Model.Statements.ForkStmt;
import Model.Statements.IStmt;
import Model.Statements.IfStmt;
import Model.Statements.NewStmt;
import Model.Statements.PrintStmt;
import Model.Statements.WH;
import Model.Statements.WhileStmt;
import Model.Ultils.MyDictionary;
import Model.Ultils.MyHeap;
import Model.Ultils.MyList;
import Model.Ultils.MyStack;
import Model.FileStatements.*;
import Repository.IRepository;
import Repository.Repository;

public class Interpreter {
	public static void main(String[] args){
		
		MyDictionary<String, Integer> symTable = new MyDictionary<String, Integer>();
		MyList<Integer> out = new MyList<Integer>();
		FileTable<Integer, Pair<String, BufferedReader>> fileTable = new FileTable<Integer, Pair<String, BufferedReader>>();
		MyHeap<Integer, Integer> heap = new MyHeap<Integer, Integer>();
		
		
		IStmt ex1= new CompStmt(new AssignStmt("v",new ConstExp(2)), new PrintStmt(new VarExp("v")));
		PrgState prg1 = new PrgState(1, new MyStack<IStmt>(), symTable, out, fileTable, heap, ex1);
		IRepository repo1 = new Repository("log1.1.txt");
		repo1.addPrgState(prg1);
		Controller ctr1 = new Controller(repo1);
		
		
		IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithExp(new ConstExp(2), new ConstExp(2),2)),new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
		PrgState prg2 = new PrgState(1, new MyStack<IStmt>(), symTable, out, fileTable, heap, ex2);
		IRepository repo2 = new Repository("log1.1.txt");
		repo2.addPrgState(prg2);
		Controller ctr2 = new Controller(repo2);
		
		
		IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp(new ConstExp(2),new ArithExp(new ConstExp(3), new ConstExp(0),3),1)), new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ConstExp(1),1)), new PrintStmt(new VarExp("b"))));
		PrgState prg3 = new PrgState(1, new MyStack<IStmt>(), symTable, out, fileTable, heap, ex3);
		IRepository repo3 = new Repository("log1.1.txt");
		repo3.addPrgState(prg3);
		Controller ctr3 = new Controller(repo3);

    	IStmt ex4 = new CompStmt(new CompStmt(new CompStmt(new OpenRFile("var_f","test.in"),new ReadFile(new VarExp("var_f"), "var_c")),new CompStmt(new PrintStmt(new VarExp("var_f")),new IfStmt(new VarExp("var_c"),new PrintStmt(new VarExp("var_c")),new PrintStmt(new ConstExp(0))))),new CloseRFile(new VarExp("var_f")));
		PrgState prg4 = new PrgState(1, new MyStack<IStmt>(), symTable, out, fileTable, heap, ex4);
		IRepository repo4 = new Repository("log1.1.txt");
		repo4.addPrgState(prg4);
		Controller ctr4 = new Controller(repo4);
		
		IStmt ex5 = new CompStmt(new NewStmt("v",new ConstExp(2)), new PrintStmt(new VarExp("v")));
		PrgState prg5 = new PrgState(1, new MyStack<IStmt>(), symTable, out, fileTable, heap, ex5);
		IRepository repo5 = new Repository("log1.1.txt");
		repo5.addPrgState(prg5);
		Controller ctr5 = new Controller(repo5);
		
		IStmt ex6 = new CompStmt(new AssignStmt("v", new ConstExp(10)),new CompStmt(new NewStmt("v",new ConstExp(2)), new PrintStmt(new VarExp("v"))));
		PrgState prg6 = new PrgState(1, new MyStack<IStmt>(), symTable, out, fileTable, heap, ex6);
		IRepository repo6 = new Repository("log1.1.txt");
		repo6.addPrgState(prg6);
		Controller ctr6 = new Controller(repo6);
		
		IStmt ex7 = new CompStmt(new AssignStmt("v",new ConstExp(10)),new CompStmt(new NewStmt("v",new ConstExp(20)), new CompStmt(new NewStmt("a",new ConstExp(22)),new CompStmt(new WH("a",new ConstExp(30)),new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new RH("a")))))));
		PrgState prg7 = new PrgState(1, new MyStack<IStmt>(), symTable, out, fileTable, heap, ex7);
		IRepository repo7 = new Repository("log1.1.txt");
		repo7.addPrgState(prg7);
		Controller ctr7 = new Controller(repo7);

		IStmt ex8 = new CompStmt(new AssignStmt("v",new ConstExp(10)),new CompStmt(new NewStmt("v",new ConstExp(20)), new CompStmt(new NewStmt("a",new ConstExp(22)),new CompStmt(new WH("a",new ConstExp(30)),new CompStmt(new PrintStmt(new VarExp("a")),new CompStmt(new PrintStmt(new RH("a")),new AssignStmt("a",new ConstExp(0))))))));
		PrgState prg8 = new PrgState(1, new MyStack<IStmt>(), symTable, out, fileTable, heap, ex8);
		IRepository repo8 = new Repository("log1.1.txt");
		repo8.addPrgState(prg8);
		Controller ctr8 = new Controller(repo8);
		
		IStmt ex9 = new CompStmt(new AssignStmt("a", new BoolExp(new ConstExp(2), new ConstExp(4), "<")), new PrintStmt(new VarExp("a")));
		PrgState prg9 = new PrgState(1, new MyStack<IStmt>(), symTable, out, fileTable, heap, ex9);
		IRepository repo9 = new Repository("log1.1.txt");
		repo9.addPrgState(prg9);
		Controller ctr9 = new Controller(repo9);
		
		IStmt ex10 = new CompStmt(new AssignStmt("v", new ConstExp(6)), new CompStmt(new WhileStmt(new ArithExp(new VarExp("v"), new ConstExp(4), 2), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ConstExp(1), 2)))), new PrintStmt(new VarExp("v"))));
		PrgState prg10 = new PrgState(1, new MyStack<IStmt>(), symTable, out, fileTable, heap, ex10);
		IRepository repo10 = new Repository("log1.1.txt");
		repo10.addPrgState(prg10);
		Controller ctr10 = new Controller(repo10);
		
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
		
		PrgState prg11 = new PrgState(1, new MyStack<IStmt>(), symTable, out, fileTable, heap, ex11);
		IRepository repo11 = new Repository("log1.1.txt");
		repo11.addPrgState(prg11);
		Controller ctr11 = new Controller(repo11);
		
		TextMenu menu = new TextMenu();
		menu.addCommand(new ExitCommand("0", "exit"));
		menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
		menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
		menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
		menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
		menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
		menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
		menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
		menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
		menu.addCommand(new RunExample("9", ex9.toString(), ctr9));
		menu.addCommand(new RunExample("10", ex10.toString(), ctr10));
		menu.addCommand(new RunExample("11", ex11.toString(), ctr11));


		menu.show();
	}
}
