package Common;

import ExamModels.QuestionsLike;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentInt extends Remote {
    void sendQuestion(QuestionsLike questionsLike) throws RemoteException;
    void startExam(String message) throws RemoteException;
    void examFinished(int mark, String message) throws RemoteException;
    void RegisteredFailed(String message) throws RemoteException;
}
