package powermock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

/**
 * 模拟普通类
 * @author hz
 *
 */
public class RectangleTest {
    @Test
    public void testGetArea() {
        double expectArea = 100.0D;
        Rectangle rect = PowerMockito.mock(Rectangle.class);
        PowerMockito.when(rect.getArea()).thenReturn(expectArea);
        double actual = rect.getArea();
        assertEquals(expectArea, actual, 1E-6D);
    }

    @Test
    public void assumeTrueWithMessage() {

        System.out.println(System.getProperty("ENV"));
        assumeTrue("Assumption Failed !!!", "DEV".equals(System.getProperty("ENV")));
        System.out.println("Assumption passed !!!");
        assertEquals(3, 2 + 1);
    }
}
