package day08

import org.junit.jupiter.api.Test

class Day08Test {

    @Test
    fun part1() {
        val testInput = Utils.readInput("Day08_part1_text.input")
        val networkA = Day08.part1(testInput)
        kotlin.test.assertEquals(2, networkA)

        val testInputB = Utils.readInput("Day08_part1b_text.input")
        val networkB = Day08.part1(testInputB)
        kotlin.test.assertEquals(6, networkB)

    }

    @Test
    fun part2() {
    }
}