package stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Warmup(iterations = 1)
@Measurement(iterations = 3)
@Fork(value = 1, jvmArgsAppend = {"-Xms5g", "-Xmx5g", "-server"})
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
public class PerfTest {

    @Param(value = {"500000"})
    private int size;

    List<Product> product = new ArrayList<>(size);

    @Setup(value = Level.Trial)
    public void prepare() {
        for (int i = 0; i < size; i++) {
            product.add(new Product(i, "a"));
        }
    }

    @Benchmark
    public void testFor() {
        long sum = 0;
        for (int i = 0; i < size; i++) {
            sum += product.get(i).getId();
        }
        // System.out.println("sum: " + sum);
    }

    @Benchmark
    public void testStream() {
        long sum = product.stream().collect(Collectors.summingLong(Product::getId));
        // System.out.println("sum stream: " + sum);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(PerfTest.class.getName())
                .build();
        new Runner(options).run();
    }
}
