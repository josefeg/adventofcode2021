package io.github.josefeg.aoc2021.day5

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Point(val x: Int, val y: Int) {
    companion object {
        fun fromString(input: String): Point {
            val coordinates = input.split(",").map { it.toInt() }
            return Point(coordinates[0], coordinates[1])
        }
    }
}

data class Line(val source: Point, val target: Point) {
    companion object {
        fun fromString(input: String): Line {
            val points = input.split(" -> ").map { Point.fromString(it) }
            return Line(points[0], points[1])
        }
    }

    fun isHorizontal(): Boolean = source.x == target.x

    fun isVertical(): Boolean = source.y == target.y

    fun toPoints(): List<Point> {
        return if (this.isHorizontal()) {
            (min(source.y, target.y)..max(source.y, target.y)).map { Point(source.x, it) }
        } else if (this.isVertical()) {
            (min(source.x, target.x)..max(source.x, target.x)).map { Point(it, source.y) }
        } else {
            val xDelta = if (source.x < target.x) 1 else -1
            val yDelta = if (source.y < target.y) 1 else -1
            val range = (0..(abs(source.x - target.x)))

            range.map { Point(source.x + (xDelta * it), source.y + (yDelta * it)) }
        }
    }
}

fun parseInput(input: List<String>): List<Line> = input.map { Line.fromString(it) }

fun findGridSize(lines: List<Line>): Pair<Int, Int> {
    return lines.fold(Pair(0, 0)) { (x, y), line ->
        Pair(max(x, max(line.source.x, line.target.x)), max(y, max(line.source.y, line.target.y)))
    }
}

fun countOverlappingPoints(lines: List<Line>): Int {
    val (x, y) = findGridSize(lines)
    val grid = MutableList(x + 1) { MutableList(y + 1) { 0 } }

    lines.filter { it.isHorizontal() || it.isVertical() }
        .flatMap { it.toPoints() }
        .forEach { grid[it.x][it.y] += 1 }

    return grid.fold(0) { acc, row ->
        row.fold(acc) { internalAcc, point -> internalAcc + (if (point > 1) 1 else 0) }
    }
}

fun countOverlappingPointsWithDiagonals(lines: List<Line>): Int {
    val (x, y) = findGridSize(lines)
    val grid = MutableList(x + 1) { MutableList(y + 1) { 0 } }

    lines.flatMap { it.toPoints() }
        .forEach { grid[it.x][it.y] += 1 }

    return grid.fold(0) { acc, row ->
        row.fold(acc) { internalAcc, point -> internalAcc + (if (point > 1) 1 else 0) }
    }
}

fun main() {
    val input = File("src/main/resources/day5.txt")
        .readLines()

    val lines = parseInput(input)
    // println(countOverlappingPoints(lines)) // 4655
    // println(countOverlappingPointsWithDiagonals(lines)) // 20500
}
