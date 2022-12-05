package cache;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EhcacheTest {

    private CacheHelper cache = new CacheHelper();

    public int getSquareValueOfNumber(int input) {
        if (cache.getSquareNumCacheFromCacheManager().containsKey(input)) {
            return cache.getSquareNumCacheFromCacheManager().get(input);
        }

        int squareValue = (int) Math.pow(input, 2);

        cache.getSquareNumCacheFromCacheManager().put(input, squareValue);

        return squareValue;
    }


    @Test
    public void test() {
        for (int i = 10; i < 15; i++) {
            assertFalse(cache.getSquareNumCacheFromCacheManager().containsKey(i));
            getSquareValueOfNumber(i);
        }

        for (int i = 10; i < 15; i++) {
            assertTrue(cache.getSquareNumCacheFromCacheManager().containsKey(i));
            System.out.println("Square value of " + i + " is: " + getSquareValueOfNumber(i));
        }
    }
}
