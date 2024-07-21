package floattest;

public class FloatTest {
    public static void main(String[] args) {
        float v1= 932.525f;
        //这里先写24位，因为第23位是通过24位决定是否舍入
        //1110100100.10000110011001
        //进位之后保留23位有效数字
        //1110100100.1000011001101，这个二进制对应十进制932.525024
        //1000100011010010010000110011010
        //二进制表达，正数，第一位是0，指数8位，10001000=2^7+2^3=128+8=136
        //正数，第一位是0，所以返回值是31位，没有将0表达。取出8位作为指数,10001000=136，按照公式e=136-127=9
        //由于二进制的表达式，第一位永远是1，所以从第九位开始取出23位有效数字，并且前面添加一个1
        //1.1101001001000011001101*2^9=1110100100.1000011001101=932.525024
        System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(v1)));
        //因为932.525实际上已经是932.525024了，所以这里只打印了932.53
        System.out.printf("%.2f\n", v1);
        System.out.println(v1);

        float v2= 932.535f;
        //1110100100.100010001111010
        //进位之后保留23位有效数字
        //1110100100.10001000111101，这个二进制对应十进制932.534973
        //1000100011010010010001000111101
        //二进制表达，正数，第一位是0，指数8位，10001000=2^7+2^3=128+8=136
        //正数，第一位是0，所以返回值是31位，没有将0表达。取出8位作为指数,10001000=136，按照公式e=136-127=9
        //由于二进制的表达式，第一位永远是1，所以从第九位开始取出23位有效数字，并且前面添加一个1
        //1.110100100 10001000111101*2^9=1110100100.10001000111101
        System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(v2)));
        //因为932.535实际上已经是932.534973了，所以这里只打印了932.53
        System.out.printf("%.2f\n", v2);
        System.out.println(v2);
    }
}