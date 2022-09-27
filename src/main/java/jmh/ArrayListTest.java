package jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 1)
@Measurement(iterations = 3)
@Fork(value = 1, jvmArgsAppend = {"-Xms12g", "-Xmx12g", "-server"})
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
public class ArrayListTest {

    @Param(value = {"5000000"})
    private int size;


    @Param(value = {"200"})
    private int count;

    @Benchmark
    public void testBigArray() {
        for (int i = 0; i < count; i++) {
            List<Order> order = new ArrayList<>(5000000);
        }
    }

    @Benchmark
    public void testBigArrayNotInit() {
        for (int i = 0; i < count; i++) {
            List<Order> order = new ArrayList<Order>();
        }
    }
    // @Benchmark
    // public void listNotInit(Blackhole bh) {
    //     List<Order> list = new ArrayList<>();
    //     for (int i = 0; i < size; i++) {
    //         Order order = getOrder(i);
    //         list.add(order);
    //     }
    //     bh.consume(list);
    // }

    // @Benchmark
    // public void listInit(Blackhole bh) {
    //     List<Order> list = new ArrayList<>(size);
    //     for (int i = 0; i < size; i++) {
    //         Order order = getOrder(i);
    //         list.add(order);
    //     }
    //     bh.consume(list);
    // }


    private static Order getOrder(int i) {
        return new Order("20220908", new BigDecimal(i), new BigDecimal(i), "000" + i, "0000000" + i, "arbiContractId" + i,
                "contractId", new BigDecimal(i), "orderSort", new BigDecimal(i), new BigDecimal(i), new BigDecimal(i), BigDecimal.TEN, "1", "1", "1",
                "attr", "0", "10:33:33", "0", new BigDecimal(i), new BigDecimal(i), "0", "1", new BigDecimal(i), "0");
    }

    public static void main(String[] args) throws Exception{
        Options options = new OptionsBuilder()
                .include(ArrayListTest.class.getName())
                .build();
        new Runner(options).run();
    }

}
