package Server;

import Common.ProfessorInt;
import ExamModels.Exam;
import ExamModels.FinalExamsFile;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    private static Registry startRegistry(Integer port)
            throws RemoteException {
        if (port == null) {
            port = 1099;
        }
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            registry.list();
            // The above call will throw an exception
            // if the registry does not already exist
            return registry;
        } catch (RemoteException ex) {
            // No valid registry at that port.
            System.out.println("RMI registry cannot be located ");
            Registry registry = LocateRegistry.createRegistry(port);
            System.out.println("RMI registry created at port ");
            return registry;
        }
    }

    private static boolean start = false;

    private static class Interrupt extends Thread {
        String interrupt_key = null;
        Object semaphore = null;

        //semaphore must be the syncronized object
        public Interrupt(Object semaphore, String interrupt_key) {
            this.semaphore = semaphore;
            this.interrupt_key = interrupt_key;
        }

        public void run() {
            while (true) {
                //read the key
                Scanner scanner = new Scanner(System.in);
                String x = scanner.nextLine();
                System.out.println(x);
                if (x.equals(this.interrupt_key)) {
                    //if is the key we expect, change the variable, notify and return(finish thread)
                    synchronized (this.semaphore) {
                        start = true;
                        this.semaphore.notify();
                        return;
                    }
                }
            }
        }
    }

    public static void main(String []args) {
        Scanner pathInput = new Scanner(System.in);
        String startWord1 = "START";
        Object obj1 = new Object();
        String startWord2 = "FINISH";
        Object obj2 = new Object();
        Interrupt interrupt1 = new Interrupt(obj1, startWord1);
        Interrupt interrupt2 = new Interrupt(obj2, startWord2);

        try {
            Registry registry = startRegistry(null);
            ProfessorImpl pImp = new ProfessorImpl();

            System.out.print("Server initializing\n");
            pImp.uploadExam(pathInput.nextLine());

            registry.bind("EXAM", pImp);
            System.out.print("Beginning registering...");
            interrupt1.start();
            synchronized (obj1) {
                while (!start) {
                    System.out.print("Write \"" + startWord1 + "\" to start the exam");
                    obj1.wait();
                }
            }

            pImp.noRegistry(); /*To stop the registering time*/
            pImp.startExam();


            Examination examination = new Examination(pImp);
            examination.start();
            System.out.print("The exam starts now! Good Luck");
            start = false;
            interrupt2.start();
            synchronized (obj2) {
                while (!start) {
                    System.out.print("Write \"" + startWord2 + "\" to finish the exam");
                    obj2.wait();
                }
            }
            examination.interrupt();
            pImp.examFinished();

            HashMap<String, Exam> examsCompleted = examination.finishExam();
            System.out.print("The exam session finished!");

            FinalExamsFile.storeExam("marks.csv", examsCompleted);

        } catch (Exception e) {
            System.err.println("Server exception" + e.toString());
            e.printStackTrace();
        }
    }
}
