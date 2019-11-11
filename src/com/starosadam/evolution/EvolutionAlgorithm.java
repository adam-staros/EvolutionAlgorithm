package com.starosadam.evolution;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleBiFunction;

/**
 * Created by adam on 11.11.19.
 */
public class EvolutionAlgorithm {

    private static Population population = new Population();
    private static final int NUMBER_OF_GENERATIONS = 100;
    private static final ToDoubleBiFunction<Double, Double> FUNCTION = (x, z) -> 6 * x - z;


    public static void main(String[] args) {
        population.init(FUNCTION);
        for (int i = 0; i < NUMBER_OF_GENERATIONS; i++) {
            population.selection();
            final Individual child = crossOver(population.fittest, population.secondFittest);
            final Individual mutatedChild = child.mutate(FUNCTION);
            System.out.println("Mutated child " + mutatedChild);
            population.replaceWeakestElementWithMutatedChild(mutatedChild);
            System.out.println("Generation: " + i + "\n" + population);
        }
    }

    static Individual crossOver(Individual best, Individual secondBest) {
        Random generator = new Random();
        List<Integer> integers = Arrays.asList(0, 1, 2);
        switch (generator.nextInt(integers.size())) {
            case 0:
                return best;
            case 1:
                return new Individual(best.x, secondBest.z);
            case 2:
                return secondBest;
            default:
                return null;
        }
    }
}
