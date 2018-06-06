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
package com.github.danzx.ga.tsp

import com.github.danzx.ga.api.GaParameters
import com.github.danzx.ga.api.Population

/**
 * TSP parameters.
 *
 * @author Daniel Pedraza-Arcega
 */
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
                for (j in (1 until points.size).shuffled()) newBackpack += points[j]
                firstGeneration += newBackpack
            }
            return firstGeneration
        }
    }
}
