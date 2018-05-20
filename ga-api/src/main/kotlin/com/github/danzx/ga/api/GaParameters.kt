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

/**
 * General parameters of all Genetic Algorithms.
 *
 * @param P a [Population] type
 * @property tournamentSize the number of potential parents to select for later breeding.
 * @property numberOfGenerations the number of iterations.
 * @property firstGeneration the genesis population used in evolution process.
 * @property numberOfParentsForCrossover the number of parent chromosomes used to produce child
 *           chromosomes in crossovers, default is `2`
 * @property mutationRate if > 0, the probability increases of new chromosomes with different
 *           (random) genes not inherited from their parents, default is `0.0` (no mutations)
 * @property useElitism persist the best chromosomes through evolution, default is `false`
 * @property isTheFittestTheLowest if `true`, the fittest chromosome has the lowest value, default
 *           is `false`
 * @property isMutationEnabled if mutation is enabled or not
 * @constructor requires at least `tournamentSize`, `numberOfGenerations` and `firstGeneration`
 *              parameters.
 *
 * @author Daniel Pedraza-Arcega
 */
open class GaParameters<out P: Population<*>>(
        val tournamentSize: Int,
        val numberOfGenerations: Int,
        val firstGeneration: P,
        val numberOfParentsForCrossover: Int = 2,
        val mutationRate: Double = 0.0,
        val useElitism: Boolean = false,
        val isTheFittestTheLowest: Boolean = false
) {
    val isMutationEnabled = mutationRate > 0.0
}