package day08

import println

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

    fun part2(lines: List<String>): Int {
        return lines.size
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
        while (!"ZZZ".equals(currentPos)) {
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
}