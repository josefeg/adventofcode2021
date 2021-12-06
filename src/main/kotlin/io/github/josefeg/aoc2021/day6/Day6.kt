package io.github.josefeg.aoc2021.day6

import java.io.File
import java.math.BigInteger

fun <T> List<T>.replaceItemAt(index: Int, newValue: T): List<T> = mapIndexed { i, t -> if (index == i) newValue else t }

fun countLanternFishAfterDaysNaive(days: Int, initial: List<Int>): Int {
    val x = (1..days).fold(initial) { acc, _ ->
        acc.flatMap { if (it > 0) listOf(it - 1) else listOf(6, 8) }
    }
    return x.size
}

fun countLanternFishAfterDaysEfficient(days: Int, initial: List<Int>): BigInteger {
    val initialGrouped = initial.fold(List(9) { BigInteger("0") }) { acc, fish ->
        acc.replaceItemAt(fish, acc[fish].inc())
    }

    val groupsAfterDays = (1..days).fold(initialGrouped) { acc, _ ->
        val aboutToReproduce = acc.first()
        val rolled = acc.subList(1, acc.size) + aboutToReproduce
        rolled.replaceItemAt(6, rolled[6] + aboutToReproduce)
    }

    return groupsAfterDays.fold(BigInteger("0")) { acc, value -> acc.add(value) }
}

fun main() {
    val initial = File("src/main/resources/day6.txt")
        .readLines()
        .flatMap { it.split(",") }
        .map { it.toInt() }

    println(countLanternFishAfterDaysNaive(18, initial))
    println(countLanternFishAfterDaysEfficient(256, initial))
}
