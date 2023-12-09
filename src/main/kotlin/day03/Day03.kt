package day03

import Utils
import println
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    val day03Input = Utils.readInput("day03_input.txt")
    val part1Result = Day03.part1(day03Input)
    part1Result.println()

    val part2Result = Day03.part2(day03Input)
    part2Result.println()
}

private const val gearSymbol = '*'

object Day03 {
    private val numbersRegex = Regex("\\d*")

    fun part1(lines: List<String>): Int {

        val sumOfNumbersAdjacentToSymbol = AtomicInteger(0)
        for (i in lines.indices) {
            val line = lines[i]
            val sumOfNumbersAdjacentToSymbolInLine: Int = numbersRegex
                //extract the numbers from the line
                .findAll(line).filter { it.value.isNotEmpty() }
                .filter {
                    val firstCharIdx = it.range.first
                    val lastCharIdx = it.range.last

                    hasAdjacentSymbol(firstCharIdx, lastCharIdx, lines, i)
                }
                //.also { it.println() }
                .map { it.value.toInt() }
                .sum()

            sumOfNumbersAdjacentToSymbol.addAndGet(sumOfNumbersAdjacentToSymbolInLine)
        }
        return sumOfNumbersAdjacentToSymbol.get()
    }

    private fun hasAdjacentSymbol(
        firstCharIdx: Int,
        lastCharIdx: Int,
        lines: List<String>,
        currentLine: Int,
    ): Boolean {
        val line = lines[currentLine]

        val isFirstLine = 0 == currentLine
        val isLastLine = (lines.size -1) == currentLine

        val firstNumberCharIsFirstLineChar = firstCharIdx == 0
        val lastNumberCharIsLastLineChar = lastCharIdx == (line.length - 1)

        val firstCharIdxToEval = if (firstNumberCharIsFirstLineChar)
            firstCharIdx
        else
            firstCharIdx - 1

        val lastCharIdxToEval = if (lastNumberCharIsLastLineChar)
            lastCharIdx
        else
            lastCharIdx + 1

        val rangeToEval = IntRange(firstCharIdxToEval, lastCharIdxToEval)

        if (anyCharIsAValidSymbol(line, rangeToEval))
            return true

        if (!isFirstLine) {
            val previousLine = lines[currentLine - 1]
            if (anyCharIsAValidSymbol(previousLine, rangeToEval))
                return true
        }

        if (!isLastLine) {
            val nextLine = lines[currentLine + 1]
            if (anyCharIsAValidSymbol(nextLine, rangeToEval))
                return true
        }

        return false
    }

    private fun anyCharIsAValidSymbol(line: String, rangeToEval: IntRange) =
        line.substring(rangeToEval).any { c -> isAValidSymbol(c) }

    private fun isAValidSymbol(char: Char) = char != '.' && !char.isDigit()

    fun part2(lines: List<String>): Int {
        val gearSymbolRegex = Regex("\\*")

        val sumOfGearRatios = AtomicInteger(0)

        for (i in lines.indices) {
            val line = lines[i]
            if (!line.contains(gearSymbol)) {
                continue
            }

            val isFirstLine = 0 == i
            val isLastLine = (lines.size -1) == i


            val gearSymbolMatches = gearSymbolRegex.findAll(line)

            val numbersInLine = numbersRegex.findAll(line).filter { it.value.isNotEmpty() }

            val numbersInPreviousLine: Sequence<MatchResult> = if (!isFirstLine)
                numbersRegex.findAll(lines[i-1]).filter { it.value.isNotEmpty() }
            else
                emptySequence()

            val numbersInNextLine: Sequence<MatchResult> = if (!isLastLine)
                numbersRegex.findAll(lines[i+1]).filter { it.value.isNotEmpty() }
            else
                emptySequence()

            for (gearSymbolMatch in gearSymbolMatches) {
                println("* in $i - ${gearSymbolMatch.range}")
                val gearSymbolAdjacentRange = (gearSymbolMatch.range.first - 1)..(gearSymbolMatch.range.last + 1)
                val adjacentNumbers: List<Int> =(
                    numbersInPreviousLine.filter { rangesOverlap(gearSymbolAdjacentRange, it.range) }.map { it.value.toInt() } +
                            numbersInLine.filter { rangesOverlap(gearSymbolAdjacentRange, it.range) }.map { it.value.toInt() } +
                        numbersInNextLine.filter { rangesOverlap(gearSymbolAdjacentRange, it.range) }.map { it.value.toInt() }
                ).toList()

                println("adjacentNumbers:\t${adjacentNumbers}")
                if (adjacentNumbers.size != 2) {
                    continue
                }
                val gearRatio = adjacentNumbers[0] * adjacentNumbers[1]
                sumOfGearRatios.addAndGet(gearRatio)
            }
        }

        return sumOfGearRatios.get()
    }
    
    fun rangesOverlap(range1: IntRange, range2: IntRange): Boolean {
        return (range1.first <= range2.last) && (range1.last >= range2.first)
    }
}


