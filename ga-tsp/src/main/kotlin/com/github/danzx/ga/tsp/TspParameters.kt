package com.github.danzx.ga.tsp

import com.github.danzx.ga.api.GaParameters
import com.github.danzx.ga.api.Population

class TspParameters(tournamentSize: Int,
                    numberOfGenerations: Int,
                    populationsOf: Int,
                    points: List<PointGene>,
                    numberOfParentsForCrossover: Int = 2,
                    mutationRate: Double = 0.0,
                    useElitism: Boolean = false):
      GaParameters<Population<RouteChromosome>>(tournamentSize, numberOfGenerations, genesisPopulation(populationsOf, points), numberOfParentsForCrossover, mutationRate, useElitism, isTheFittestTheLowest = true) {

    companion object {
        private fun genesisPopulation(populationsSize: Int, points: List<PointGene>): Population<RouteChromosome> {
            val firstGeneration = Population<RouteChromosome>()
            for (i in 1..populationsSize) {
                val newBackpack = RouteChromosome()
                for (i in (1 until points.size).shuffled()) newBackpack += points[i]
                firstGeneration += newBackpack
            }
            return firstGeneration
        }
    }
}