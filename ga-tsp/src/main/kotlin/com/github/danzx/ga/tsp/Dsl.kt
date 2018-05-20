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
package com.github.danzx.ga.tsp

import com.github.danzx.ga.api.util.random

/** Converts an array of nullable [PointGene]s to a [RouteChromosome] */
fun <G: PointGene?> Array<G>.toRouteChromosome(): RouteChromosome {
    val c = RouteChromosome()
    for (i in indices) {
        c += this[i]!!
    }
    return c
}

/**
 * Creates an ArrayList of [PointGene]s as part of DSL of TSP.
 *
 * @param init init block
 */
fun points(init: ArrayList<PointGene>.() -> Unit): ArrayList<PointGene> {
    val list = ArrayList<PointGene>()
    list.init()
    return list
}

/**
 * Adds a [PointGene] to an ArrayList.
 *
 * @param x the x coordinate.
 * @param y the y coordinate.
 */
fun ArrayList<PointGene>.pointAt(x: Int, y: Int) {
    this += PointGene(x, y)
}


/**
 * Creates an ArrayList with random [PointGene]s
 *
 * @param numberOfPoints the size of the list
 * @param xLimit the x coordinate limit.
 * @param yLimit the y coordinate limit.
 */
fun random(numberOfPoints: Int, xLimit: Int, yLimit: Int): ArrayList<PointGene> {
    val items = ArrayList<PointGene>(numberOfPoints)
    for(i in 1..numberOfPoints) items.pointAt((1..xLimit).random(), (1..yLimit).random())
    return items
}