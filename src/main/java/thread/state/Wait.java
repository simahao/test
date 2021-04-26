package thread.state;

public class Wait {
    // wait need monitor lock, syncronized keyword could get monitor lock
    public synchronized void testWait() {
        System.out.println("start");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        final Wait w = new Wait();
        new Thread(new Runnable() {
            @Override
            public void run() {
                w.testWait();
            }
        }).start();
    }
}
