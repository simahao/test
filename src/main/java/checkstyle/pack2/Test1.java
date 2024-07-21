package checkstyle.pack2;

import checkstyle.BizException;

public class Test1 {

    public void test() throws BizException {
        throw new BizException("2210", "abc");
    }

    public static void main(String[] args) throws Exception {
        Test1 test = new Test1();
        test.test();
    }
}
