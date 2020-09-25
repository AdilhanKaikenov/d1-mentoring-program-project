package com.epam.mentoring.java8.task5;

import com.epam.mentoring.java8.task5.generator.ItemGenerator;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 10)
@Fork(1)
@Measurement(iterations = 10)
public class CustomCollectorPerformanceTest {

    @Benchmark
    public void testParallelStream() {
        Supplier<Stream<A>> stream = this.createStreamOfItems(10_000);
        Set<A> result = stream.get()
                .parallel()
                .peek(item -> item.add(item.getNumber() * 259 * 39))
                .peek(item -> item.cut(1))
                .collect(CustomCollector.toCustomCollector());
    }

    @Benchmark
    public void testStream() {
        Supplier<Stream<A>> stream = this.createStreamOfItems(10_000);
        Set<A> result = stream.get()
                .peek(item -> item.add(item.getNumber() * 259 * 39))
                .peek(item -> item.cut(1))
                .collect(CustomCollector.toCustomCollector());
    }

    private Supplier<Stream<A>> createStreamOfItems(final int itemAmount) {
        final List<A> items = new ArrayList<>();
        for (int i = 0; i < itemAmount; i++) {
            items.add(ItemGenerator.generateItem());
        }

        return items::stream;
    }

    // Run Benchmark
    public static void main(String[] args) throws RunnerException, IOException {
        Main.main(args);
    }
}
