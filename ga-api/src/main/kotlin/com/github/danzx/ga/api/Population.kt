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
package com.github.danzx.ga.api

import com.github.danzx.ga.api.util.random

import java.util.SortedMap
import java.util.TreeMap

/**
 * A collection of chromosomes sorted by their fitness values.
 * Can be used `+=` operator to add chromosomes and can be iterated.
 *
 * @param C a [Chromosome] type.
 * @property size the number of chromosomes in this chromosome
 * @property isEmpty if this population has no chromosomes
 *
 * @author Daniel Pedraza-Arcega
 */
class Population<C : Chromosome<*>> : Iterable<C> {

    enum class Half { TOP, BOTTOM }

    private val chromosomes = TreeMap<Double, MutableList<C>>()

    var size = 0
        private set

    val isEmpty
        get() = size == 0

    operator fun plusAssign(chromosome: C) {
        if (chromosome.fitnessValue in chromosomes.keys) chromosomes[chromosome.fitnessValue]!! += chromosome
        else {
            val list = ArrayList<C>()
            list += chromosome
            chromosomes[chromosome.fitnessValue] = list
        }
        size++
    }

    /**
     * @param whichHalf either `TOP` or `BOTTOM`
     * @return the fittest chromosome from a half the population.
     */
    fun fittestFrom(whichHalf: Half) = if (whichHalf == Half.TOP) chromosomes.firstEntry().value[0] else chromosomes.lastEntry().value[0]

    /**
     * @param whichHalf either `TOP` or `BOTTOM`
     * @return a random chromosome from a half the population.
     */
    fun randomFrom(whichHalf: Half): C {
        val index = randomIndexFrom(whichHalf)
        var chromosome: C? = null
        forEachIndexed { i, c -> if (i == index) chromosome = c }
        return chromosome!!
    }

    private fun randomIndexFrom(whichHalf: Half) = if (whichHalf == Half.TOP) (0 until size/2).random() else (size/2 until size).random()

    override operator fun iterator(): Iterator<C> = ChromosomeIterator(chromosomes)

    override fun toString() = """{ "chromosomes": ${mapToString()} }"""

    private fun mapToString(): String {
        return chromosomes.values.flatMap { it.toList() }.joinToString(prefix = "[", postfix = "]")
    }

    private class ChromosomeIterator<C : Chromosome<*>>(chromosomes: SortedMap<Double, MutableList<C>>) : Iterator<C> {

        private var bucketIterator: Iterator<C>? = null
        private val mapIterator = chromosomes.iterator()

        override fun hasNext() = bucketHasNextElement || mapIterator.hasNext()

        override fun next(): C {
            if (bucketHasNextElement) return bucketIterator!!.next()
            val (_, bucket) = mapIterator.next()
            bucketIterator = bucket.iterator()
            return bucketIterator!!.next()
        }

        private val bucketHasNextElement
            get() = (bucketIterator != null && bucketIterator!!.hasNext())
    }
}