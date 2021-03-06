package com.treviang.aoc.day01

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        return countIncreases(input.map { it.toInt() })
    }

    fun part2(input: List<String>): Int {
        return countIncreasesWithWindow(input.map { it.toInt() }, 3)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("com/treviang/aoc/day01/Day01_test")
    println(countIncreases(testInput.map { it.toInt() }))

    val input = readInput("com/treviang/aoc/day01/Day01")
    println(part1(input))
    println(part2(input))
}

fun countIncreases(input: List<Int>): Int {
    return countIncreasesWithWindow(input, 1)
}

fun countIncreasesWithWindow(input: List<Int>, windowSize: Int): Int {

    var lastWindowSum = 0
    var count = 0

    for (index in input.indices) {

        if (index + windowSize - 1 < input.size) {
            var currentWindow = (index until index + windowSize).map { i: Int -> input[i] }
                .toIntArray()

            if (currentWindow.size == windowSize && lastWindowSum > 0 && currentWindow.sum() > lastWindowSum) {
                count++
            }
            lastWindowSum = currentWindow.sum()
        }
    }

    return count
}