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

import com.github.danzx.ga.api.BaseGaProblem
import com.github.danzx.ga.api.util.random

/**
 * TSP algorithm.
 *
 * @author Daniel Pedraza-Arcega
 */
class TravelingSalesmanProblem(parameters: TspParameters) : BaseGaProblem<RouteChromosome>(parameters) {

    /** Randomly swap points in the route using the given mutation rate */
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