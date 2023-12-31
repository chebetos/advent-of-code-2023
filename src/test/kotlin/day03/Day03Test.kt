package day03

import Utils
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    @Test
    fun part1() {
        val testInput = Utils.readInput("Day03_part1_test.input")

        assertEquals(4361, Day03.part1(testInput))
    }

    @Test
    fun part2() {
        val testInput = Utils.readInput("Day03_part1_test.input")
        assertEquals(467835, Day03.part2(testInput))
    }
}