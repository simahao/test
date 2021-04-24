package futuretask;

import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 10 && !Thread.currentThread().isInterrupted(); i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "_" + i);
                Thread.sleep(500);
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return i;
    }
}
