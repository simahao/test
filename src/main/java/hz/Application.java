package hz;

public class Application {

    public static void main(String[] args) {
        double g = Double.parseDouble("4.6");
        double a = g;
        double b = a * 100;
        double c = 4.6 * 100;
        double d = 460;
        double e = d * 100;
        double f = a + a + a + a + a + a + a + a + a + a
                + a + a + a + a + a + a + a + a + a + a
                + a + a + a + a + a + a + a + a + a + a
                + a + a + a + a + a + a + a + a + a + a
                + a + a + a + a + a + a + a + a + a + a
                + a + a + a + a + a + a + a + a + a + a
                + a + a + a + a + a + a + a + a + a + a
                + a + a + a + a + a + a + a + a + a + a
                + a + a + a + a + a + a + a + a + a + a
                + a + a + a + a + a + a + a + a + a + a;
        System.out.println(g);
        System.out.printf("4.6       a = %.70f \n", a);
        System.out.printf("a * 100   b = %.100f \n", b);
        System.out.printf("4.6 * 100 c = %.100f \n", c);
        System.out.printf("460       d = %.100f \n", d);
        System.out.printf("d * 100   e = %.100f \n", e);
        System.out.printf("a + .+.a  f = %.100f \n", f);
    }
}
