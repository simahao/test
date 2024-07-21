package stopwatch;


import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

public class Stop1 {

    private void sleepOneSecondAndGetStopWatchReading(StopWatch stopWatch) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("StopWatch Time: " + stopWatch.getTime(TimeUnit.MILLISECONDS) + "ms");
    }

    public void testStopWatch1() throws InterruptedException {

        StopWatch stopWatch = new StopWatch();
        sleepOneSecondAndGetStopWatchReading(stopWatch);
        stopWatch.start();
        sleepOneSecondAndGetStopWatchReading(stopWatch);
        stopWatch.stop();
        sleepOneSecondAndGetStopWatchReading(stopWatch);
        stopWatch.reset();
        sleepOneSecondAndGetStopWatchReading(stopWatch);
        stopWatch.start();
        sleepOneSecondAndGetStopWatchReading(stopWatch);
        stopWatch.split();
        sleepOneSecondAndGetStopWatchReading(stopWatch);
        stopWatch.split();
        sleepOneSecondAndGetStopWatchReading(stopWatch);
        stopWatch.unsplit();
        sleepOneSecondAndGetStopWatchReading(stopWatch);
        stopWatch.split();
        System.out.println("Last Split time :" + stopWatch.getSplitTime() + " ms");
        System.out.println(stopWatch.toSplitString());
        System.out.println(stopWatch.toString());

    }

    public static void main(String[] args) throws InterruptedException {

        Stop1 stop = new Stop1();
        stop.testStopWatch1();
    }
}
