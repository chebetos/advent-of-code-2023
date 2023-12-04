package day02

import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    private val day02 = Day02

    @Test
    fun part1() {
        val testInput = Utils.readInput("Day02_part1_test.input")

        assertEquals(0, day02.part1(testInput))
    }

    @Test
    fun part2() {
    }
}