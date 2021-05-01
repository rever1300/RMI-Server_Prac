package Server;

import Common.ProfessorInt;
import Common.StudentInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProfessorImpl extends UnicastRemoteObject implements ProfessorInt {

    public ProfessorImpl() throws RemoteException {}


    @Override
    public void register(StudentInt student, String name) throws RemoteException {

    }

    @Override
    public void sendAnswer(StudentInt student, int i) {

    }
}
