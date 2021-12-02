package io.github.josefeg.aoc2021.day1

import java.io.File

fun countIncrements(measurements: List<Int>): Int {
    return measurements.foldIndexed(0) { index, acc, item -> if (index == 0) 0 else if (item > measurements[index - 1]) acc + 1 else acc }
}

fun countThreeMeasurementsSlidingIncrements(measurements: List<Int>): Int {
    val sumWindow = { index: Int -> measurements[index] + measurements[index - 1] + measurements[index - 2] }
    return measurements.foldIndexed(0) { index, acc, _ -> if (index < 3) 0 else if (sumWindow(index) > sumWindow(index - 1)) acc + 1 else acc }
}

fun main() {
    val measurements = File("src/main/resources/day1.txt")
        .readLines()
        .map(String::toInt)

    println(countIncrements(measurements))
    println(countThreeMeasurementsSlidingIncrements(measurements))
}
