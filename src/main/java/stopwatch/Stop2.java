package stopwatch;

import org.springframework.util.StopWatch;

public class Stop2 {

    public static void main(String[] args) throws InterruptedException {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("task1");
        Thread.sleep(1000);
        System.out.println("current task: " + stopWatch.currentTaskName());
        stopWatch.stop();

        stopWatch.start("task2");
        Thread.sleep(2000);
        System.out.println("current task: " + stopWatch.currentTaskName());
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.shortSummary());

        System.out.println("all tasks spend: " + stopWatch.getTotalTimeMillis());
        System.out.println("tasks number: " + stopWatch.getTaskCount());
        System.out.println("tasks info: " + stopWatch.getTaskInfo());

    }
}
