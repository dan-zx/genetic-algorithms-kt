package com.github.danzx.ga.kp

import com.github.danzx.ga.api.Chromosome

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