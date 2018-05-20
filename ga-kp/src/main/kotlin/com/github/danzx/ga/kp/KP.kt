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

fun main(args: Array<String>) {
    val kp = KnapsackProblem(KpParameters(
            tournamentSize = 5,
            numberOfGenerations = 5_000,
            mutationRate = 0.5,
            useElitism = true,
            backpackWeightLimit = 15.0,
            populationsOf = 200,
            items = random(numberOfItems = 2_000, maxMass = 15, maxValue = 5_000)))
    kp()
}