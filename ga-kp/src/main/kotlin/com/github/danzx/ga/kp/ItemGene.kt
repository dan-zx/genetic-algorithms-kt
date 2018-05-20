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

import com.github.danzx.ga.api.Gene

/**
 * Represents an item that can be carried in a backpack
 *
 * @property name the name of the item.
 * @property mass the mass of the item.
 * @property value the value of the item.
 *
 * @author Daniel Pedraza-Arcega
 */
data class ItemGene(val name: String, val mass: Double, val value: Double) : Gene {
    override fun toString() = """{ "name": "$name", "mass": $mass, "value": $value }"""
}