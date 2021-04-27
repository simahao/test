package thread.state;

public class Join {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " start");
                System.out.println(Thread.currentThread().getName() + " end");
            });

            t.start();

            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
