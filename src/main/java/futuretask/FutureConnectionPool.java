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
        private static int i = 0;
        public Connection() {
            System.out.println("create one connection:" + i++);
        }
    }

    private ConcurrentHashMap<String, FutureTask<Connection>> connectionPool =
        new ConcurrentHashMap<String, FutureTask<Connection>>();

    public Connection getConnection(String key) throws InterruptedException, ExecutionException {
        FutureTask<Connection> connectionTask = connectionPool.get(key);
        if (connectionTask != null) {
            return connectionTask.get();
        } else {
            Callable<Connection> callable = new Callable<Connection>() {

                @Override
                public Connection call() throws Exception {
                    Thread.sleep(300);
                    return createConnection();
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
    private Connection createConnection() {
        return new Connection();
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final FutureConnectionPool ft2 = new FutureConnectionPool();

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Random random = new Random();
                        int ri = random.nextInt(10);
                        ft2.getConnection(String.valueOf(ri));
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
