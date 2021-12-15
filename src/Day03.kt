import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

fun main() {
    fun gamma(input: List<String>): Int {
        val count = input.size
        return input[0].mapIndexed { charIndex, _ ->
            if (input.map { it[charIndex] }.count { it == '1' } >= (count / 2)) '1' else '0'
        }.joinToString("")
            .toInt(2)
    }
    fun epsilon(input: List<String>): Int {
        val count = input.size
        return input[0].mapIndexed { charIndex, _ ->
            if (input.map { it[charIndex] }.count { it == '1' } <= (count / 2)) '1' else '0'
        }.joinToString("")
            .toInt(2)
    }

    fun oxyGenRating(input: List<String>): Int {
        var rating = 0
        var remaining = input
        input[0].forEachIndexed { charIndex, _ ->
            val dividend = ceil(remaining.size / 2.0)
            val commonBit = if (remaining.map { it[charIndex] }.count { it == '1' } >= dividend) '1' else '0'
            println("commonBit[$charIndex] == $commonBit")
            remaining = remaining.filter { it[charIndex] == commonBit }
            println("Remaining[$charIndex]: ${remaining.joinToString() }}")
            if(remaining.size == 1) {
                rating = remaining[0].toInt(2)
            }
        }
        remaining.forEach { println(it)}
        return rating
    }
    fun co2scrubberRating(input: List<String>): Int {
        var rating = 0
        var remaining = input
        input[0].forEachIndexed { charIndex, _ ->
            val dividend = floor(remaining.size / 2.0)
            val zeroCount = remaining.map { it[charIndex] }.count { it == '0' }
            val commonBit = if(zeroCount.toDouble() == (remaining.size.toDouble() / 2.0)) '0' else if(zeroCount <= dividend) '0' else '1'
            println("commonBit[$charIndex] == $commonBit")
            remaining = remaining.filter { it[charIndex] == commonBit }
            println("Remaining[$charIndex]: ${remaining.joinToString() }}")
            if(remaining.size == 1) {
                rating = remaining[0].toInt(2)
            }
        }
        remaining.forEach { println(it)}
        return rating
    }

    fun part1(input: List<String>): Int {
        return gamma(input) * epsilon(input)
    }

    fun part2(input: List<String>): Int {
        return oxyGenRating(input) * co2scrubberRating(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val part1test = part1(testInput)
    println("Part 1 TEST: $part1test")
    check(part1test == 198)
//    check(part2(testInput) == 5)
    val input = readInput("Day03")
    println("Part 1: " + part1(input))
    println("Part 2 Oxy: ${oxyGenRating(testInput)}")
    println("Part 2 Co2: ${co2scrubberRating(testInput)}")
    println("Part 2: " + part2(input))
}
