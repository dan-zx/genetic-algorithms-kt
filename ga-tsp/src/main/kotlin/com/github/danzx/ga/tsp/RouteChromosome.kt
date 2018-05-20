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

import com.github.danzx.ga.api.Chromosome

/**
 * A possible route in TSP.
 *
 * @property fitnessValue the travel distance of the full route.
 *
 * @author Daniel Pedraza-Arcega
 */
class RouteChromosome: Chromosome<PointGene>() {

    override fun copy(): RouteChromosome {
        val clone = RouteChromosome()
        forEach { clone += it.copy() }
        return clone
    }

    override val fitnessValue: Double
        get() {
            var travelDistance = 0.0
            for (index in 0 until size - 1) {
                val a = this[index]
                val b = this[index+1]
                travelDistance += a.distanceBetween(b)
            }
            return travelDistance
        }
}