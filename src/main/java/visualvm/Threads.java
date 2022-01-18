package visualvm;

public class Threads extends Thread {
    public static void main(String[] args) {

        Threads mt1 = new Threads("Thread a");
        Threads mt2 = new Threads("Thread b");

        mt1.setName("My-Thread-1 ");
        mt2.setName("My-Thread-2 ");

        mt1.start();
        mt2.start();
    }

    public Threads(String name) {
    }

    public void run() {
        while (true) {
        }
    }
}
