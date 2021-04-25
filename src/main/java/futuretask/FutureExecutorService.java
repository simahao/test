package futuretask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExecutorService {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Task task = new Task();
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(task);
        // get result until task return outcome
        // main thread is blocked by child thread
        Integer outcome = future.get();
        System.out.println("last outcome:" + outcome);
        executor.shutdown();
    }
}
