package io.github.josefeg.aoc2021.day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {
    private val input = listOf(
        199,
        200,
        208,
        210,
        200,
        207,
        240,
        269,
        260,
        263
                                  )

    private val d = Day1()

    @Test
    fun `countIncrements returns the correct result`() {
        assertEquals(7, this.d.countIncrements(this.input))
    }

    @Test
    fun `countThreeMeasurementsSlidingIncrements returns the correct result`() {
        assertEquals(5, this.d.countThreeMeasurementsSlidingIncrements(this.input))
    }
}
