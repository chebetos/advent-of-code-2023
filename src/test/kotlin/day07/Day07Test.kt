package day07

import Utils
import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {
    @Test
    fun day07Part1() {
        // test if implementation meets criteria from the description, like:
        val testInput = Utils.readInput("Day07_part1_test.input")
        assertEquals(6440, Day07.part1(testInput))
    }

    @Test
    fun day07Part2() {
        // test if implementation meets criteria from the description, like:
        val testInput = Utils.readInput("Day07_part1_test.input")
        assertEquals(5905, Day07Part2.part2(testInput))
    }
}