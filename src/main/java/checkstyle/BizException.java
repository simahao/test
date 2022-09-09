package checkstyle;

public class BizException extends Exception {

    public BizException(String code, String message) {
        super(message);
        System.out.println("code:" + code + " msg:" + message);
    }
}
