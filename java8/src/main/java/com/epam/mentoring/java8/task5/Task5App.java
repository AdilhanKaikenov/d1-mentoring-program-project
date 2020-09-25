package com.epam.mentoring.java8.task5;

import com.epam.mentoring.java8.task5.generator.ItemGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Kaikenov Adilhan
 **/
public class Task5App {

    public static void main(String[] args) {

        final List<A> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add(ItemGenerator.generateItem());
        }

//        Make stream from the collection of A’s instances.
        final Supplier<Stream<A>> itemsStreamSupplier = items::stream;

//        Collectors are needed for different ways of accumulating the elements of a Stream.

//        1. Implement Collector interface
//        2. Don’t forget the final transformation
//        3. Try to use Characteristics for the optimization purpose.

        Set<A> result = itemsStreamSupplier.get()
                .peek(item -> item.add(item.getNumber() * 259 * 39))
                .peek(item -> item.cut(1))
                .collect(CustomCollector.toCustomCollector());

//        4. Add tests for your collector.
//        Test your Collector with the parallel streams for significant amount of items in stream.

        // See : com.epam.mentoring.java8.task5.CustomCollectorPerformanceTest
    }
}
