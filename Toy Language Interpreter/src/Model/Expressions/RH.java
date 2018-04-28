package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Ultils.MyIDictionary;
import Model.Ultils.MyIHeap;

public class RH extends Exp{
	public String var_name;
	
	public RH(String var_name){
		this.var_name = var_name;
	}

	public int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer, Integer> heap) throws MyException {
		if (tbl.lookup(this.var_name) == null){
			throw new MyException("Variable not in SymTable!");
		}
		else{
			int heapKey = tbl.lookup(this.var_name);
			if (heap.lookupHeap(heapKey) == null){
				throw new MyException("Variable not in Heap!");
			}
			else{
				return heap.lookupHeap(heapKey);
			}
		}
	}

	@Override
	public String toString() {
		return "rH(" + this.var_name + ")";
	}
	
	
}
