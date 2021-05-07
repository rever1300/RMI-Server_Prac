package Server;

import ExamModels.Exam;

import java.util.HashMap;

/*I made this extra Thread-class to manage the beginning and the finish of the exam in other thread*/
public class Examination extends Thread {
    private final ProfessorImpl pImp;

    public Examination(ProfessorImpl pImp) {
        this.pImp = pImp;
    }

    @Override
    public void run() {
        try {
            while (this.pImp.finished()) {
                synchronized (this.pImp) {
                    this.pImp.wait();
                    String studentID = this.pImp.getStudentID();
                    if (!this.pImp.studentFinished(studentID)) {
                        this.pImp.nextQuestion(studentID);
                    } else {
                        this.pImp.examFinished(studentID);
                    }
                }
            }
            System.out.print("All students have finished!!");
        }catch (Exception e){
            System.err.print("A server error ocurred!");
        }
    }
    public HashMap<String, Exam> finishExam(){
        return this.pImp.getExams();
    }
}
