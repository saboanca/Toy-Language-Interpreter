package Model.Ultils;

import java.util.Stack;

import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Statements.*;

public class MyStack<T> implements MyIStack<T> {
	Stack<T> stack = new Stack<T>();//collection
	
	public T pop() throws MyException{//exception if stack is empty
		if (this.stack.isEmpty()){
			throw new MyException("Empty stack!");
		}
		return this.stack.pop();
	}
	
	public void push(T v){
		this.stack.push(v);
	}
	
	public Stack<T> getStack(){
		return this.stack;
	}
	
	public boolean isEmpty(){
		return this.stack.isEmpty();
	}
	
	public String toString(){
		//return "" + this.stack;
		String str = "";
		for(T e:stack){
			str = str + e.toString();
		}
		return str;
	}
	
}
