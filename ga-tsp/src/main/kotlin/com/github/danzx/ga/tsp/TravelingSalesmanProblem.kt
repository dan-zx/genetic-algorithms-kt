package com.github.danzx.ga.tsp

import com.github.danzx.ga.api.BaseGaProblem
import com.github.danzx.ga.api.GaParameters
import com.github.danzx.ga.api.Population
import com.github.danzx.ga.api.util.random

class TravelingSalesmanProblem(parameters: GaParameters<Population<RouteChromosome>>) : BaseGaProblem<RouteChromosome>(parameters) {

    override fun mutate(chromosome: RouteChromosome, mutationRate: Double) {
        chromosome.forEachIndexed { index, _ ->
            if (Math.random() < mutationRate) {
                val indexToSwap = (0 until chromosome.size).random()
                chromosome.swap(index, indexToSwap)
            }
        }
    }

    override fun crossover(parents: List<RouteChromosome>): RouteChromosome {
        val father = parents[0]
        val mother = parents[1]
        val childGenesArray = arrayOfNulls<PointGene>(father.size)
        val initialPosition = (0 until father.size).random()
        val finalPosition = (0 until father.size).random()
        for (i in initialPosition until finalPosition) childGenesArray[i] = father[i]
        for (motherGene in mother) {
            if (motherGene !in childGenesArray) {
                for (j in 0 until childGenesArray.size) {
                    if (childGenesArray[j] == null) {
                        childGenesArray[j] = motherGene
                        break
                    }
                }
            }
        }

        return childGenesArray.toRouteChromosome()
    }
}