package br.com.ruhan.mock.model;

/**
 * Created by ruhan on 15/12/16.
 */
public class Value {

    private double value;

    public Value(final double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
