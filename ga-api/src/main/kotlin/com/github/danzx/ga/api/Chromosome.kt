package com.github.danzx.ga.api

import java.util.Collections

abstract class Chromosome<G: Gene> : Iterable<G> {

    protected var genes = ArrayList<G>()

    val size
        get() = genes.size

    val isEmpty
        get() = genes.isEmpty()

    abstract val fitnessValue: Double
    abstract fun copy() : Chromosome<G>

    override fun toString() =  """{ "genes": $genes, "fitnessValue": $fitnessValue }"""

    fun clear() = genes.clear()
    fun shuffle() = genes.shuffle()
    fun swap(i: Int, j: Int) = Collections.swap(genes, i, j)

    override operator fun iterator() = genes.iterator()
    operator fun contains(element: G) = genes.contains(element)
    operator fun get(index: Int) = genes[index]

    open operator fun set(index: Int, element: G) {
        genes[index] = element
    }

    open operator fun plusAssign(element: G) {
        genes.add(element)
    }

    open operator fun minusAssign(element: G) {
        genes.remove(element)
    }
}