package com.epam.mentoring.java8.task5;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author Kaikenov Adilhan
 **/
public class CustomCollector<T> implements Collector<T, List<T>, Set<T>> {

//    supplier() is used to create accumulator object(s).
//    For parallel stream, supplier may be used to create accumulator objects (one per thread)
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

//   accumulator() is used for adding a new element to an existing accumulator object
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

//   combiner() is used for merging two accumulators together:
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list2.addAll(list1);
            return list1;
        };
    }

    // finisher() is used for converting an accumulator to final result type
    @Override
    public Function<List<T>, Set<T>> finisher() {
        return HashSet::new;
    }

//    characteristics() used for internal optimizations

//    Characteristics.CONCURRENT -  can be modified from many threads concurrently
//    Characteristics.UNORDERED - result of the collect operation doesn’t depend on the order of stream elements
//    Characteristics.IDENTITY_FINISH - Function returned by finisher() is identity and we may cast accumulator object
//      straight to the result type
    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Collector.Characteristics.UNORDERED);
    }

    public static <T> CustomCollector<T> toCustomCollector() {
        return new CustomCollector<>();
    }
}
