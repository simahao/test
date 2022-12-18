package powermock;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
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


    /**
     * 测试mockito 参数匹配器
     * anyInt
     */
    @Test
    public void testGet6() {
        Integer expected = 100;
        List<Integer> mockList = PowerMockito.mock(List.class);
        PowerMockito.when(mockList.get(Mockito.anyInt())).thenReturn(expected);
        Integer actual = mockList.get(100);
        Assert.assertEquals(expected, actual);
        actual = mockList.get(200);
        Assert.assertEquals(expected, actual);
    }

    /**
     * 参数匹配器
     * eq
     */
    @Test
    public void testGet7() {
        int index = 1;
        Integer expected = 100;
        List<Integer> mockList = PowerMockito.mock(List.class);
        PowerMockito.when(mockList.get(Mockito.eq(index))).thenReturn(expected);
        Integer actual = mockList.get(100);
        Assert.assertNotEquals(expected, actual);
        actual = mockList.get(index);
        Assert.assertEquals(expected, actual);
    }

    /**
     * 参数匹配器
     * geq lt
     */
    @Test
    public void testGet8() {
        int index = 1;
        Integer expected = 100;
        List<Integer> mockList = PowerMockito.mock(List.class);
        PowerMockito.when(mockList.get(AdditionalMatchers.geq(0))).thenReturn(expected);
        PowerMockito.when(mockList.get(AdditionalMatchers.lt(0))).thenThrow(new IndexOutOfBoundsException());
        Integer actual = mockList.get(index);
        Assert.assertEquals(expected, actual);
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
            mockList.get(-1);
        });
    }

    /**
     * 验证调用真实方法
     * times,atLeastOnce,atLeast,only,atMostOnce,atMost
     */
    @Test
    public void testGet9() {
        List<Integer> mockList = PowerMockito.mock(List.class);
        PowerMockito.doNothing().when(mockList).clear();
        mockList.clear();
        Mockito.verify(mockList, Mockito.times(1)).clear();
    }

    /**
     * 测试调用顺序
     */
    @Test
    public void testAdd() {
        List<Integer> mockList = PowerMockito.mock(List.class);
        PowerMockito.doReturn(true).when(mockList).add(Mockito.anyInt());
        mockList.add(1);
        mockList.add(2);
        mockList.add(3);
        InOrder inOrder = Mockito.inOrder(mockList);
        inOrder.verify(mockList).add(1);
        inOrder.verify(mockList).add(2);
        inOrder.verify(mockList).add(3);
    }


    @Test
    public void testArgumentCaptor() {
        Integer[] expecteds = new Integer[] {1, 2, 3};
        List<Integer> mockList = PowerMockito.mock(List.class);
        PowerMockito.doReturn(true).when(mockList).add(Mockito.anyInt());
        for (Integer expected: expecteds) {
            mockList.add(expected);
        }
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.verify(mockList, Mockito.times(3)).add(argumentCaptor.capture());
        Integer[] actuals = argumentCaptor.getAllValues().toArray(new Integer[0]);
        Assert.assertEquals(expecteds, actuals);
    }
}
