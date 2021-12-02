package io.github.josefeg.aoc2021.day2

import java.io.File

fun calculatePosition(movements: List<String>): Int {
    val (depth, forward) = movements.map { Movement.parse(it) }
        .fold(Pair(0, 0)) { acc, mvmt ->
            val (depth, forward) = acc
            return@fold when (mvmt.direction) {
                "forward" -> Pair(depth, forward + mvmt.amount)
                "down" -> Pair(depth + mvmt.amount, forward)
                "up" -> Pair(depth - mvmt.amount, forward)
                else -> acc
            }
        }

    return depth * forward
}

fun calculatePositionWithAim(movements: List<String>): Int {
    val (depth, forward, _) = movements.map { Movement.parse(it) }
        .fold(Triple(0, 0, 0)) { acc, mvmt ->
            val (depth, forward, aim) = acc
            return@fold when (mvmt.direction) {
                "forward" -> Triple(depth + (aim * mvmt.amount), forward + mvmt.amount, aim)
                "down" -> Triple(depth, forward, aim + mvmt.amount)
                "up" -> Triple(depth, forward, aim - mvmt.amount)
                else -> acc
            }
        }

    return depth * forward
}

data class Movement(val direction: String, val amount: Int) {
    companion object {
        fun parse(m: String): Movement {
            val parts = m.split(" ")
            val amount = parts[1].toInt()
            return Movement(parts[0], amount)
        }
    }
}

fun main() {
    val movements = File("src/main/resources/day2.txt")
        .readLines()

    println(calculatePosition(movements))
    println(calculatePositionWithAim(movements))
}

