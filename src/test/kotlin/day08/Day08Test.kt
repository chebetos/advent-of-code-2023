package day08

import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {

    @Test
    fun part1() {
        val testInput = Utils.readInput("Day08_part1_text.input")
        val networkA = Day08.part1(testInput)
        assertEquals(2, networkA)

        val testInputB = Utils.readInput("Day08_part1b_text.input")
        val networkB = Day08.part1(testInputB)
        assertEquals(6, networkB)
    }

    @Test
    fun part2() {
        val testInput = Utils.readInput("Day08_part2_text.input")
        val networkA = Day08.part2(testInput)
        assertEquals(6L, networkA)
    }
}