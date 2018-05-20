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

fun main(args: Array<String>) {
    val tsp = TravelingSalesmanProblem(TspParameters(
            tournamentSize = 4,
            numberOfGenerations = 5_000,
            mutationRate = 0.025,
            useElitism = true,
            populationsOf = 200,
            points = random(numberOfPoints = 100, xLimit = 500, yLimit = 500)
    ))

    tsp()
}