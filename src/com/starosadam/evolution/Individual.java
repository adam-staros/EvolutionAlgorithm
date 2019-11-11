package com.starosadam.evolution;

import java.util.Random;

/**
 * Created by adam on 11.11.19.
 */
public class Individual {

    double x;
    double z;


    private static final double LOWER_POPULATION_LIMIT = -50;
    private static final double UPPER_POPULATION_LIMIT = 50;

    public void init(){
        Random generator = new Random();

        this.x = LOWER_POPULATION_LIMIT + (UPPER_POPULATION_LIMIT - LOWER_POPULATION_LIMIT) * generator.nextDouble();
        this.z = LOWER_POPULATION_LIMIT + (UPPER_POPULATION_LIMIT - LOWER_POPULATION_LIMIT) * generator.nextDouble();
    }

}
