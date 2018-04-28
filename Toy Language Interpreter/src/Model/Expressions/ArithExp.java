package Model.Expressions;

import Model.Ultils.*;
import Model.Statements.*;
import Model.Exceptions.*;


public class ArithExp extends Exp{
	Exp e1;
	Exp e2;
	int op; //1 stands for +, 2 stands for -
	
	public ArithExp(Exp e1, Exp e2, int op){
		this.e1 = e1;
		this.e2 = e2;
		this.op = op;
	}
	
	@Override
	public String toString(){
		switch(this.op){
			case 1 : return this.e1.toString() + "+" + this.e2.toString();
			case 2 : return this.e1.toString() + "-" + this.e2.toString();
			case 3 : return this.e1.toString() + "*" + this.e2.toString();
			case 4 : return this.e1.toString() + "/" + this.e2.toString();
			default: return "";
		}
	}
	@Override //si aici override?
	public int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer, Integer> heap)throws MyException{
		if (op == 1){
			return (e1.eval(tbl, heap) + e2.eval(tbl, heap));
		}
		else if (op == 2){
			return (e1.eval(tbl, heap) - e2.eval(tbl, heap));
		}
		else if (op == 3){
			return (e1.eval(tbl, heap) * e2.eval(tbl, heap));
		}
		
		else //if (op == 4){//exceptie la div cu 0
			
			if (e2.eval(tbl, heap) == 0){
				throw new MyException("Division by 0!");
			}
			return (e1.eval(tbl, heap) / e2.eval(tbl, heap));
		//}
	}
}
