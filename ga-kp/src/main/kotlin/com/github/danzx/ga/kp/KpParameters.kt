package com.github.danzx.ga.kp

import com.github.danzx.ga.api.GaParameters
import com.github.danzx.ga.api.Population
import com.github.danzx.ga.api.util.random

class KpParameters(tournamentSize: Int,
                   numberOfGenerations: Int,
                   private val items: List<ItemGene>,
                   populationsOf: Int,
                   backpackWeightLimit: Double,
                   numberOfParentsForCrossover: Int = 2,
                   mutationRate: Double = 0.0,
                   useElitism: Boolean = false):
      GaParameters<Population<BackpackChromosome>>(tournamentSize, numberOfGenerations, genesisPopulation(populationsOf, backpackWeightLimit, items), numberOfParentsForCrossover, mutationRate, useElitism, isTheFittestTheLowest = false) {

    fun getRandomItem() = items[(0 until items.size).random()]

    companion object {
        private fun genesisPopulation(populationsSize: Int, backpackWeightLimit: Double, items: List<ItemGene>): Population<BackpackChromosome> {
            val firstGeneration = Population<BackpackChromosome>()
            for (i in 1..populationsSize) {
                val newBackpack = BackpackChromosome(backpackWeightLimit)
                for (i in (0 until items.size).shuffled()) newBackpack.tryAdd(items[i])
                firstGeneration += newBackpack
            }

            return firstGeneration
        }
    }
}