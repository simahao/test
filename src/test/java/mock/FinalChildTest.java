package mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FinalChild.class)
public class FinalChildTest {
    @Mock
    private FinalParent parent;

    @Test
    public void testDoSomething() {
        Mockito.when(parent.isEven(0)).thenReturn(true);
        FinalChild child = new FinalChild();
        assertEquals(0, child.doSomething(0));
    }
}
