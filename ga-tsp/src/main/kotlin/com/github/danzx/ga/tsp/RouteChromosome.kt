package com.github.danzx.ga.tsp

import com.github.danzx.ga.api.Chromosome

class RouteChromosome: Chromosome<PointGene>() {

    override fun copy(): RouteChromosome {
        val clone = RouteChromosome()
        forEach { clone += it.copy() }
        return clone
    }

    override val fitnessValue: Double
        get() {
            var travelDistance = 0.0
            for (index in 0 until size - 1) {
                val a = this[index]
                val b = this[index+1]
                travelDistance += a.distanceBetween(b)
            }
            return travelDistance
        }

    fun addCityAt(x: Int, y: Int) {
        this += PointGene(x, y)
    }
}