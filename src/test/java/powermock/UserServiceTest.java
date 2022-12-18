package powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(PowerMockRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @InjectMocks
    private UserService userService2;

    /**
     * spy模式下，when...thenReturn是执行原有代码的
     */
    @Test
    public void testGetUserCount1() {

        long expected = 100L;
        UserService us = PowerMockito.spy(new UserService());
        PowerMockito.when(us.getUserCount()).thenReturn(expected);

        long actual = us.getUserCount();

        Assert.assertEquals(expected, actual);
    }

    /**
     * spy模式下, doReturn...when是不执行原有代码的
     */
    @Test
    public void testGetUserCount2() {
        long expected = 100L;
        UserService us = PowerMockito.spy(new UserService());
        PowerMockito.doReturn(expected).when(us).getUserCount();
        long actual = us.getUserCount();
        Assert.assertEquals(expected, actual);
    }


    /**
     * mock方式下，when...thenReturn和doReturn...when是没有区别的，都不执行getUserCount原有代码
     */
    @Test
    public void testGetUserCount3() {

        long expected = 100L;
        UserService us = PowerMockito.mock(UserService.class);
        PowerMockito.when(us.getUserCount()).thenReturn(expected);

        long actual = us.getUserCount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetUserCount4() {
        long expected = 100L;
        UserService us = PowerMockito.mock(UserService.class);
        PowerMockito.doReturn(expected).when(us).getUserCount();
        long actual = us.getUserCount();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetUserLimit() {
        Long expected = 1000L;
        ReflectionTestUtils.setField(userService, "userLimit", expected);
        Long actual = userService.getUserLimit();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getGetUserLimit2() {
        Long expected = 1000L;
        Whitebox.setInternalState(userService2, "userLimit", expected);
        Long actual = userService2.getUserLimit();
        Assert.assertEquals(expected, actual);
    }
}
