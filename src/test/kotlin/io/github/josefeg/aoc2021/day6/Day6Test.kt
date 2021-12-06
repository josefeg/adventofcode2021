package io.github.josefeg.aoc2021.day6

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day6Test {
    private val input = listOf(
        3,
        4,
        3,
        1,
        2
                              )

    @Test
    fun `countLanternFishAfterDaysNaive returns the correct result`() {
        Assertions.assertEquals(5934, countLanternFishAfterDaysNaive(80, this.input))
    }

    @Test
    fun `countLanternFishAfterDaysEfficient returns the correct result`() {
        Assertions.assertEquals(BigInteger("5934"), countLanternFishAfterDaysEfficient(80, this.input))
        Assertions.assertEquals(BigInteger("26984457539"), countLanternFishAfterDaysEfficient(256, this.input))
    }
}
