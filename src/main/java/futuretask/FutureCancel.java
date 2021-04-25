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
        executor.shutdown();

        // 这里短暂暂停一下，模拟非阻塞模式（主线程做其他工作）
        try {
            Thread.sleep(50L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 通过future发起cancel操作，如果future在wait，sleep，会抛出interupt exception
        // 另外，为了让task能够cancel，需要在task中利用Thread.currentThread().isInterruted()进行控制
        future.cancel(true);

        // main thread wait child terminate
        while (!executor.isTerminated()) {
        }
    }
}
