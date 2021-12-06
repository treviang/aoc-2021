package com.treviang.aoc.day04

import com.treviang.aoc.constants.EMPTY_STRING
import readInput

fun main() {

    //fun part1(input: List<String>): Int {
        //var power = com.treviang.aoc.day03.findPowerConsumption(input)
        //return power.gammaRate.toInt() * power.epsilonRate.toInt()
    //}

    //fun part2(input: List<String>): Int {
        //var lsr = com.treviang.aoc.day03.findLifeSupportRating(input)
        //return lsr.oxygenRating * lsr.cO2Rating
    //}

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    println(prepareGame(testInput))

    val input = readInput("Day04")
    //println(part1(input))
    //println(part2(input))
}

data class Bingo(var extractions: List<Int>, var cards: List<List<Array<Card>>>)
data class Card(var value: Int, var checked: Boolean)

fun prepareGame(input: List<String>) {
    var extractions = input[0].split(",").map { it -> it.toInt() }

    var cards :List<List<Array<Card>>>
    var rowNumber = 0
    var card = arrayListOf<Array<Card>>()

    for (i in 2 until input.size) {
        if (input[i] == EMPTY_STRING) {
            //cards.add(card)
            rowNumber = 0
           // card = List<com.treviang.aoc.day04.Card>
        } else {
            var row = input[i].split(" ").map { it -> it.toInt() }
            card[rowNumber] = row.map { it -> Card(it, false) }.toTypedArray()
            rowNumber ++
        }
    }

    //return com.treviang.aoc.day04.Bingo (extractions, List(1))
}