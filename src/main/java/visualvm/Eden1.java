package visualvm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Eden1
 * @author hz
 */
public class Eden1 {
    public static void main(String[] args) throws InterruptedException {
        // string();
        map1();
    }

    private static void string() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        long num = 0;
        while (true) {
            sb.append("a");
            if (num++ == 10000) {
                Thread.sleep(1);
                num = 0;
            }
        }
    }

    private static void ygc() throws InterruptedException {
        while (true) {
            byte[] mem = new byte[300 * 1024];
            Thread.sleep(1);
        }
    }

    private static void map1() {
        Map<String, Integer> map = new HashMap<>();

        while (true) {
            map.put(new Random().toString(), new Random().nextInt());
        }
    }
}
