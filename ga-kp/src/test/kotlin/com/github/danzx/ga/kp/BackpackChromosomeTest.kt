package com.github.danzx.ga.kp

import junitparams.JUnitParamsRunner
import junitparams.Parameters

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset.offset

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class BackpackChromosomeTest {

    @Test
    @Parameters(method = "itemsToAdd")
    fun `should add element and return if successful`(item: ItemGene, shouldBeAdded: Boolean, expectedWeight: Double, expectedValue: Double) {
        val backpack = BackpackChromosome(10.0)
        backpack.tryAdd(ItemGene("0", 3.0, 2.0))

        assertThat(backpack.tryAdd(item)).isEqualTo(shouldBeAdded)
        if (shouldBeAdded) assertThat(backpack).hasSize(2).contains(item)
        else assertThat(backpack).hasSize(1)
        assertThat(backpack.currentWeight).isCloseTo(expectedWeight, offset(0.000000000000001))
        assertThat(backpack.fitnessValue).isCloseTo(expectedValue, offset(0.000000000000001))
    }

    @Test
    @Parameters(method = "itemsToSet")
    fun `should replace element and return if successful`(index: Int, item: ItemGene, shouldBeInserted: Boolean, expectedWeight: Double, expectedValue: Double) {
        val backpack = BackpackChromosome(10.0)
        backpack.tryAdd(ItemGene("0", 3.0, 2.0))
        backpack.tryAdd(ItemGene("1", 0.5, 3.0))
        backpack.tryAdd(ItemGene("2", 4.5, 3.7))

        assertThat(backpack.trySet(index, item)).isEqualTo(shouldBeInserted)
        assertThat(backpack).hasSize(3)
        if (shouldBeInserted) assertThat(backpack).contains(item)
        assertThat(backpack.currentWeight).isCloseTo(expectedWeight, offset(0.000000000000001))
        assertThat(backpack.fitnessValue).isCloseTo(expectedValue, offset(0.000000000000001))
    }

    fun itemsToAdd() = arrayOf(
            // Will fit
            arrayOf(ItemGene("1", 0.0, 1.0), true, 3.0, 3.0),
            arrayOf(ItemGene("1", 7.0, 2.0), true, 10.0, 4.0),
            arrayOf(ItemGene("1", 6.0, 0.5), true, 9.0, 2.5),

            // Won't fit
            arrayOf(ItemGene("1", 8.0, 1.0), false, 3.0, 2.0),
            arrayOf(ItemGene("1", 9.0, 1.0), false, 3.0, 2.0),
            arrayOf(ItemGene("1", 10.0, 1.0), false, 3.0, 2.0),

            // Already in backpack
            arrayOf(ItemGene("0", 3.0, 2.0), false, 3.0, 2.0)
    )

    fun itemsToSet() = arrayOf(
            // Will fit
            arrayOf(0, ItemGene("3", 0.0, 0.0), true, 5.0, 6.7),
            arrayOf(1, ItemGene("3", 0.0, 0.0), true, 7.5, 5.7),
            arrayOf(2, ItemGene("3", 0.0, 0.0), true, 3.5, 5.0),
            arrayOf(0, ItemGene("3", 1.0, 1.0), true, 6.0, 7.7),
            arrayOf(1, ItemGene("3", 1.0, 1.0), true, 8.5, 6.7),
            arrayOf(2, ItemGene("3", 1.0, 1.0), true, 4.5, 6.0),
            arrayOf(0, ItemGene("3", 5.0, 0.0), true, 10.0, 6.7),
            arrayOf(1, ItemGene("3", 2.5, 0.0), true, 10.0, 5.7),
            arrayOf(2, ItemGene("3", 6.5, 0.0), true, 10.0, 5.0),

            // Won't fit
            arrayOf(0, ItemGene("3", 5.5, 0.0), false, 8.0, 8.7),
            arrayOf(1, ItemGene("3", 3.0, 0.0), false, 8.0, 8.7),
            arrayOf(2, ItemGene("3", 7.0, 0.0), false, 8.0, 8.7),

            // Already in backpack
            arrayOf(0, ItemGene("0", 3.0, 2.0), false, 8.0, 8.7),
            arrayOf(1, ItemGene("0", 3.0, 2.0), false, 8.0, 8.7),
            arrayOf(2, ItemGene("0", 3.0, 2.0), false, 8.0, 8.7),
            arrayOf(0, ItemGene("1", 0.5, 3.0), false, 8.0, 8.7),
            arrayOf(1, ItemGene("1", 0.5, 3.0), false, 8.0, 8.7),
            arrayOf(2, ItemGene("1", 0.5, 3.0), false, 8.0, 8.7),
            arrayOf(0, ItemGene("2", 4.5, 3.7), false, 8.0, 8.7),
            arrayOf(1, ItemGene("2", 4.5, 3.7), false, 8.0, 8.7),
            arrayOf(2, ItemGene("2", 4.5, 3.7), false, 8.0, 8.7)
    )
}