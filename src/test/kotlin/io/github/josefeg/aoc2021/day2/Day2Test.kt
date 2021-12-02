package io.github.josefeg.aoc2021.day2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day2Test {
    private val input = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2"
                              )

    @Test
    fun `calculatePosition returns the correct result`() {
        assertEquals(150, calculatePosition(this.input))
    }

    @Test
    fun `calculatePositionWithAim returns the correct result`() {
        assertEquals(900, calculatePositionWithAim(this.input))
    }
}
