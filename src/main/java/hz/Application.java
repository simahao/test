package hz;

import java.io.File;

public class Application {
    public static void main(String[] args) {
        String str = "a.b.c.d";
        System.out.println(str);
        str = str.replace(".", File.separator);
        System.out.println(str);
    }
}
