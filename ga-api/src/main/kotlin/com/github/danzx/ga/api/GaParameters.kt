package com.github.danzx.ga.api

data class GaParameters<out P: Population<*>>(
        val tournamentSize: Int,
        val numberOfGenerations: Int,
        val firstGeneration: P,
        val numberOfParentsForCrossover: Int = 2,
        val mutationRate: Double = 0.0,
        val useElitism: Boolean = false
)