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
       Integer outcome = future.get();
       System.out.println("last outcome:" + outcome);
       executor.shutdown();
   } 
}
