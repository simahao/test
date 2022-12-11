package powermock;


import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    @Test
    public void testSize() {
        Integer expected = 100;
        List<?> list = PowerMockito.mock(List.class);
        PowerMockito.when(list.size()).thenReturn(expected);
        Integer actual = list.size();
        Assert.assertEquals(expected, actual);
    }

    /**
     * 通过thenThrow处理异常
     */
    @Test
    public void testGet1() {
        int index = -1;
        List<Integer> list = PowerMockito.mock(List.class);
        PowerMockito.when(list.get(index)).thenThrow(new IndexOutOfBoundsException());
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.get(index));
    }

    /**
     * 通过try catch处理异常
     */
    @Test
    public void testGet2() {
        int index = -1;
        List<Integer> list = PowerMockito.mock(List.class);
        try {
            list.get(index);
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }

    /**
     * 通过expected处理异常
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void tesetGet3() {
        int index = -1;
        List<Integer> list = PowerMockito.mock(List.class);
        PowerMockito.when(list.get(index)).thenThrow(new IndexOutOfBoundsException());
        list.get(index);
    }

    /**
     * 测试应答
     */
    @Test
    public void testGet4() {
        int index = 1;
        Integer expected = 100;
        List<Integer> mockList = PowerMockito.mock(List.class);
        PowerMockito.when(mockList.get(index)).thenAnswer(invocation -> {
            Integer value = invocation.getArgument(0);
            return value * 100;
        });
        Integer actual = mockList.get(index);
        Assert.assertEquals(expected, actual);
    }

    /**
     * 测试调用真实方法
     */
    @Test
    public void testGet5() {
        int index = 0;
        Integer expected = 100;
        List<Integer> list = new ArrayList<>();
        list.add(expected);
        List<Integer> spyList = PowerMockito.spy(list);
        PowerMockito.when(spyList.get(index)).thenCallRealMethod();
        Integer actual = spyList.get(index);
        Assert.assertEquals(expected, actual);
    }
}
