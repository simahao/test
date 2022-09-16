package jmh;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@Warmup(iterations = 3)
@Measurement(iterations = 3)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
public class ArrayListTest {

    private static int size = 10000000;

    /*@Benchmark
    public void stringList_notinit(Blackhole bh) {
        String str = "str";
        String bhStr = "done";
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(str);
        }
        bh.consume(bhStr);
    }

    @Benchmark
    public void stringList_init(Blackhole bh) {
        String str = "str";
        String bhStr = "done";
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(str);
        }
        bh.consume(bhStr);
    }

    @Benchmark
    public void stringList_init1W(Blackhole bh) {
        String str = "str";
        String bhStr = "done";
        List<String> list = new ArrayList<>(10000);
        for (int i = 0; i < size; i++) {
            list.add(str);
        }
        bh.consume(bhStr);
    }

    @Benchmark
    public void stringList_init10W(Blackhole bh) {
        String str = "str";
        String bhStr = "done";
        List<String> list = new ArrayList<>(100000);
        for (int i = 0; i < size; i++) {
            list.add(str);
        }
        bh.consume(bhStr);
    }*/

    @Benchmark
    public void orderList_notinit(Blackhole bh) {
        Order order = getOrder(1L);
        String bhStr = "done";
        List<Order> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(order);
        }
        bh.consume(bhStr);
    }

    /*@Benchmark
    public void orderList_init(Blackhole bh) {
        Order order = getOrder(1L);
        String bhStr = "done";
        List<Order> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(order);
        }
        bh.consume(bhStr);
    }*/

    /*@Benchmark
    public void orderList_init1W(Blackhole bh) {
        Order order = getOrder(1L);
        String bhStr = "done";
        List<Order> list = new ArrayList<>(10000);
        for (int i = 0; i < size; i++) {
            list.add(order);
        }
        bh.consume(bhStr);
    }*/

    /*@Benchmark
    public void orderList_init10W(Blackhole bh) {
        Order order = getOrder(1L);
        String bhStr = "done";
        List<Order> list = new ArrayList<>(100000);
        for (int i = 0; i < size; i++) {
            list.add(order);
        }
        bh.consume(bhStr);
    }*/

    private static Order getOrder(long size) {
        Order order = new Order();
        String str = String.valueOf(size);
        BigDecimal bigDecimal = BigDecimal.valueOf(size);
        order.setsProp1(str);
        order.setsProp2(str);
        order.setsProp3(str);
        order.setsProp4(str);
        order.setsProp5(str);
        order.setBigDecimal1(bigDecimal);
        order.setBigDecimal2(bigDecimal);
        order.setBigDecimal3(bigDecimal);
        order.setBigDecimal4(bigDecimal);
        order.setBigDecimal5(bigDecimal);
        order.setBigDecimal6(bigDecimal);
        order.setBigDecimal7(bigDecimal);
        order.setBigDecimal8(bigDecimal);
        order.setBigDecimal9(bigDecimal);
        order.setBigDecimal10(bigDecimal);
        order.setBigDecimal11(bigDecimal);
        order.setBigDecimal12(bigDecimal);
        order.setBigDecimal13(bigDecimal);
        order.setBigDecimal14(bigDecimal);
        order.setBigDecimal15(bigDecimal);
        return order;
    }

    public static void main(String[] args) throws Exception{
        Options options = new OptionsBuilder()
                .include(ArrayListTest.class.getName())
                .build();
        new Runner(options).run();
    }

}
