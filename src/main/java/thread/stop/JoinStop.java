package thread.stop;

import java.util.ArrayList;
import java.util.List;

import thread.Task;

public class JoinStop {
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<Thread>();

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new Task());
            t.start();
            list.add(t);
        }

        try {
            for (Thread t : list) {
                t.join();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("main thread is exiting with join for waiting child thread");
    }
}
