package futuretask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskExecutorService {
    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> future = new FutureTask<Integer>(task) {
            @Override
            protected void done() {
                try {
                    System.out.println("future.done:" + get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(future);

        try {
            System.out.println("future.get():" + future.get());
        } catch (Exception e) {
            System.out.println("cancel future task");
        }
        executor.shutdown();
    }
}
