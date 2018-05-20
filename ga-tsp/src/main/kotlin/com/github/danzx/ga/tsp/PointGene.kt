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

import com.github.danzx.ga.api.Gene

/**
 * Represents a point in a Cartesian Plane.
 *
 * @property x the x coordinate.
 * @property y the y coordinate.
 *
 * @author Daniel Pedraza-Arcega
 */
data class PointGene(val x: Int, val y: Int): Gene {

    /**
     * Calculates the distance between this point and another.
     *
     * @param other other point.
     */
    fun distanceBetween(other: PointGene): Double {
        val xDistance = Math.pow((x - other.x).toDouble(), 2.0)
        val yDistance = Math.pow((y - other.y).toDouble(), 2.0)
        return Math.sqrt(xDistance + yDistance)
    }

    override fun toString() = """{ "x": $x, "y": $y }"""
}