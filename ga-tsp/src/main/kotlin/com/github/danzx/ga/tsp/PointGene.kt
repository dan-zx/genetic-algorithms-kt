package com.github.danzx.ga.tsp

import com.github.danzx.ga.api.Gene

data class PointGene(val x: Int, val y: Int): Gene {
    fun distanceBetween(other: PointGene): Double {
        val xDistance = Math.pow((x - other.x).toDouble(), 2.0)
        val yDistance = Math.pow((y - other.y).toDouble(), 2.0)
        return Math.sqrt(xDistance + yDistance)
    }

    override fun toString() = """{ "x": $x, "y": $y }"""
}