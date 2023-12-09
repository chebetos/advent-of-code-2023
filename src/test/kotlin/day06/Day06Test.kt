package day06

import Utils
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

    private val testInput = Utils.readInput("Day06_part1_text.input")
    @Test
    fun part1() {
        assertEquals(288, Day06.part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals(71503, Day06.part2(testInput))
    }
}