package com.epam.mentoring.javacore.task1.model;

/**
 * @author Kaikenov Adilhan
 **/
public final class Dimension {

    private double length;
    private double width;
    private double height;

    public Dimension(double length, double width, double height) {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Negative values can not be used.");
        }

        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getLength() {
        return this.length;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "length=" + this.length +
                ", width=" + this.width +
                ", height=" + this.height +
                '}';
    }
}
