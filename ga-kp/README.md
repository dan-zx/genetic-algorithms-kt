Knapsack Problem
================

General usage:

```kotlin
val kp = KnapsackProblem(KpParameters(
        tournamentSize = /* Number of potential parents to select for later breeding */,
        numberOfGenerations = /* Number of iteration */,
        mutationRate = /* If > 0, the probability increases of new backpacks with different items from parants */,
        useElitism = /* Persist the best backpacks through evolution, default is false */,
        backpackWeightLimit = /* How much a backpack can hold */,
        populationsOf = /* Size of populations */
        numberOfParentsForCrossover = /* the number of parent backpacks used to produce child backpacks in crossovers, default is 2 */

        // Use random() utility to create the first population with random items
        items = random(numberOfItems = 2_000, maxMass = 15, maxValue = 5_000)))
        // Or..
        // items() utility to create the the first population with random backpacks with a set of defined items
        items = items {
            // Add as many items as you want
            item(name = "Brownies", mass = 5.23, value = 4.35 )
            // ...
            item(name = "Ant Repellent", mass = 8.72, value = 10.0 )
        }
))

val fittest = kp() // invoke to get the best backpack
```
