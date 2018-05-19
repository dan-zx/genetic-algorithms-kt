package com.github.danzx.ga.kp

import com.github.danzx.ga.api.util.random
import java.util.UUID

fun items(init: ArrayList<ItemGene>.() -> Unit): ArrayList<ItemGene> {
    val list = ArrayList<ItemGene>()
    list.init()
    return list
}

fun ArrayList<ItemGene>.item(name: String, mass: Double, value: Double) {
    this += ItemGene(name, mass, value)
}

fun random(numberOfItems: Int, maxMass: Int, maxValue: Int): ArrayList<ItemGene> {
    val items = ArrayList<ItemGene>(numberOfItems)
    for(i in 1..numberOfItems) items.item(UUID.randomUUID().toString(), (1..maxMass).random().toDouble(), (1..maxValue).random().toDouble())
    return items
}