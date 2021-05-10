package Server;

import Common.ProfessorInt;
import Common.StudentInt;
import ExamModels.Exam;
import ExamModels.FinalExamsFile;
import ExamModels.ParserCSV;
import ExamModels.QuestionsLike;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ProfessorImpl extends UnicastRemoteObject implements ProfessorInt {

    private Exam exam;
    /*HashMap<id,student>*/
    private HashMap<String, StudentInt> students;
    private HashMap<String, Exam> studentExams;
    private int numberOfStudents;
    private int examsIP;
    private boolean examIP;
    private boolean registry;
    private String studentRequest;

    public ProfessorImpl() throws RemoteException {
        super();
        this.students = new HashMap<>();
        this.studentExams = new HashMap<>();
        this.examsIP = 0;
        this.numberOfStudents = 0;
        this.registry = true;
        this.examIP = true;
    }


    public void uploadExam(String path) throws IOException {
        this.exam = ParserCSV.build(path);
    }

    public void noRegistry() {
        this.registry = false;
    }

    public void startExam() {
        for (Map.Entry<String, StudentInt> studentSet : this.students.entrySet()) {
            String studentID = studentSet.getKey();
            StudentInt student = studentSet.getValue();
            student.startExam("The exam is starting now");
            student.sendQuestion(this.studentExams.get(studentID).nextQuestion());
            this.examsIP++;
        }
    }

    public void examFinished() {
        /*We obtain the marks*/
        for (Map.Entry<String, StudentInt> studentSet : this.students.entrySet()) {
            String studentID = studentSet.getKey();
            StudentInt student = studentSet.getValue();
            try {
                student.examFinished(this.studentExams.get(studentID).getMark(), "EXAM FINISHED!!");
            } catch (Exception e) {
                System.out.print(e.toString());
                e.printStackTrace();
            }
        }
    }

    /*CHECK FINALIZATION*/
    public boolean finished() {
        return this.examsIP == 0;
    }

    public boolean studentFinished(String student) {
        if (!this.studentExams.get(student).hasNext()) {
            this.examsIP--;
            return true;
        }
        return false;
    }

    public String getStudentID() {
        /*Each student has their one*/
        return this.studentRequest;

    }

    public synchronized void examFinished(String student) {
        try {
            this.students.get(student).examFinished((this.studentExams.get(student).getMark()), "YOUR EXAM HAS FINISHED!!");
        } catch (Exception e) {
            System.out.print(e.toString());
            e.printStackTrace();
        }
    }

    public void nextQuestion(String student) {
        this.students.get(student).sendQuestion(this.studentExams.get(student).nextQuestion());
    }

    public HashMap<String, Exam> getExams() {
        return this.studentExams;
    }

    @Override
    public synchronized void register(StudentInt student, String name){
        if (this.registry) {
            this.students.put(name,student);
            this.numberOfStudents++;
            System.out.print("There are "+ numberOfStudents + " students");
            this.studentExams.put(name ,this.exam.examInstance());
        } else {
            student.RegisteredFailed("Registration Time expired!");
            System.out.print("The " + name + " student cannot enter due to expired registration time");
        }
    }

    @Override
    public synchronized void sendAnswer(String student, QuestionsLike questionsLike) {
        if(this.examIP){
            this.studentExams.get(student).toAnswer(questionsLike);
            this.studentRequest = student;
        }
        notify();
    }
}
