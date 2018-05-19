Traveling Salesman Problem
==========================

General usage:

```kotlin
val tsp = TravelingSalesmanProblem(GaParameters(
        tournamentSize = /* Number of potential parents to select for later breeding */,
        numberOfGenerations = /* Number of iteration */,
        mutationRate = /* If > 0, the probability increases of new routes with different paths from parants  */,
        useElitism = /* Persist the best route through evolution, default is false */,
        numberOfParentsForCrossover = /* the number of parent routes used to produce child routes in crossovers, default is 2 */

        // Use genesisPopulation() utility create the first solution
        firstGeneration = genesisPopulation(populationsOf = /* Size of populations */) {
            // Add as many cities as you want in a Cartesian Plane
            // This cities represent the firs solution that will be used in the GA
            addCityAt(x = 60, y = 200)
            // ...
            addCityAt(x = 80, y = 180)
        }
))

val fittest = tsp() // invoke to get the best route
```
