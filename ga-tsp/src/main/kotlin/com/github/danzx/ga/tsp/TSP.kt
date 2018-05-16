package com.github.danzx.ga.tsp

import com.github.danzx.ga.api.GaParameters

fun main(args: Array<String>) {
    val tsp = TravelingSalesmanProblem(GaParameters(
            tournamentSize = 4,
            numberOfGenerations = 10,
            mutationRate = 0.025,
            useElitism = true,
            isTheFittestTheLowest = true,
            firstGeneration = genesisPopulation(populationsOf = 8) {
                addCityAt(x = 60, y = 200)
                addCityAt(x = 180, y = 200)
                addCityAt(x = 80, y = 180)
                addCityAt(x = 140, y = 180)
                addCityAt(x = 20, y = 160)
                addCityAt(x = 100, y = 160)
                addCityAt(x = 200, y = 160)
                addCityAt(x = 140, y = 140)
                addCityAt(x = 40, y = 120)
                addCityAt(x = 100, y = 120)
                addCityAt(x = 180, y = 100)
                addCityAt(x = 60, y = 80)
                addCityAt(x = 120, y = 80)
                addCityAt(x = 180, y = 60)
                addCityAt(x = 20, y = 40)
                addCityAt(x = 100, y = 40)
                addCityAt(x = 200, y = 40)
                addCityAt(x = 20, y = 20)
                addCityAt(x = 60, y = 20)
                addCityAt(x = 160, y = 20)
            }
    ))

    tsp()
}