package com.github.danzx.ga.api

import com.github.danzx.ga.api.Population.Half.BOTTOM
import com.github.danzx.ga.api.Population.Half.TOP
import com.github.danzx.ga.api.util.loggerFor

abstract class BaseGaProblem<C: Chromosome<*>>(private val parameters: GaParameters<Population<C>>) {

    private val logger = loggerFor<BaseGaProblem<C>>()

    abstract fun crossover(parents : List<C>): C
    abstract fun mutate(chromosome: C, mutationRate: Double)

    private fun selection(population: Population<C>): C {
        val tournamentPopulation = Population<C>()
        for (i in 1..parameters.tournamentSize) tournamentPopulation += getRandomFittest(population)
        logger.trace("Selected population: {}", tournamentPopulation)
        return getFittest(tournamentPopulation)
    }

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
