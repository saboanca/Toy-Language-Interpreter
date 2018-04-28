package Model.Expressions;

import Model.Exceptions.*;
import Model.Ultils.*;

public class BoolExp extends Exp{
	Exp e1;
	Exp e2;
	String sign;
	
	public BoolExp(Exp e1, Exp e2, String sign){
		this.e1 = e1;
		this.e2 = e2;
		this.sign = sign;
	}
	
	@Override
	public String toString(){
		switch(this.sign){
			case "<": return "(" + this.e1.toString() + " < " + this.e2.toString() + ")";
			case "<=": return "(" + this.e1.toString() + " <= " + this.e2.toString() + ")";
			case "==": return "(" + this.e1.toString() + " == " + this.e2.toString() + ")";
			case "!=": return "(" + this.e1.toString() + " != " + this.e2.toString() + ")";
			case ">=": return "(" + this.e1.toString() + " >= " + this.e2.toString() + ")";
			case ">":  return "(" + this.e1.toString() + " > " + this.e2.toString() + ")";
			default: return "";
		}			
	}
	
	@Override
	public int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer, Integer> heap)throws MyException{
		if (this.sign.compareTo(">") == 0){
			if (this.e1.eval(tbl, heap) > this.e2.eval(tbl, heap)){
				return 1;
			}
			else{
				return 0;
			}
		}
		else if (this.sign.compareTo(">=") == 0){
			if (this.e1.eval(tbl, heap) >= this.e2.eval(tbl, heap)){
				return 1;
			}
			else{
				return 0;
			}
		}
		else if (this.sign.compareTo("==") == 0){
			if (this.e1.eval(tbl, heap) == this.e2.eval(tbl, heap)){
				return 1;
			}
			else{
				return 0;
			}
		}
		else if (this.sign.compareTo("!=") == 0){
			if (this.e1.eval(tbl, heap) != this.e2.eval(tbl, heap)){
				return 1;
			}
			else{
				return 0;
			}
		}
		else if (this.sign.compareTo("<=") == 0){
			if (this.e1.eval(tbl, heap) <= this.e2.eval(tbl, heap)){
				return 1;
			}
			else{
				return 0;
			}
		}
		else if (this.sign.compareTo("<") == 0){
			if (this.e1.eval(tbl, heap) < this.e2.eval(tbl, heap)){
				return 1;
			}
			else{
				return 0;
			}
		}
		else{
			throw new MyException("Cannot identify sign!");
		}
	}
}