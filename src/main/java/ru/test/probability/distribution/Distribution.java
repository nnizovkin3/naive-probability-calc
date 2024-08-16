package ru.test.probability.distribution;

public interface Distribution<Variable> {
    double probability(Variable variable);
    double mean();
    double variance();
    Variable mod();
    default double standardDeviation() {
        return Math.sqrt(variance());
    }
}
