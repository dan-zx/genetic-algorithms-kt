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