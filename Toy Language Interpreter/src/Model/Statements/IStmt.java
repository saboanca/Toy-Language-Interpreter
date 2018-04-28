package Model.Statements;

import java.io.IOException;

import Model.PrgState;
import Model.Exceptions.*;
import Model.Expressions.*;
import Model.Ultils.*;

public interface IStmt {
	public String toString();
	public PrgState execute(PrgState state) throws MyException, IOException;
}
