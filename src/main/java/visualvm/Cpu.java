package visualvm;

public class Cpu {


    /**
     * cpu 运行固定百分比
     *
     * @throws InterruptedException
     */
    public static void cpuFix() throws InterruptedException {
        // 80%的占有率
        int busyTime = 8;
        // 20%的占有率
        int idleTime = 2;
        // 开始时间
        long startTime = 0;

        while (true) {
            startTime = System.currentTimeMillis();

            while (System.currentTimeMillis() - startTime < busyTime) {
            }
            Thread.sleep(idleTime);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        cpuFix();
    }
}
