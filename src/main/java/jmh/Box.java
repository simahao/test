package jmh;

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
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Box {

    @Param(value = {"1000000", "5000000", "20000000"})
    private int length;

    // @Benchmark
    // public void testBox(Blackhole blackhole) {
    //     Integer tr = null;
    //     for (int i = 0; i < length; i++) {
    //         tr = i;
    //     }
    //     blackhole.consume(tr);
    // }

    // @Benchmark
    // public void testUnBox(Blackhole blackhole) {
    //     int tr = 0;
    //     for (Integer i = 0; i < length; i++) {
    //         tr = i.intValue();
    //     }
    //     blackhole.consume(tr);
    // }
    @Benchmark
    public void test1(Blackhole blackhole) {
        int tmp = 0;
        for (int i = 0; i < length; i++) {
            tmp = i;
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(Box.class.getSimpleName())
            .result("result.json")
            .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }

}
