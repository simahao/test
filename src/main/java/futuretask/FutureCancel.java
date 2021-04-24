package futuretask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureCancel {
    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> future = new FutureTask<Integer>(task);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(future);

        try {
            Thread.sleep(50L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        future.cancel(true);

        executor.shutdownNow();
    }    
}
