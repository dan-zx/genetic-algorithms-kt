package com.github.danzx.ga.api

import com.github.danzx.ga.api.util.loggerFor
import com.github.danzx.ga.api.util.random

abstract class BaseGaProblem<C: Chromosome<*>>(private val parameters: GaParameters<Population<C>>) {

    private val logger = loggerFor<BaseGaProblem<C>>()

    abstract fun crossover(parents : List<C>): C

    open fun mutate(chromosome: C) {
        for (index in chromosome.indices) {
            if (Math.random() < parameters.mutationRate) {
                val indexToSwap = (0 until chromosome.size).random()
                chromosome.swap(index, indexToSwap)
            }
        }
    }

    private fun selection(population: Population<C>): C {
        val tournamentPopulation = Population<C>()

        for (i in 1..parameters.tournamentSize) {
            tournamentPopulation += population.randomFromTop()
        }

        return tournamentPopulation.fittest
    }

    private fun evolve(population: Population<C>): Population<C> {
        val newGeneration = Population<C>()
        var offset = 0
        if (parameters.useElitism) {
            val child = population.fittest
            newGeneration += child
            logger.trace("Elite child: {}", child)
            offset++
        }

        for (i in offset until population.size) {
            val parents = ArrayList<C>(parameters.numberOfParentsForCrossover)
            for (j in 1..parameters.numberOfParentsForCrossover) parents += selection(population)
            val child = crossover(parents)
            logger.trace("Child #{}: {}", i+1, child)
            if (parameters.mutationRate > 0) {
                mutate(child)
                logger.trace("Mutated child #{}: {}", i+1, child)
            }
            newGeneration += child
        }

        logger.trace("New generation size: {}", newGeneration.size)
        return newGeneration
    }

    operator fun invoke(): C {
        var currentPopulation = parameters.firstGeneration
        logger.trace("Genesis generation: {}", currentPopulation)
        logger.debug("Fittest solution of genesis generation: {}", currentPopulation.fittest)
        logger.info("Fittest value of genesis generation: {}", currentPopulation.fittest.fitnessValue)
        for (i in 1..parameters.numberOfGenerations) {
            currentPopulation = evolve(currentPopulation)
            logger.trace("Generation #{}: {}", i, currentPopulation)
            logger.debug("Fittest solution of generation #{}: {}", i, currentPopulation.fittest)
            logger.info("Fittest value of generation #{}: {}", i, currentPopulation.fittest.fitnessValue)
        }
        return currentPopulation.fittest
    }
}
