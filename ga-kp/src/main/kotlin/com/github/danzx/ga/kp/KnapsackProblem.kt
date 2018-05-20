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

import com.github.danzx.ga.api.BaseGaProblem
import com.github.danzx.ga.api.util.random

/**
 * KP algorithm.
 *
 * @author Daniel Pedraza-Arcega
 */
class KnapsackProblem(private val parameters: KpParameters) : BaseGaProblem<BackpackChromosome>(parameters) {

    /** Randomly selects items to replace with new items if possible. */
    override fun mutate(chromosome: BackpackChromosome, mutationRate: Double) {
        val indices = chromosome.filterIndexed { _, _ -> Math.random() < mutationRate }.indices
        for (i in indices) chromosome.trySet(i, parameters.getRandomItem())
    }

    override fun crossover(parents: List<BackpackChromosome>): BackpackChromosome {
        val (father, mother) = parents
        val (child, parent) = if (father.fitnessValue > mother.fitnessValue) Pair(father.copy(), mother) else Pair(mother.copy(), father)
        setRandomGenes(child, parent)
        return child
    }

    private fun setRandomGenes(child: BackpackChromosome, parent: BackpackChromosome) {
        for (i in 0 until child.size) {
            val itemToPeekFromParent = (0 until parent.size).random()
            val itemToSetInChild = (0 until child.size).random()
            child.trySet(itemToSetInChild, parent[itemToPeekFromParent])
        }
    }
}
