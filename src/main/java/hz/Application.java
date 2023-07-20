package hz;

public class Application {

    public static void main(String[] args) {
        double a = 4.6;
        double b = a * 100;
        double c = 4.6 * 100;
        double f = a + a + a + a + a + a + a + a + a + a;
                // + a + a + a + a + a + a + a + a + a + a
                // + a + a + a + a + a + a + a + a + a + a
                // + a + a + a + a + a + a + a + a + a + a
                // + a + a + a + a + a + a + a + a + a + a
                // + a + a + a + a + a + a + a + a + a + a
                // + a + a + a + a + a + a + a + a + a + a
                // + a + a + a + a + a + a + a + a + a + a
                // + a + a + a + a + a + a + a + a + a + a
                // + a + a + a + a + a + a + a + a + a + a;
        System.out.printf("4.6       a = %.18f \n", a);
        System.out.printf("a * 100   b = %.18f \n", b);
        System.out.printf("4.6 * 100 c = %.18f \n", c);
        System.out.printf("a + .+.a  f = %.18f \n", f);
    }
}
