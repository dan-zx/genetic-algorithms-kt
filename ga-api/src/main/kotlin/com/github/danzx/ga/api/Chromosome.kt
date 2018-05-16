package com.github.danzx.ga.api

import java.util.Collections

abstract class Chromosome<G: Gene>(isTheFitnessTheLowest: Boolean = false, initialCapacity: Int = 10) : Comparable<Chromosome<G>>, Cloneable, Iterable<G> {

    private var genes = ArrayList<G>(initialCapacity)
    private var comparator: Comparator<Chromosome<*>>

    init {
        comparator = DescendingFitnessValueComparator
        if (isTheFitnessTheLowest) comparator = AscendingFitnessValueComparator
    }

    val size
        get() = genes.size

    val isEmpty
        get() = genes.isEmpty()

    val indices
        get() = IntRange(0, size-1)

    abstract val fitnessValue: Double
    abstract fun copy() : Chromosome<G>


    override fun compareTo(other: Chromosome<G>) = comparator.compare(this, other)
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

    private object AscendingFitnessValueComparator : Comparator<Chromosome<*>> {
        override fun compare(o1: Chromosome<*>, o2: Chromosome<*>) = if (o1.fitnessValue > o2.fitnessValue) 1 else if (o1.fitnessValue < o2.fitnessValue) -1 else 0
    }

    private object DescendingFitnessValueComparator : Comparator<Chromosome<*>> {
        private val comparator = AscendingFitnessValueComparator.reversed()
        override fun compare(o1: Chromosome<*>, o2: Chromosome<*>) = comparator.compare(o1, o2)
    }
}