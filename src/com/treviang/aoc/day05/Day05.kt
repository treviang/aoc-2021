package com.treviang.aoc.day05

import com.treviang.aoc.constants.EMPTY_STRING
import readInput

fun main() {

    fun part1(input: List<String>): Int {
        return crossLine(input)
    }

    //fun part2(input: List<String>): Int {
    //var lsr = com.treviang.aoc.day03.findLifeSupportRating(input)
    //return lsr.oxygenRating * lsr.cO2Rating
    //}

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("com/treviang/aoc/day05/Day05_test")
    println(crossLine(testInput))

    val input = readInput("com/treviang/aoc/day05/Day05")
    println(part1(input))
    //println(part2(input))
}

data class Point(var x: Int, var y: Int)
data class Coordinate(var p1: Point, var p2: Point)

fun crossLine(input: List<String>) :Int{
    var coordinates: MutableList<Coordinate> = mutableListOf()
    input.forEach() {
        var points = it.split("->").map { it ->
            Point(it.split(",")[0].trim().toInt(), it.split(",")[1].trim().toInt())
        }
        coordinates.add(Coordinate(points[0], points[1]))
    }
    var grid = Array(1000) { Array(1000) { 0 } }

    for (i in 0 until coordinates.size) {
        if (coordinates[i].p1.x == coordinates[i].p2.x) {
            if(coordinates[i].p1.y < coordinates[i].p2.y) {
                for(y in coordinates[i].p1.y .. coordinates[i].p2.y)
                grid[coordinates[i].p1.x][y] ++
            } else {
                for(y in coordinates[i].p2.y .. coordinates[i].p1.y)
                    grid[coordinates[i].p1.x][y] ++
            }
        } else if (coordinates[i].p1.y == coordinates[i].p2.y) {
            if(coordinates[i].p1.x < coordinates[i].p2.x) {
                for(x in coordinates[i].p1.x .. coordinates[i].p2.x)
                    grid[x][coordinates[i].p1.y] ++
            } else {
                for(x in coordinates[i].p2.x .. coordinates[i].p1.x)
                    grid[x][coordinates[i].p1.y] ++
            }
        }
    }

    var count = 0
    grid.forEach { r ->
        r.forEach { c ->
            if (c >= 2) {
                count++
            }
        }
    }
    return count
}