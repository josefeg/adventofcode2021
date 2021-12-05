package io.github.josefeg.aoc2021.day5

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day5Test {
    private val input = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
                              )

    @Test
    fun `countOverlappingPoints returns the correct result`() {
        val lines = parseInput(this.input)
        Assertions.assertEquals(5, countOverlappingPoints(lines))
    }

    @Test
    fun `countOverlappingPointsWithDiagonals returns the correct result`() {
        val lines = parseInput(this.input)
        Assertions.assertEquals(12, countOverlappingPointsWithDiagonals(lines))
    }
}
