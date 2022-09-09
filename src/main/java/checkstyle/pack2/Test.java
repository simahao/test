package checkstyle.pack2;

import checkstyle.BizException;

public class Test {

    public void test() throws BizException {
        throw new BizException("2201", "abc");
    }

    public static void main(String[] args) throws Exception {
        Test test = new Test();
        test.test();
    }
}
