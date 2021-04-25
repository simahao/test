package futuretask;

import java.util.concurrent.FutureTask;

public class FutureThread {
    public static void main(String[] args) {

        Task task = new Task();
        FutureTask<Integer> future = new FutureTask<>(task);
        Thread thread = new Thread(future);
        thread.setName("future thread");
        thread.start();

        try {
            // get result until task return outcome
            // main thread is blocked by child thread
            Integer outcome = future.get();
            System.out.println("last outcome:" + outcome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
