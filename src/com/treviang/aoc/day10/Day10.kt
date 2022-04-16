package com.treviang.aoc.day10

import readInput

import readInput

fun main() {

    //fun part1(input: List<String>): Int {
    //    return findLowPoints(input).sumOf { it.value + 1 }
    //}

    //fun part2(input: List<String>): Int {
    //}

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("com/treviang/aoc/day10/Day10_test")
    println(findCorruptedCharacters(testInput))

    val input = readInput("com/treviang/aoc/day10/Day10")
    //println(part1(input))
    //println(part2(input))
}

data class LowPoint(var value:Int, var i: Int, var j:Int, var basinSize:Int)
data class CheckedString(var value:Char, var checked:Boolean)

fun findCorruptedCharacters(input: List<String>): List<Char> {
    //initialize structures
    var occurrences = HashMap<String, Int>()
    var corruptedCharacters = arrayListOf<Char>()

    for (line in input) {
        occurrences["("] = 0
        occurrences["["] = 0
        occurrences["{"] = 0
        occurrences["<"] = 0
        for (char in line) {
            when (char) {
                ')' -> {
                    if(occurrences["("]!! == 0) {
                        corruptedCharacters.add(char)
                        break
                    } else {
                        occurrences.merge("(", 1, Int::minus)
                    }
                }
                ']' -> {
                    if(occurrences["["]!! == 0) {
                        corruptedCharacters.add(char)
                        break
                    } else {
                        occurrences.merge("[", 1, Int::minus)
                    }
                }
                '}' -> {
                    if(occurrences["{"]!! == 0) {
                        corruptedCharacters.add(char)
                        break
                    } else {
                        occurrences.merge("{", 1, Int::minus)
                    }
                }
                '>' -> {
                    if(occurrences["<"]!! == 0) {
                        corruptedCharacters.add(char)
                        break
                    } else {
                        occurrences.merge("<", 1, Int::minus)
                    }
                }
                else -> occurrences.merge(char.toString(), 1, Int::plus)

            }
        }
    }
    return corruptedCharacters
}