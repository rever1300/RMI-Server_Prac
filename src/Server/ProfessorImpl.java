package Server;

import Common.ProfessorInt;
import Common.StudentInt;
import ExamModels.FinalExamsFile;
import ExamModels.ParserCSV;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProfessorImpl extends UnicastRemoteObject implements ProfessorInt {

    public ProfessorImpl() throws RemoteException {
        super();

    }


    public void uploadExam(String path) throws IOException {
        this.exam = ParserCSV.build(path);
    }

    @Override
    public void register(StudentInt student, String name) throws RemoteException {

    }

    @Override
    public void sendAnswer(StudentInt student, int i) {

    }
}
