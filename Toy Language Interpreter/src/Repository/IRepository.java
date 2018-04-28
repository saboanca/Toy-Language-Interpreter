package Repository;
import Model.*;

import java.io.*;
import java.util.List;

public interface IRepository {
	public PrgState getCrtPrg();
	public void addPrgState(PrgState state);
	public void logPrgStateExec(PrgState state) throws IOException;
	public List<PrgState> getPrgList();
	public void setPrgList(List<PrgState> newList);
}
