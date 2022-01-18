package visualvm;



public class DeadLock {

    class Resource {
        private int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    class LockThread0 extends Thread {

        private Resource r1;

        private Resource r2;

        LockThread0(Resource r1, Resource r2) {
            this.r1 = r1;
            this.r2 = r2;
        }

        @Override
        public void run() {
            int j = 0;
            while (true) {
                synchronized (r2) {
                    System.out.println("The second thread got r2's lock  " + j);
                    synchronized (r1) {
                        System.out.println("The second thread got r1's lock" + j);
                    }
                }
                j++;
            }
        }
    }

    class LockThread1 extends Thread {

        private Resource r1;

        private Resource r2;

        LockThread1(Resource r1, Resource r2) {
            this.r1 = r1;
            this.r2 = r2;
        }

        @Override
        public void run() {
            int j = 0;
            while (true) {
                synchronized (r1) {
                    System.out.println("The first thread got r1's lock " + j);
                    synchronized (r2) {
                        System.out.println("The first thread got r2's lock  " + j);
                    }
                }
                j++;
            }
        }
    }
    public static void main(String[] args) {

        DeadLock dl = new DeadLock();

        DeadLock.Resource r0 = dl.new Resource();
        DeadLock.Resource r1 = dl.new Resource();

        // Resource r0 = new Resource();
        // Resource r1 = new Resource();

        Thread myTh0 = dl.new LockThread0(r1, r0);
        Thread myTh1 = dl.new LockThread1(r1, r0);

        myTh0.setName("DeadLock-0 ");
        myTh1.setName("DeadLock-1 ");

        myTh0.start();
        myTh1.start();
    }
}
