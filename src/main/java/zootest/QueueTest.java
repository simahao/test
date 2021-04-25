package zootest;

import java.util.concurrent.LinkedBlockingQueue;

public class QueueTest {
    public LinkedBlockingQueue<Integer> q = new LinkedBlockingQueue<>();

    public void queue() {
        try {
            q.take();
            System.out.println("released");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rel() {
        q.add(5);
    }

    public static void main(String[] args) throws Exception {
        QueueTest obj = new QueueTest();
        obj.queue();
        obj.rel();
    }
}
