package edu.fmi.ai.geneticalgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by vasil on 11/14/15.
 */
public class Population {
    private List<PopulationMember> population;
    private final int maxSize = 10;
    private final int minSize = 5;
    private final Random rand;

    public Population() {
        this.population = new ArrayList<>();
        rand = new Random();
    }

    public void addToPopulation(boolean[] unit, int unitValue) {
        PopulationMember populationMember = new PopulationMember(unit, unitValue);
        population.add(populationMember);
        Collections.sort(population);
        if(population.size() > maxSize) {
            population.remove(population.size() - 1);
        }
    }

    public PopulationMember top() {
        return population.get(0);
    }

    public PopulationMember getTopMember() {
        int index = rand.nextInt(minSize);
        return population.get(index);
    }
}
