package com.github.danzx.ga.api

import java.util.Collections

abstract class Chromosome<G: Gene>(initialCapacity: Int = 10) : Iterable<G> {

    private var genes = ArrayList<G>(initialCapacity)

    val size
        get() = genes.size

    val isEmpty
        get() = genes.isEmpty()

    abstract val fitnessValue: Double
    abstract fun copy() : Chromosome<G>

    override fun toString() =  """{ "genes": ${genes}, "fitnessValue": $fitnessValue }"""

    fun clear() = genes.clear()
    fun shuffle() = genes.shuffle()
    fun swap(i: Int, j: Int) = Collections.swap(genes, i, j)

    override operator fun iterator() = genes.iterator()
    operator fun contains(element: G) = genes.contains(element)
    operator fun get(index: Int) = genes[index]

    operator fun set(index: Int, element: G) {
        genes[index] = element
    }

    operator fun plusAssign(element: G) {
        genes.add(element)
    }

    operator fun minusAssign(element: G) {
        genes.remove(element)
    }
}