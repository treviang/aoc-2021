fun main() {

    fun part1(input: List<String>): Int {
        var position = findPosition(input)
        return position.horPosition * position.depth;
    }

    fun part2(input: List<String>): Int {
        var position = findPositionComplicated(input)
        return position.horPosition * position.depth;
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(findPositionComplicated(testInput))

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

data class Position(var depth: Int, var horPosition: Int, var aim: Int)

fun findPosition(input: List<String>): Position {

    var position = Position (0, 0, 0);

    for (instruction in input) {
        var commandList = instruction.split(" ");

        when(commandList[0]) {
            "forward" -> position.horPosition += commandList[1].toInt()
            "down" -> position.depth += commandList[1].toInt()
            "up" -> position.depth -= commandList[1].toInt()
        }
    }
    return position;
}

fun findPositionComplicated(input: List<String>): Position {

    var position = Position (0, 0, 0);

    for (instruction in input) {
        var commandList = instruction.split(" ");

        when(commandList[0]) {
            "down" -> position.aim += commandList[1].toInt()
            "up" -> position.aim -= commandList[1].toInt()
            "forward" -> {
                position.horPosition += commandList[1].toInt()
                position.depth += position.aim * commandList[1].toInt()
            }
        }
    }
    return position;
}