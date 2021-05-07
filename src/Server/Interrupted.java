package Server;

import java.util.Scanner;

public class Interrupted {
    //variable to change when start
    public static boolean start = false;

    private static class Interrupt extends Thread {
        String interrupt_key = null;
        Object semaphore = null;

        //semaphore must be the syncronized object
        private Interrupt(Object semaphore, String interrupt_key) {
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
}


