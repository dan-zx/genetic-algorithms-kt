package com.github.danzx.ga.kp

import com.github.danzx.ga.api.BaseGaProblem
import com.github.danzx.ga.api.util.random

class KnapsackProblem(private val parameters: KpParameters) : BaseGaProblem<BackpackChromosome>(parameters) {

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
