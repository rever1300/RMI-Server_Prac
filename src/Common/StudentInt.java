package Common;

import ExamModels.QuestionsLike;

import java.rmi.Remote;

public interface StudentInt extends Remote {
    void sendQuestion(QuestionsLike questionsLike);
    void startExam(String message);
    void examFinished(int mark, String message);
    void RegisteredFailed(String message);
}
