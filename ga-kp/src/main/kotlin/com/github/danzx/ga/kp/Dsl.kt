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
package com.github.danzx.ga.kp

import com.github.danzx.ga.api.util.random

import java.util.UUID

/**
 * Creates an ArrayList of [ItemGene]s as part of DSL of TSP.
 *
 * @param init init block
 */
fun items(init: ArrayList<ItemGene>.() -> Unit): ArrayList<ItemGene> {
    val list = ArrayList<ItemGene>()
    list.init()
    return list
}

/**
 * Adds a [ItemGene] to an ArrayList.
 *
 * @param name the name of the item.
 * @param mass the mass of the item.
 * @param value the value of the item.
 */
fun ArrayList<ItemGene>.item(name: String, mass: Double, value: Double) {
    this += ItemGene(name, mass, value)
}

/**
 * Creates an ArrayList with random [ItemGene]s
 *
 * @param numberOfItems the size of the list
 * @param maxMass the mass limit.
 * @param maxValue the value limit.
 */
fun random(numberOfItems: Int, maxMass: Int, maxValue: Int): ArrayList<ItemGene> {
    val items = ArrayList<ItemGene>(numberOfItems)
    for(i in 1..numberOfItems) items.item(UUID.randomUUID().toString(), (1..maxMass).random().toDouble(), (1..maxValue).random().toDouble())
    return items
}