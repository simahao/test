package thread.state;

public class SleepAndWait {

    public synchronized void sleepTest() {

        System.out.println("sleep start");
        try {
            // sleep don't release monitor lock
            // so other thread can't get lock when this thread sleep
            // outcome will be [start end] [start end] [start end]
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep end");
    }

    public synchronized void waitTest() {

        System.out.println("wait start");
        try {
            // wait release monitor lock
            // other thread will get lock when this thread invoke wait
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("wait end");
    }

    public static void main(String[] args) {

        final SleepAndWait sw1 = new SleepAndWait();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sw1.sleepTest();
                }
            }).start();
        }

        // wait sleep test for finishing
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------");

        final SleepAndWait sw2 = new SleepAndWait();

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sw2.waitTest();
                }}).start();
        }
    }
}
