package View;
import Model.PrgState;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Statements.*;
import Model.Ultils.*;
import Repository.*;
import Controller.*;

public class Main {
	/*public static void main(String[] args){
		 //IStmt ex1= new CompStmt(new AssignStmt("v",new ConstExp(2)), new PrintStmt(new VarExp("v")));
		 IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp(new ConstExp(2), new ConstExp(2),2)),new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
		 //IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithExp(new ConstExp(2),new ArithExp(new ConstExp(3), new ConstExp(0),3),1)), new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ConstExp(1),1)), new PrintStmt(new VarExp("b"))));
		 //IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithExp(new ConstExp(2),new ArithExp(new ConstExp(3), new ConstExp(5)),3), 2),  new CompStmt(new AssignStmt("b",new ArithExp(2,new VarExp("a"), new ConstExp(1))), new PrintStmt(new VarExp("b")));
		 //IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp('-',new ConstExp(2), new ConstExp(2))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
		 //System.out.println(ex1.toString());
		 //PrgState state = new PrgState(new MyStack<IStmt>(), new  MyDictionary<String,Integer>(), new MyList<Integer>(), ex1);
		 PrgState state = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Integer>(), new MyList<Integer>(), ex3);
		 IRepository repository = new Repository();
		 repository.addPrgState(state);
		 Controller controller = new Controller(repository);
		 //controller.getRepo().addPrgState(state);
		 //PrgState state = controller.getRepo().getCrtPrg();
		 //ex1.execute(state);
		 //PrgState state1 = controller.oneStep(state);
		 
		 //System.out.println(controller.oneStep(state).toString());
		 
		 //try{
		 //System.out.println(controller.oneStep(state).toString());
		 controller.allStep();
		 //}catch(MyException e){
			//System.out.println(e.getMessage());
		 //}

	}*/
}
