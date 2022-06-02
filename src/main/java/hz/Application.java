package hz;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Application {
    public static void main(String[] args) {
        BigDecimal x = new BigDecimal("2.78");
        BigDecimal y = new BigDecimal("0.008853");
        BigDecimal z = x.multiply(y);
        BigInteger i = new BigInteger("12345678901234567890");
        System.out.println(i);
    }
}
