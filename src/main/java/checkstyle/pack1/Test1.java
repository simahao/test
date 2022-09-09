package checkstyle.pack1;

import checkstyle.BizException;

public class Test1 {

    public void test() throws BizException {
        throw new BizException("1110", "abc");
    }

    public static void main(String[] args) throws Exception {
        Test1 test = new Test1();
        test.test();
    }
}
