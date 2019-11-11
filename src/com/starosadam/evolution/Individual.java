package com.starosadam.evolution;

import java.util.Random;
import java.util.function.ToDoubleBiFunction;

/**
 * Created by adam on 11.11.19.
 */
class Individual {

    double x;
    double z;
    double fitness;

    private static final double LOWER_POPULATION_LIMIT = -50;
    private static final double UPPER_POPULATION_LIMIT = 50;

    private Random generator = new Random();

    Individual(ToDoubleBiFunction<Double, Double> function) {
        this.x = random(LOWER_POPULATION_LIMIT, UPPER_POPULATION_LIMIT);
        this.z = random(LOWER_POPULATION_LIMIT, UPPER_POPULATION_LIMIT);
        this.fitness = calculateFitness(function);
    }

    Individual(double x, double z) {
        this.x = x;
        this.z = z;
    }

    private Individual(double x, double z, double fitness) {
        this.x = x;
        this.z = z;
        this.fitness = fitness;
    }

    private double calculateFitness(ToDoubleBiFunction<Double, Double> function) {
        return function.applyAsDouble(x, z);
    }

    Individual mutate(ToDoubleBiFunction<Double, Double> function) {
        double newX = getNewDouble(x);
        newX = checkIfNewValueIsBetweenDomain(newX);
        double newZ = getNewDouble(z);
        newZ = checkIfNewValueIsBetweenDomain(newZ);
        final double newFitness = calculateFitness(function);
        return new Individual(newX, newZ, newFitness);
    }

    private double random(double lowerLimit, double upperLimit) {
        return lowerLimit + (upperLimit - lowerLimit) * generator.nextDouble();
    }

    private double getNewDouble(double value) {
        return value * random(-0.02, 0.02) + value;
    }

    private double checkIfNewValueIsBetweenDomain(double newX) {
        if (newX > 50) {
            newX = 50;
        }
        if (newX < -50) {
            newX = -50;
        }
        return newX;
    }

    double getFitness() {
        return fitness;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "x=" + x +
                ", z=" + z +
                '}';
    }
}
