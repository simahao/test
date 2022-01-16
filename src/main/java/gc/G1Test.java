package gc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 3)
@Threads(3)
@Fork(1)
// 所有测试线程共享一个实例，测试有状态实例在多线程共享下的性能
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class G1Test {

    @Param(value = {"512", "1024"})
    private int sizeInBytes;

    // -Xmx=4M
    // 1024 * 0.6 = 615
    // -Xmx=128M
    // 32 * 0.6 = 20
    // @Param(value = {"615", "20"})
    @Param(value = {"300", "10"})
    private int numberOfObjects;

    @Benchmark
    public void allocate(Blackhole blackhole) {
        List<byte[]> junk = new ArrayList<>(numberOfObjects);
        for (int j = 0; j < numberOfObjects; j++) {
            junk.add(new byte[sizeInBytes]);
        }
        blackhole.consume(junk);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(G1Test.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
