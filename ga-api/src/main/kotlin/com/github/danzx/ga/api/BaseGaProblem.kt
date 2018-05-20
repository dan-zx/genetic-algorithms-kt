/*
 * Copyright 2018 Daniel Pedraza-Arcega
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.danzx.ga.api

import com.github.danzx.ga.api.Population.Half.BOTTOM
import com.github.danzx.ga.api.Population.Half.TOP
import com.github.danzx.ga.api.util.loggerFor

/**
 * The base of a Genetic Algorithm and implements the following operators:
 * - Selection
 * - Crossover
 * - Mutation
 * - Elitism
 *
 * Problems must extend this class and implement crossover and mutation operators.
 *
 * @constructor requires a set of parameter to start the process.
 *
 * @author Daniel Pedraza-Arcega
 */
abstract class BaseGaProblem<C: Chromosome<*>>(private val parameters: GaParameters<Population<C>>) {

    private val logger = loggerFor<BaseGaProblem<C>>()

    /**
     * Mixes chromosomes to produce a new child chromosome.
     *
     * @param parents the chromosomes to mix taken from selection.
     * @return the new chromosome.
     */
    abstract fun crossover(parents : List<C>): C

    /**
     * Alters one or more genes in a chromosome from its initial state.
     * This method will be called only if mutation is enabled.
     *
     * @param chromosome the chromosome to mutate.
     * @param mutationRate the mutation rate.
     */
    abstract fun mutate(chromosome: C, mutationRate: Double)

    /**
     * Selects the best chromosome for later breeding in a tournament with the bests.
     *
     * @param population the current population.
     * @return a chromosome from the current population for later breeding.
     */
    private fun selection(population: Population<C>): C {
        val tournamentPopulation = Population<C>()
        for (i in 1..parameters.tournamentSize) tournamentPopulation += getRandomFittest(population)
        logger.trace("Selected population: {}", tournamentPopulation)
        return getFittest(tournamentPopulation)
    }

    /**
     * Evolves a population over one generation.
     *
     * @param population a population.
     * @return a new population.
     */
    private fun evolve(population: Population<C>): Population<C> {
        val newGeneration = Population<C>()
        var offset = 0
        if (parameters.useElitism) {
            val child = getFittest(population)
            newGeneration += child
            logger.trace("Elite child: {}", child)
            offset++
        }

        for (i in offset until population.size) {
            val parents = ArrayList<C>(parameters.numberOfParentsForCrossover)
            for (j in 1..parameters.numberOfParentsForCrossover) parents += selection(population)
            val child = crossover(parents)
            logger.trace("Child #{}: {}", i+1, child)
            if (parameters.isMutationEnabled) {
                mutate(child, parameters.mutationRate)
                logger.trace("Mutated child #{}: {}", i+1, child)
            }
            newGeneration += child
        }

        return newGeneration
    }

    /**
     * Runs evolution process ang returns the fittest chromosome.
     *
     * @return the fittest chromosome
     */
    operator fun invoke(): C {
        var currentPopulation = parameters.firstGeneration
        logger.trace("Genesis population: {}", currentPopulation)
        var fittest = getFittest(currentPopulation)
        logger.debug("Fittest solution of genesis population: {}", fittest)
        logger.info("Fittest value of genesis population: {}", fittest.fitnessValue)
        for (i in 1..parameters.numberOfGenerations) {
            currentPopulation = evolve(currentPopulation)
            logger.trace("Generation #{}: {}", i, currentPopulation)
            fittest = getFittest(currentPopulation)
            logger.debug("Fittest solution of generation #{}: {}", i, fittest)
            logger.info("Fittest value of generation #{}: {}", i, fittest.fitnessValue)
        }
        return getFittest(currentPopulation)
    }

    private fun getFittest(population: Population<C>) = if (parameters.isTheFittestTheLowest) population.fittestFrom(TOP) else population.fittestFrom(BOTTOM)
    private fun getRandomFittest(population: Population<C>) = if (parameters.isTheFittestTheLowest) population.randomFrom(TOP) else population.randomFrom(BOTTOM)
}
