package Model.Expressions;

import Model.Ultils.*;
import Model.Statements.*;
import Model.Exceptions.*;

public abstract class Exp {
	public abstract int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer, Integer> heap) throws MyException;
	public abstract String toString();
}
