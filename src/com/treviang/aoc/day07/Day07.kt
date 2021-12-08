package com.treviang.aoc.day07

import readInput
import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        return findCrabPosition(input)
    }

    fun part2(input: List<String>): Int {
        return findCrabPositionMoreExpensive(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("com/treviang/aoc/day07/Day07_test")
    println(findCrabPositionMoreExpensive(testInput))

    val input = readInput("com/treviang/aoc/day07/Day07")
    println(part1(input))
    println(part2(input))
}

fun findCrabPosition(input: List<String>): Int {
    //initialize structures
    val initialPositions = input[0].split(",").map { it -> it.toInt() }
    var minFuel = Int.MAX_VALUE

    for(i in initialPositions.minOf { it } .. initialPositions.maxOf { it } ) {
        var fuelValue = 0
        initialPositions.forEach{
            fuelValue += abs(i - it)
        }
        if(fuelValue < minFuel) {
            minFuel = fuelValue
        }
    }
    return minFuel
}

fun findCrabPositionMoreExpensive(input: List<String>): Int {
    //initialize structures
    val initialPositions = input[0].split(",").map { it -> it.toInt() }
    var minFuel = Int.MAX_VALUE

    for(i in initialPositions.minOf { it } .. initialPositions.maxOf { it } ) {
        var fuelValue = 0
        initialPositions.forEach{
            fuelValue += calcFuelCost(it, i)
        }
        if(fuelValue < minFuel) {
            minFuel = fuelValue
        }
    }
    return minFuel
}

fun calcFuelCost(start: Int, end: Int): Int {
    var fuelCost = 0
    if (start < end) {
        var increment = 1
        for (i in start+1..end) {
            fuelCost += increment
            increment++
        }
    } else {
        var increment = 1
        for (i in end+1..start) {
            fuelCost += increment
            increment++
        }
    }
    return fuelCost
}