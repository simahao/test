package visualvm;

public class Eden {

    public static final int OUTOFMEMORY = 200000000;


    private StringBuilder oom = new StringBuilder();

    public Eden(int len) {

        int i = 0;
        while (i < len) {
            i++;
            try {
                oom.append("a");
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public long getLength() {
        return oom.length();
    }

    public static void main(String[] args) {
        Eden eden = new Eden(OUTOFMEMORY);
        System.out.println("string length:" + eden.getLength());
    }
}
