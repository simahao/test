package thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class PrintTask extends RecursiveAction {

    private static final int THRESHOLD = 5;

    private  int start;

    private  int end;

    PrintTask(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i <= end; i++) {
                System.out.println(Thread.currentThread().getName() + ",i=" + i);
            }
        } else {
            int middle = (start + end) / 2;
            PrintTask firstTask = new PrintTask(start, middle);
            PrintTask secondTask = new PrintTask(middle + 1, end);
            invokeAll(firstTask, secondTask);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new PrintTask(1, 50));
        pool.awaitTermination(20, TimeUnit.SECONDS);
        pool.shutdown();
    }
}
