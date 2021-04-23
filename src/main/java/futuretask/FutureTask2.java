package futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTask2 {
    static class Connection {}
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
                    return createConnection();
                }

            };
            FutureTask<Connection> newTask = new FutureTask<Connection>(callable);
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
}
