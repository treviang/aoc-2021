import constants.EMPTY_STRING

fun main() {

    fun part1(input: List<String>): Int {
        var power = findPowerConsumption(input)
        return power.gammaRate.toInt() * power.epsilonRate.toInt()
    }

    fun part2(input: List<String>): Int {
        var lsr = findLifeSupportRating(input)
        return lsr.oxygenRating * lsr.cO2Rating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println(findLifeSupportRating(testInput))

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

data class Power(var gammaRate: String, var epsilonRate: String)
data class LifeSupportRating(var oxygenRating: Int, var cO2Rating: Int)

fun findPowerConsumption(input: List<String>): Power {
    var power = findBinaryPowerConsumption(input)
    return Power(power.gammaRate.toInt(2).toString(), power.epsilonRate.toInt(2).toString())
}

fun findBinaryPowerConsumption(input: List<String>): Power {
    var gammaBinary = EMPTY_STRING
    var epsilonBinary = EMPTY_STRING
    val sizeOfElements = input[0].length

    for (i in 0 until sizeOfElements) {
        val numbersByElement = input.groupingBy { it -> it[i] }.eachCount()
        gammaBinary += numbersByElement.maxByOrNull { it.value }?.key
        epsilonBinary += numbersByElement.minByOrNull { it.value }?.key
    }
    return Power(gammaBinary, epsilonBinary);
}

fun findEpsilonGamma(input: List<String>, index: Int): Power {
    var gammaBinary = EMPTY_STRING
    var epsilonBinary = EMPTY_STRING

    val numbersByElement = input.groupingBy { it -> it[index] }.eachCount()
    if (numbersByElement.containsKey('0') && numbersByElement.containsKey('1')
        && numbersByElement.getValue('0') == numbersByElement.getValue('1')
    ) {
        gammaBinary += 1
        epsilonBinary += 0
    } else {
        gammaBinary += numbersByElement.maxByOrNull { it.value }?.key
        epsilonBinary += numbersByElement.minByOrNull { it.value }?.key
    }
    return Power(gammaBinary, epsilonBinary);
}

fun findLifeSupportRating(input: List<String>): LifeSupportRating {

    val sizeOfElements = input[0].length

    var oxygenRatingBinary = EMPTY_STRING
    var cO2RatingBinary = EMPTY_STRING

    var elementsOx = input.toList()
    var elementsCO2 = input.toList()
    var patternOx = EMPTY_STRING
    var patternCO2 = EMPTY_STRING

    for (i in 0 until sizeOfElements) {
        patternOx += findEpsilonGamma(elementsOx, i).gammaRate
        patternCO2 += findEpsilonGamma(elementsCO2, i).epsilonRate
        elementsOx = elementsOx.filter { it.startsWith(patternOx) }
        elementsCO2 = elementsCO2.filter { it.startsWith(patternCO2) }

        if (elementsOx.size == 1) {
            oxygenRatingBinary = elementsOx[0]
        }
        if(elementsCO2.size == 1) {
            cO2RatingBinary = elementsCO2[0]
        }
    }
    return LifeSupportRating(oxygenRatingBinary.toInt(2), cO2RatingBinary.toInt(2))
}