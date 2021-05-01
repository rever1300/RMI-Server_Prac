package Common;

import java.rmi.Remote;

public interface StudentInt extends Remote {
    void sendQuestion(StudentInt student, String question);
    void sendMark(StudentInt student, int mark);
}
