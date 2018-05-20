Traveling Salesman Problem
==========================

General usage:

```kotlin
val tsp = TravelingSalesmanProblem(TspParameters(
        tournamentSize = /* Number of potential parents to select for later breeding */,
        numberOfGenerations = /* Number of iteration */,
        mutationRate = /* If > 0, the probability increases of new routes with different paths from parents */,
        useElitism = /* Persist the best route through evolution, default is false */,
        numberOfParentsForCrossover = /* the number of parent routes used to produce child routes in crossovers, default is 2 */,
        populationsOf = /* Size of populations */,
        // Use random() utility to create the first population with random points
        points = random(numberOfPoints = 2_000, xLimit = 500, yLimit = 500)))
        // Or..
        // Use points() utility create the first solution
        points = points {
            // Add as many cities as you want in a Cartesian Plane
            // This cities represent the firs solution that will be used in the GA
            pointAt(x = 60, y = 200)
            // ...
            pointAt(x = 80, y = 180)
        }
))

val fittest = tsp() // invoke to get the best route
```
