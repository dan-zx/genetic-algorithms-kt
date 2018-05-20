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

import java.util.Collections

/**
 * A possible solution of the problem.
 * The **fitnessValue** indicates how good or bad is the solution.
 * Works a as an indexed mutable collection with the same syntactic sugar of a list
 * (`+=`, `-=`, `[]`, `in`)
 *
 * @param G a [Gene] type.
 * @property genes the list of genes
 * @property size the number of genes in this chromosome
 * @property isEmpty if this chromosome has no genes
 * @property fitnessValue indicates how good or bad is the solution.
 *           Depends on each problem if good means high or low.
 *
 * @author Daniel Pedraza-Arcega
 */
abstract class Chromosome<G: Gene> : Iterable<G>, RandomAccess {

    protected var genes = ArrayList<G>()

    val size
        get() = genes.size

    val isEmpty
        get() = genes.isEmpty()

    abstract val fitnessValue: Double

    /** @return a deep clone of this chromosome. */
    abstract fun copy() : Chromosome<G>

    /** Shuffles its genes. */
    fun shuffle() = genes.shuffle()

    /** Swaps the gene at index i with the j index. */
    fun swap(i: Int, j: Int) = Collections.swap(genes, i, j)

    override fun toString() =  """{ "genes": $genes, "fitnessValue": $fitnessValue }"""
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