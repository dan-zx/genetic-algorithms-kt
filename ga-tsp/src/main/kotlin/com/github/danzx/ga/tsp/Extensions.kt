package com.github.danzx.ga.tsp

import com.github.danzx.ga.api.Population

fun <G: PointGene?> Array<G>.toRouteChromosome(): RouteChromosome {
    val c = RouteChromosome()
    for (i in indices) {
        c += this[i]!!
    }
    return c
}

fun genesisPopulation(populationsOf: Int, init: RouteChromosome.() -> Unit): Population<RouteChromosome> {
    val genesisChromosome = RouteChromosome()
    genesisChromosome.init()
    val firstGeneration = Population<RouteChromosome>()
    firstGeneration += genesisChromosome
    for (i in 1 until populationsOf) {
        val newChromosome = genesisChromosome.copy()
        newChromosome.shuffle()
        firstGeneration += newChromosome
    }

    return firstGeneration
}