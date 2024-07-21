package thread.forkjoin;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class CalculateTask extends RecursiveTask<Integer> {

    private static final int THREADHOLD = 10000;

    private int start;

    private int end;

    public CalculateTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start < THREADHOLD) {
            int result = 0;
            for (int i = start; i <= end; i++) {
                result += i;
            }
            return result;
        } else {
            int middle = (start + end) / 2;
            CalculateTask first = new CalculateTask(start, middle);
            CalculateTask second = new CalculateTask(middle + 1, end);
            invokeAll(first, second);
            return first.join() + second.join();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int result = 0;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 1; i <= 1000000; i++) {
            result += i;
        }
        stopWatch.stop();

        System.out.println("result: " + result + " spend time: " + stopWatch.getTime(TimeUnit.MILLISECONDS));

        ForkJoinPool pool = new ForkJoinPool();
        stopWatch.reset();
        stopWatch.start();
        ForkJoinTask<Integer> task = pool.submit(new CalculateTask(1, 1000000));
        result = task.get();
        System.out.println("threads number: " + pool.getPoolSize());
        stopWatch.stop();
        System.out.println("result: " + result + " spend time: " + stopWatch.getTime(TimeUnit.MILLISECONDS));
        pool.awaitTermination(10, TimeUnit.SECONDS);
        pool.shutdown();
    }
}
