package day02

import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    private val day02 = Day02

    @Test
    fun part1() {
        val testInput = Utils.readInput("Day02_part1_test.input")
        val bagSetup = CubeSet(red = 12, green = 13, blue = 14)

        assertEquals(8, day02.part1(bagSetup, testInput))
    }

    @Test
    fun part2() {
        val testInput = Utils.readInput("Day02_part2_test.input")
        assertEquals(2286, day02.part2(testInput))
    }
}