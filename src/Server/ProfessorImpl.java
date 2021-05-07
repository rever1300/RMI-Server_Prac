package Server;

import Common.ProfessorInt;
import Common.StudentInt;
import ExamModels.Exam;
import ExamModels.FinalExamsFile;
import ExamModels.ParserCSV;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ProfessorImpl extends UnicastRemoteObject implements ProfessorInt {

    private Exam exam;
    /*HashMap<id,student>*/
    private HashMap<String, StudentInt> students;
    private HashMap<StudentInt, Exam> studentExam;
    private int examsIP;
    private boolean registry;


    public ProfessorImpl() throws RemoteException {
        super();
        this.students = new HashMap<>();
        this.studentExam = new HashMap<>();
        this.examsIP = 0;
        this.registry = true;
    }


    public void uploadExam(String path) throws IOException {
        this.exam = ParserCSV.build(path);
    }

    public void noRegistry(){
        this.registry = false;
    }

    public void startExam(){
        for(Map.Entry<String, StudentInt> studentSet : this.students.entrySet()){
            String studentID = studentSet.getKey();
            StudentInt student = studentSet.getValue();
            student.startExam("The exam is starting now");
            student.sendQuestion(this.studentExam.get(studentID).nextQuestion());
            this.examsIP++;
        }
    }

    @Override
    public void register(StudentInt student, String name) throws RemoteException {

    }

    @Override
    public void sendAnswer(StudentInt student, int i) {

    }
}
