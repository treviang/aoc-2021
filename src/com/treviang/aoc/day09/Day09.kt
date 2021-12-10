package com.treviang.aoc.day09

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        return findLowPoints(input).sumOf { it.value + 1 }
    }

    fun part2(input: List<String>): Int {
        var basinSizes = findBasinSizes(input)
        var sortedBasin = basinSizes.sortedByDescending { it.basinSize }
        return sortedBasin[0].basinSize * sortedBasin[1].basinSize * sortedBasin[2].basinSize
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("com/treviang/aoc/day09/Day09_test")
    println(findBasinSizes(testInput))

    val input = readInput("com/treviang/aoc/day09/Day09")
    println(part1(input))
    println(part2(input))
}

data class LowPoint(var value:Int, var i: Int, var j:Int, var basinSize:Int)
data class CheckedString(var value:Char, var checked:Boolean)

fun findLowPoints(input: List<String>): List<LowPoint> {
    //initialize structures
    var lowPoints = arrayListOf<LowPoint>()

    for (j in input.indices) {
        for (i in input[j].indices) {
            if (input[j].elementAt(i) < input[j].elementAtOrElse(i + 1) { Char.MAX_VALUE }
                && input[j].elementAt(i) < input[j].elementAtOrElse(i - 1) { Char.MAX_VALUE }
                && (j < 1 || input[j].elementAt(i) < input[j - 1].elementAt(i))
                && (j >= input.size - 1 || input[j].elementAt(i) < input[j + 1].elementAtOrElse(i) { Char.MAX_VALUE })) {
                lowPoints.add(LowPoint(input[j].elementAt(i).digitToInt(), i, j, 0))
            }
        }
    }
    return lowPoints
}

fun findBasinSizes(input: List<String>): List<LowPoint> {
    var lowPoints = findLowPoints(input)
    var checkedCharacters = arrayListOf<List<CheckedString>>()
    for (j in input.indices) {
        var checkedString = arrayListOf<CheckedString>()
        for (i in input[j].indices) {
            checkedString.add(CheckedString(input[j][i], false))
        }
        checkedCharacters.add(checkedString)
    }
    for(lowPoint in lowPoints) {
        lowPoint.basinSize = findBasinSize(checkedCharacters, lowPoint.i, lowPoint.j)
    }
    return lowPoints
}

fun findBasinSize(input: List<List<CheckedString>>, i:Int, j:Int):Int {
    if( j<0 || j > input.size - 1 || i<0 || i > input[j].size -1 || input[j][i].checked || input[j][i].value == 9.digitToChar()) {
        return 0
    }
    input[j][i].checked = true
    return 1 + findBasinSize(input, i, j-1)  + findBasinSize(input, i-1, j) + findBasinSize(input, i+1, j) + findBasinSize(input, i, j+1)
}