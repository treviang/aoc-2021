package com.treviang.aoc.day06

import readInput
import java.math.BigInteger

fun main() {

    fun part1(input: List<String>): BigInteger {
        return findNumberOfFish(input, 80)
    }

    fun part2(input: List<String>): BigInteger {
        return findNumberOfFish(input, 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("com/treviang/aoc/day06/Day06_test")
    println(findNumberOfFish(testInput, 256))

    val input = readInput("com/treviang/aoc/day06/Day06")
    println(part1(input))
    println(part2(input))
}

fun findNumberOfFish(input: List<String>, days: Int): BigInteger {
    //initialize structures
    var initialFishes = input[0].split(",").map { it -> it.toInt() }
    val fishesPopulation = Array(9) { BigInteger("0") }

    //fill initial population
    initialFishes.forEach { fishesPopulation[it]++; }

    for(i in 1.. days) {
        var newLanternFish = fishesPopulation[0]
        for(i in 0 until fishesPopulation.size-1) {
            fishesPopulation[i] = fishesPopulation[(i + 1)]
        }
        fishesPopulation[6] += newLanternFish
        fishesPopulation[fishesPopulation.size-1] = newLanternFish
    }
    return fishesPopulation.sumOf { it }
}