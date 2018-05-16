package com.github.danzx.ga.api

import com.github.danzx.ga.api.util.random
import java.util.TreeMap

class Population<C : Chromosome<*>> {

    private val chromosomes = TreeMap<C, MutableList<C>>()
    var size = 0
        private set

    operator fun plusAssign(chromosome: C) {
        if (chromosome in chromosomes.keys) chromosomes[chromosome]!! += chromosome
        else {
            val list = ArrayList<C>()
            list += chromosome
            chromosomes[chromosome] = list
        }
        size++
    }

    val fittest
        get() = chromosomes.firstKey()

    fun randomFromTop(): C {
        val index = (0..size/2).random()
        var i = 0
        for ((_, list) in chromosomes) {
            for (chromosome in list) {
                if (i == index) return chromosome
                i++
            }
        }
        // Should never happen
        return null!!
    }

    override fun toString() = """{ "chromosomes": ${mapToString()} }"""


    private fun mapToString(): String {
        return chromosomes.values.flatMap { it.toList() }.joinToString(prefix = "[", postfix = "]")
    }
}