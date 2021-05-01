package Common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProfessorInt extends Remote {
    void register(StudentInt student, String name) throws RemoteException;
    void sendAnswer(StudentInt student, int i);
}
