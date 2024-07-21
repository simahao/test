package thread.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class NewTask extends RecursiveAction {

    private long load = 0;

    private long counter = 0;

    public NewTask(long load) {
        this.load = load;
    }

    protected void compute() {
        List<NewTask> subTasks = createSubTasks();
        if (subTasks == null) {
            return;
        }
        for (NewTask nt : subTasks) {
            nt.fork();
        }
    }

    private List<NewTask> createSubTasks() {

        if (this.load == 0) {
            return null;
        }

        List<NewTask> subTasks = new ArrayList<>();

        NewTask t1 = new NewTask(this.load / 2);
        NewTask t2 = new NewTask(this.load / 2);
        NewTask t3 = new NewTask(this.load / 2);
        NewTask t4 = new NewTask(this.load / 2);

        subTasks.add(t1);
        subTasks.add(t2);
        subTasks.add(t3);
        subTasks.add(t4);

        System.out.println("counter = " + ++counter);
        return subTasks;
    }

    public static void main(String[] args) throws InterruptedException {
        int proc = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of available core in the processor is: "
            + proc);

        ForkJoinPool pool = ForkJoinPool.commonPool();
        System.out.println("Common Pool Size is: "
                           + pool.getPoolSize());
        System.out.println("Number of active thread before invoking: "
            + pool.getActiveThreadCount());

        NewTask nt = new NewTask(400);
        pool.invoke(nt);

        System.out.println("Number of active thread after invoking: "
            + pool.getActiveThreadCount());
        System.out.println("Common Pool Size is: "
                           + pool.getPoolSize());

        Thread.sleep(1000);
    }
}
