Genetic Algorithms with Kotlin
==============================

[![License](https://img.shields.io/badge/licence-Apache_Licence_2.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

General API classes
-------------------

The API is is located at [ga-api directory](ga-api)

* `Gene` is marker interface. Elements of the complete solution should implement this interface.
* A `Chromosome` is a possible solution of the problem. The `fitnessValue` indicates how good or bad is the solution.
* `GaParameters` are the general parameters of all Genetic Algorithms.
* `Population` are a collection of chromosomes sorted by their fitness values.
* `BaseGaProblem` is the base of a Genetic Algorithm and implements the following operators:
    - Selection
    - Crossover
    - Mutation
    - Elitism

    > Problems must extend this class and implement crossover and mutation operators

Implemented examples
--------------------

* Traveling Salesman Problem (TSP) is located at [ga-tsp directory](ga-tsp/README.md)
    - Run with:
    ```sh
    $ ./gradlew :ga-tsp:run
    ```
* Knapsack Problem (KP) is located at [ga-kp directory](ga-kp/README.md)
    - Run with:
    ```sh
    $ ./gradlew :ga-kp:run
    ```

License
-------

    Copyright 2018 Daniel Pedraza-Arcega

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
