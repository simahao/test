package checkstyle.pack1;

import checkstyle.BizException;

public class Test2 {
    public void test() throws BizException {
        throw new BizException("1120", "abc");
    }

    public static void main(String[] args) throws Exception {
        Test2 test = new Test2();
        test.test();
    }
}
