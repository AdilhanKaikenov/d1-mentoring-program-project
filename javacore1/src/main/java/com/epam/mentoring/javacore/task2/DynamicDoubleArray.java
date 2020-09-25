package com.epam.mentoring.javacore.task2;

import java.util.Arrays;

/**
 * @author Kaikenov Adilhan
 **/
public class DynamicDoubleArray {

    private static final int DEFAULT_CAPACITY = 10;

    private double[] array;

    private int size;

    public DynamicDoubleArray() {
        this.array = new double[DEFAULT_CAPACITY];
    }

    public DynamicDoubleArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity can not be negative. Capacity: " + initialCapacity);
        }

         this.array = new double[initialCapacity];
    }

    public void add(double item) {
        if (this.array.length == this.size) {
            this.array = Arrays.copyOf(this.array, this.size * 2);
        }
        this.array[this.size] = item;
        this.size++;
    }

    public void remove(int index) {
        for (int i = index; i < this.array.length - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.size--;
    }

    public double get(int index) {
        return this.array[index];
    }

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
