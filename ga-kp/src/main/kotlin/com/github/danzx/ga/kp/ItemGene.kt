package com.github.danzx.ga.kp

import com.github.danzx.ga.api.Gene

data class ItemGene(val name: String, val mass: Double, val value: Double) : Gene {
    override fun toString() = """{ "name": "$name", "mass": $mass, "value": $value }"""
}