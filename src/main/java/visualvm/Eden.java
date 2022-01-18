package visualvm;

public class Eden {

    public static final int OUTOFMEMORY = 200000000;

    private String oom;

    private int length;

    private StringBuilder tempOom = new StringBuilder();

    public Eden(int len) {

        this.length = len;
        int i = 0;
        while (i < len) {
            i++;
            try {
                tempOom.append("a");
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                break;
            }
        }
        this.oom = tempOom.toString();
    }

    public String getOom() {
        return oom;
    }

    public int getLength() {
        return length;
    }

    public static void main(String[] args) {
        Eden eden = new Eden(OUTOFMEMORY);
        System.out.println(eden.getOom().length());
    }
}
