fun main() {

    fun part1(input: List<String>): Int {
        return countIncreases(input);
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    //println(countIncreases(testInput))

    val input = readInput("Day01")
    println(part1(input))
    //println(part2(input))
}

fun countIncreases(input: List<String>): Int {
    var count = 0

    for ((index, value) in input.withIndex()) {
        if(index > 0 && value > input[index-1]) {
            count ++
        }
    }
    return count;
}