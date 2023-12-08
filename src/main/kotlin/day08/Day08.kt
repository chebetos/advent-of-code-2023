package day08

import println
import java.time.Duration
import java.time.Instant

fun main() {
    val day01Input = Utils.readInput("day08_input.txt")
    val part1Result = Day08.part1(day01Input)
    part1Result.println()

    val part2Result = Day08.part2(day01Input)
    part2Result.println()
}

object Day08 {
    fun part1(lines: List<String>): Int {
        val network = Network.fromString(lines)
        println("Network: $network")
        return network.navigate()
    }

    fun part2(lines: List<String>): Long {
        val network = Network.fromString(lines)
        return network.navigateFromNodesEndingWithA()
    }
}

data class Network(
    val leftRightInstructions: String,
    val network: Map<String, Pair<String, String>>
) {
    companion object {
        fun fromString(lines: List<String>): Network {
            val leftRightInstructions = lines[0]
            val network = LinkedHashMap<String, Pair<String, String>>()
            lines.drop(2).forEach {
                val lineArray = it.split('=').map { s -> s.trim() }
                val key = lineArray[0]
                val values = lineArray[1]
                    .removeSurrounding("(", ")")
                    .split(',')
                    .map{ it.trim() }
                val value = Pair(values[0], values[1])
                network.put(key, value)
            }

            return Network(leftRightInstructions, network)
        }
    }

    fun navigate(): Int {
        var counter = 0
        var currentInstruction = 0
        var currentPos = "AAA"
        while ("ZZZ" != currentPos) {
            counter++

            val currentPositionNetworkElement = network.getValue(currentPos)
            val whereToGo = leftRightInstructions[currentInstruction]
            when (whereToGo) {
                'L' -> currentPos = currentPositionNetworkElement.first
                'R' -> currentPos = currentPositionNetworkElement.second
                else -> println("Invalid instruction: $whereToGo")
            }
            currentInstruction++
            if (leftRightInstructions.length == currentInstruction)  {
                currentInstruction = 0
            }
        }
        return counter
    }

    fun navigateFromNodesEndingWithA(): Long {
        var counter = 0L
        var currentInstruction = 0

        val nodesEndingWithA = network.keys.filter { it.endsWith('A') }
        val nodesEndingWithZ = network.keys.filter { it.endsWith('Z') }

        val currentPositions = nodesEndingWithA.toMutableList()
        currentPositions.println()
        nodesEndingWithZ.println()

        val startingAt = Instant.now()
        while (currentPositions.any { !it.endsWith('Z') }) {
            counter++

            val whereToGo = leftRightInstructions[currentInstruction]
            val newPositions = currentPositions.map { makeMovement(it, whereToGo) }

            for (i in currentPositions.indices) {
                currentPositions[i] = newPositions[i]
            }

            currentInstruction++
            if (leftRightInstructions.length == currentInstruction)  {
                currentInstruction = 0
            }
            if (counter % 100_000_000L == 0L ||
                currentPositions.count { it.endsWith('Z') } > 3) {
                println("${Duration.between(startingAt, Instant.now())} - Iteration: $counter. Current positions: $currentPositions")

            }
        }
        currentPositions.println()
        println("${Duration.between(startingAt, Instant.now())} - Iteration: $counter. Current positions: $currentPositions")
        return counter
    }

    private fun makeMovement(currentPosition: String, whereToGo: Char): String {
        val currentPositionNetworkElement = network.getValue(currentPosition)
        val nextPosition: String? = when (whereToGo) {
            'L' -> currentPositionNetworkElement.first
            'R' -> currentPositionNetworkElement.second
            else -> null
        }
        checkNotNull(nextPosition) { "Invalid instruction where to go: $whereToGo" }
        return nextPosition
    }
}