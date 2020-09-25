package com.epam.mentoring.java8.task3;

import java.util.Objects;
import java.util.function.Function;

/**
 * Functional interface ThreeFunction takes three arguments and produce result.
 *
 * @param <F> the type of the first argument to the function
 * @param <S> the type of the second argument to the function
 * @param <O> the type of the third argument to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface ThreeFunction<F, S, O, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param f the first function argument
     * @param s the second function argument
     * @param o the third function argument
     * @return the function result
     */
    R apply(F f, S s, O o);

    /**
     * Returns a composed function that first applies this function to its input
     * and then applies the {@code after} function to the result.
     *
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     * @param <T> the type of output of the {@code after} function, and of the
     *          composed function
     * @param after  the function to apply after this function is applied
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    default <T> ThreeFunction<F, S, O, T> andThen(Function<? super R, ? extends T> after) {
        Objects.requireNonNull(after);
        return (F f, S s, O o) -> after.apply(apply(f, s, o));
    }
}
