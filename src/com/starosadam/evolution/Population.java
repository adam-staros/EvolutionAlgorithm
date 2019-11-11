package com.starosadam.evolution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

/**
 * Created by adam on 11.11.19.
 */
class Population {
    private static final int POPULATION_SIZE = 20;

    Individual fittest;
    Individual secondFittest;
    List<Individual> individuals = new ArrayList<>();

    void init(ToDoubleBiFunction<Double, Double> function) {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            individuals.add(new Individual(function));
        }
    }

    void selection() {
        List<Individual> copyOfIndividuals = new ArrayList<>(individuals);
        fittest = copyOfIndividuals.stream().max(Comparator.comparingDouble(Individual::getFitness)).get();
        copyOfIndividuals.remove(fittest);
        secondFittest = copyOfIndividuals.stream().max(Comparator.comparingDouble(Individual::getFitness)).get();
    }

    void replaceWeakestElementWithMutatedChild(Individual child) {
        individuals.add(child);
        final Individual weakest = individuals.stream().min(Comparator.comparingDouble(i -> i.fitness)).get();
        individuals.remove(weakest);
    }

    @Override
    public String toString() {
        return "Population{" +
                "fittest=" + fittest +
                ", secondFittest=" + secondFittest +
                ", individuals=" + individuals +
                '}';
    }
}
