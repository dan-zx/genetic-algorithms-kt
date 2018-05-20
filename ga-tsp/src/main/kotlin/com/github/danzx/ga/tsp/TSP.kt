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