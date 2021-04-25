package thread.stop;

import java.util.concurrent.CountDownLatch;

public class LatchStop {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " start");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                    System.out.println(Thread.currentThread().getName() + " end");
                }
            }.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread is exiting with CountDownLatch for waiting child thread");
    }
}
