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

import com.github.danzx.ga.api.Chromosome

/**
 * A possible backpack in KP.
 *
 * @property weightLimit the maximum weight this backpack can hold
 * @property currentWeight the sum of all items' weight.
 * @property currentValue the sum of all items' value.
 * @property fitnessValue same as [currentValue].
 *
 * @author Daniel Pedraza-Arcega
 */
class BackpackChromosome(val weightLimit: Double): Chromosome<ItemGene>() {

    var currentWeight: Double = 0.0
        private set
    private var currentValue: Double = 0.0

    override val fitnessValue: Double
        get() = currentValue

    override fun copy(): BackpackChromosome {
        val clone = BackpackChromosome(weightLimit)
        clone.currentWeight = currentWeight
        clone.currentValue = currentValue
        forEach { clone.genes.add(it.copy()) }
        return clone
    }

    /**
     * Tries to replace the item located at the given index with the given item if it fits.
     *
     * @param index the index.
     * @param element the item.
     * @return `true` if successful; otherwise `false`.
     */
    fun trySet(index: Int, element: ItemGene): Boolean {
        if (element !in this) {
            val oldElement = this[index]
            currentWeight -= oldElement.mass
            if (itemFits(element)) {
                currentValue = currentValue - oldElement.value + element.value
                currentWeight += element.mass
                super.set(index, element)
                return true
            }
            currentWeight += oldElement.mass
        }
        return false
    }

    /**
     * Tries to add a new item if it fits.
     *
     * @param element the item.
     * @return `true` if successful; otherwise `false`.
     */
    fun tryAdd(element: ItemGene): Boolean {
        if (element !in this && itemFits(element)) {
            currentWeight += element.mass
            currentValue += element.value
            super.plusAssign(element)
            return true
        }
        return false
    }

    override operator fun minusAssign(element: ItemGene) {
        if (element in this) {
            currentWeight -= element.mass
            currentValue -= element.value
            super.minusAssign(element)
        }
    }

    @Deprecated(
            message = "Use trySet() method instead",
            level = DeprecationLevel.ERROR,
            replaceWith = ReplaceWith("trySet(index, element)")
    )
    override operator fun set(index: Int, element: ItemGene) {
        trySet(index, element)
    }

    @Deprecated(
            message = "Use tryAdd() method instead",
            level = DeprecationLevel.ERROR,
            replaceWith = ReplaceWith("tryAdd(element)")
    )
    override operator fun plusAssign(element: ItemGene) {
        tryAdd(element)
    }

    override fun toString() = """{ "weight": $currentWeight, "value": $currentValue, "items": $genes }"""

    private fun itemFits(element: ItemGene) = currentWeight + element.mass <= weightLimit
}