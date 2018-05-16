package com.github.danzx.ga.api.util

import java.util.Random

private object RandomRange : Random()

fun ClosedRange<Int>.random() = RandomRange.nextInt((endInclusive + 1) - start) + start
