package mock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * 模拟final类测试
 * @author hz
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Circle.class})
public class CircleTest {

    @Test
    public void testGetArea() {
        double expectArea = 3.14D;
        Circle circle = PowerMockito.mock(Circle.class);
        PowerMockito.when(circle.getArea()).thenReturn(expectArea);
        double actual = circle.getArea();
        assertEquals(expectArea, actual, 1E-6D);
    }

}
