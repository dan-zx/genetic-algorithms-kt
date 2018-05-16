package com.github.danzx.ga.api

import com.github.danzx.ga.api.Population.Half.BOTTOM
import com.github.danzx.ga.api.Population.Half.TOP

import org.assertj.core.api.Assertions.assertThat

import org.junit.Test

class PopulationTest {

    @Test
    fun `should sort chromosomes by fitness value asc`() {
        assertThat(testPopulation.size).isEqualTo(4)
        assertThat(testPopulation.isEmpty).isFalse()
        assertThat(testPopulation.map { it.fitnessValue }).isSorted.containsExactly(14.0, 20.0, 20.0, 23.0)
        assertThat(testPopulation.fittestFrom(TOP)).isEqualTo(testChromosomes[0])
        assertThat(testPopulation.fittestFrom(BOTTOM)).isEqualTo(testChromosomes[1])
    }

    @Test
    fun `should get random chromosomes in a half`() {
        assertThat(arrayOf(testPopulation.randomFrom(TOP))).containsAnyOf(testChromosomes[0], testChromosomes[2])
        assertThat(arrayOf(testPopulation.randomFrom(BOTTOM))).containsAnyOf(testChromosomes[1], testChromosomes[3])
    }

    private val testPopulation: Population<TestChromosome> by lazy {
            val p = Population<TestChromosome>()
            testChromosomes.forEach { p += it }
            p
        }

    private val testChromosomes: Array<TestChromosome> by lazy {
            val c1 = TestChromosome()
            c1 += TestGene(9)
            c1 += TestGene(1)
            c1 += TestGene(1)
            c1 += TestGene(3) // 14

            val c2 = TestChromosome()
            c2 += TestGene(8)
            c2 += TestGene(5)
            c2 += TestGene(1)
            c2 += TestGene(9) // 23

            val c3 = TestChromosome()
            c3 += TestGene(8)
            c3 += TestGene(2)
            c3 += TestGene(0)
            c3 += TestGene(10) // 20

            val c4 = TestChromosome()
            c4 += TestGene(9)
            c4 += TestGene(1)
            c4 += TestGene(1)
            c4 += TestGene(9) // 20

            arrayOf(c1, c2, c3, c4)
        }

    private class TestChromosome : Chromosome<TestGene>() {
        override val fitnessValue: Double
            get() = sumByDouble { it.value.toDouble() }

        override fun copy(): TestChromosome {
            TODO("not implemented")
        }
    }

    private data class TestGene(val value: Int) : Gene
}