package futuretask;

import java.util.concurrent.FutureTask;

/**
 * output:
 * future thread_0
 * future thread_1
 * future thread_2
 * future thread_3
 * future thread_4
 * future thread_5
 * future thread_6
 * future thread_7
 * future thread_8
 * future thread_9
 * last outcome:10
 */
public class FutureThread {
    public static void main(String[] args) {

        Task task = new Task();
        FutureTask<Integer> future = new FutureTask<>(task);
        Thread thread = new Thread(future);
        thread.setName("future thread");
        thread.start();

        try {
            Integer outcome = future.get();
            System.out.println("last outcome:" + outcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
