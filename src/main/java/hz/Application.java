package hz;

public class Application {
    public static void main(String[] args) {
        String url = "http://abc:2222/sdfs/";
        url = url.replaceAll("http:", "");
        System.out.println(url);
    }
}
