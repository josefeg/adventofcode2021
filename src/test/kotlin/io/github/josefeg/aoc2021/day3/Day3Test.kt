package io.github.josefeg.aoc2021.day3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day3Test {
    private val input = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
                              )

    @Test
    fun `calculatePowerConsumption returns the correct result`() {
        Assertions.assertEquals(198, calculatePowerConsumption(this.input))
    }

    @Test
    fun `calculateLifeSupportRating returns the correct result`() {
        Assertions.assertEquals(230, calculateLifeSupportRating(this.input))
    }
}
