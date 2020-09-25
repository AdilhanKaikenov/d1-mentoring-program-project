package com.epam.mentoring.javacore.task1.model;

/**
 * @author Kaikenov Adilhan
 **/
public class Product {

    private String name;

    private double weight;

    private double volume;

    public Product(String name, double weight, double volume) {
        this.name = name;
        this.weight = weight;
        this.volume = volume;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return this.volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.weight, this.weight) != 0) return false;
        if (Double.compare(product.volume, this.volume) != 0) return false;
        return this.name != null ? this.name.equals(product.name) : product.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = this.name != null ? this.name.hashCode() : 0;
        temp = Double.doubleToLongBits(this.weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + this.name + '\'' +
                ", weight=" + this.weight +
                ", volume=" + this.volume +
                super.toString() +
                '}';
    }
}
