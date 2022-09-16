package jmh;

import java.util.Arrays;
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
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 3, time = 5)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class Sort {

    @Param(value = {"10000000", "20000000"})
    private int length;

    private String seed = "342dfkljio7342nmcvx092";

    @Benchmark
    public void testParallelSort(Blackhole blackhole) {
        String[] testData = new String[this.length];
        int seedLength = seed.length();
        for (int i = 0; i < this.length; i++) {
            int start = i % seedLength;
            testData[i] = seed.substring(start);
        }
        String a = "done";
        Arrays.parallelSort(testData);
        blackhole.consume(a);
    }

    @Benchmark
    public void testSort(Blackhole blackhole) {
        String[] testData = new String[this.length];
        int seedLength = seed.length();
        for (int i = 0; i < this.length; i++) {
            int start = i % seedLength;
            testData[i] = seed.substring(start);
        }
        String a = "done";
        Arrays.sort(testData);
        blackhole.consume(a);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Sort.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}

