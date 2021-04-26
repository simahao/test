package thread.state;

public class Yield {
    static class YieldTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                // yield change thread starte from running to runnable
                // schedule has no guarantee that other thread get cpu
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        YieldTask task = new YieldTask();
        Thread t1 = new Thread(task, "thread1");
        Thread t2 = new Thread(task, "thread2");

        t1.start();
        t2.start();
    }
}
