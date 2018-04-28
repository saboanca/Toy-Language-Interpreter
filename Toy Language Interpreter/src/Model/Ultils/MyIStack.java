package Model.Ultils;

import java.util.Stack;

import Model.PrgState;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Statements.*;

public interface MyIStack<T> {
	public T pop() throws MyException;
	public void push(T v);
	public boolean isEmpty();
	public String toString();
	public Stack<T> getStack();
}
