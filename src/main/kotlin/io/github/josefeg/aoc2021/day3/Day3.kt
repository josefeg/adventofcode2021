package io.github.josefeg.aoc2021.day3

import java.io.File
import kotlin.math.pow

fun calculatePowerConsumption(input: List<String>): Int {
    val counts = input[0].foldIndexed(listOf()) { index, acc: List<Int>, _ -> acc + listOf(input.count { it[index] == '1' }) }

    val threshold = input.size / 2
    val gamma = binaryToDecimal(counts.fold("") { acc, value -> acc + if (value > threshold) "1" else "0" })
    val epsilon = binaryToDecimal(counts.fold("") { acc, value -> acc + if (value < threshold) "1" else "0" })

    return gamma * epsilon
}

fun calculateLifeSupportRating(readings: List<String>): Int {
    fun calculateLifeSupportRating(index: Int, cmp: (Char, Char) -> Boolean, input: List<String>): String {
        val threshold = input.size / 2.0
        val mostCommonBit = if (input.fold(0) { acc, value -> acc + (if (value[index] == '1') 1 else 0) } >= threshold) '1' else '0'
        val haveMostCommonBit = input.filter { cmp(it[index], mostCommonBit) }
        if (haveMostCommonBit.size == 1) {
            return haveMostCommonBit[0]
        }
        return calculateLifeSupportRating(index + 1, cmp, haveMostCommonBit)
    }

    val o2 = binaryToDecimal(calculateLifeSupportRating(0, { c1, c2 -> c1 == c2 }, readings))
    val co2 = binaryToDecimal(calculateLifeSupportRating(0, { c1, c2 -> c1 != c2 }, readings))

    return o2 * co2
}

fun binaryToDecimal(bin: String): Int {
    return bin.reversed().foldIndexed(0) { index, acc, c -> acc + if (c == '0') 0 else 2.0.pow(index).toInt() }
}

fun main() {
    val input = File("src/main/resources/day3.txt")
        .readLines()

    println(calculatePowerConsumption(input))
    println(calculateLifeSupportRating(input))
}
