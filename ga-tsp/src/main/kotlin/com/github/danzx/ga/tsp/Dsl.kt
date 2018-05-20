package com.github.danzx.ga.tsp

import com.github.danzx.ga.api.util.random

fun <G: PointGene?> Array<G>.toRouteChromosome(): RouteChromosome {
    val c = RouteChromosome()
    for (i in indices) {
        c += this[i]!!
    }
    return c
}

fun points(init: ArrayList<PointGene>.() -> Unit): ArrayList<PointGene> {
    val list = ArrayList<PointGene>()
    list.init()
    return list
}

fun ArrayList<PointGene>.pointAt(x: Int, y: Int) {
    this += PointGene(x, y)
}


fun random(numberOfPoints: Int, xLimit: Int, yLimit: Int): ArrayList<PointGene> {
    val items = ArrayList<PointGene>(numberOfPoints)
    for(i in 1..numberOfPoints) items.pointAt((1..xLimit).random(), (1..yLimit).random())
    return items
}