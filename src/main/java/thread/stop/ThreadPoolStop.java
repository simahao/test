package thread.stop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import thread.Task;

public class ThreadPoolStop {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            pool.execute(new Task());
        }

        pool.shutdown();

        while (!pool.isTerminated()) {
        }
        // timeout mode
        //while(!pool.awaitTermination(2, TimeUnit.SECONDS));
        System.out.println("main thread is exiting with ThreadPool(isTerminated) for waiting child thread");
    }
}
