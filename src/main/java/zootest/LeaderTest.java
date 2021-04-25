package zootest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.google.common.collect.Lists;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

public class LeaderTest {
    private static final int CLIENT_QTY = 2;

    private static final String PATH = "/leader";

    private static final String URL = "172.24.177.38:2181,172.24.177.39:2181,172.24.177.30:2181";
    // private static final String URL = "192.168.128.128:2181";

    public static void main(String[] args) throws Exception {
        // all of the useful sample code is in ExampleClient.java
        // String log4jConfPath ="src/main/resources/log4j.properties";
        // PropertyConfigurator.configure(log4jConfPath);
        System.out.println("Create " + CLIENT_QTY
                + " clients, have each negotiate for leadership and then wait a random number of seconds before letting another leader election occur.");

        List<CuratorFramework> clients = Lists.newArrayList();
        List<Client> examples = Lists.newArrayList();
        try {
            for (int i = 0; i < CLIENT_QTY; ++i) {
                CuratorFramework client = CuratorFrameworkFactory.newClient(URL, new ExponentialBackoffRetry(1000, 3));
                clients.add(client);

                Client example = new Client(client, PATH, "Client #" + i);
                examples.add(example);

                client.start();
                example.start();
            }

            System.out.println("Press enter/return to quit\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } finally {
            System.out.println("Shutting down...");
            for (Client exampleClient : examples) {
                CloseableUtils.closeQuietly(exampleClient);
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
        }
    }
}
