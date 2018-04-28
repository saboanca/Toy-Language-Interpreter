package Model.Expressions;

import Model.Ultils.*;
import Model.Statements.*;
import Model.Exceptions.*;

public class ConstExp extends Exp{
	int number;
	
	public ConstExp(int number){
		this.number = number;
	}
	
	@Override
	public int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer, Integer> heap){
		return this.number;
	}

	@Override
	public String toString() {
		return Integer.toString(this.number);
	}
	
}
