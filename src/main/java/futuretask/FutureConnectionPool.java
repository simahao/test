package futuretask;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureConnectionPool {

    static class Connection {

        Connection(Integer key) {
            System.out.println("create one connection:" + key);
        }
    }

    private ConcurrentHashMap<Integer, FutureTask<Connection>> connectionPool =
        new ConcurrentHashMap<>();

    public Connection getConnection(Integer key) throws InterruptedException, ExecutionException {
        FutureTask<Connection> connectionTask = connectionPool.get(key);
        if (connectionTask != null) {
            System.out.println("get connection from pool, key:" + key);
            return connectionTask.get();
        } else {
            Callable<Connection> callable = new Callable<Connection>() {

                @Override
                public Connection call() throws Exception {
                    // Thread.sleep(300);
                    return createConnection(key);
                }
            };

            FutureTask<Connection> newTask = new FutureTask<>(callable);
            connectionTask = connectionPool.putIfAbsent(key, newTask);
            if (connectionTask == null) {
                connectionTask = newTask;
                connectionTask.run();
            }
            return connectionTask.get();
        }
    }

    private Connection createConnection(Integer key) {
        return new Connection(key);
    }

    public static void main(String[] args) {

        final FutureConnectionPool ft = new FutureConnectionPool();
        final Object lock = new Object();
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Random random = new Random();
                    int ri = random.nextInt(5);
                    synchronized (lock) {
                        ft.getConnection(ri);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();

        try {
            // check child thread whether finish work every 1 second
            while (!executor.isTerminated()) {
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
