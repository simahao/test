package thread.state;

public class Notify {
    public synchronized void testNotify() {
        System.out.println(Thread.currentThread().getName() + " start");
        try {
            wait();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }

    public static void main(String[] args) {

        final Notify nf = new Notify();

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    nf.testNotify();
                }
            }).start();
        }

        // only notify thread1
        synchronized (nf) {
            nf.notify();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // nofify other four threads
        synchronized (nf) {
            nf.notifyAll();
        }
    }
}
