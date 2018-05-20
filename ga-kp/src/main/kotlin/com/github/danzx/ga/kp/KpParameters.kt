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
package com.github.danzx.ga.kp

import com.github.danzx.ga.api.GaParameters
import com.github.danzx.ga.api.Population
import com.github.danzx.ga.api.util.random

/**
 * Kp parameters.
 *
 * @author Daniel Pedraza-Arcega
 */
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