package day07

import println

fun main() {
    val day07Input = Utils.readInput("day07_input.txt")
    val part1Result = Day07.part1(day07Input)
    part1Result.println()

    val part2Result = Day07.part2(day07Input)
    part2Result.println()
}

object Day07 {
    fun part1(lines: List<String>): Int {
        val hands = lines.map { Hand.fromString(it) }
            .sorted()
        val camelCardGame = CamelCardGame(hands)
        return camelCardGame.totalWinnings()
    }

    fun part2(lines: List<String>): Int {
        return lines.size
    }
}

data class CamelCardGame(val handsAndBids: List<Hand>) {
    fun totalWinnings(): Int {
        val sortedHand = this.handsAndBids.sorted()
        return sortedHand
            .also { it.println() }
            .mapIndexed { index, hand -> (index + 1) * hand.bid }
            .also { it.println() }
            .sum()
    }
}
data class Hand(
    val cards: List<Card>,
    val bid: Int,
    val type: HandType = HandType.fromCards(cards),
) : Comparable<Hand> {
    companion object {
        fun fromString(input: String): Hand {
            require(input.contains(' ')) { "Input should contains only 1 space: '$input'" }
            val inputArray = input.split(' ')
            require(2 == inputArray.size) { "Input should contains only 2 words: '$inputArray'" }
            val bid = inputArray[1].toInt()
            require(5 == inputArray[0].length) { "First word (hand), should contains 5 chars: '${inputArray[0]}'" }
            val cards = inputArray[0].toCharArray().map { Card.fromChar(it) }
            return Hand(cards, bid)
        }
    }
    override fun compareTo(other: Hand): Int {
        val comparisonByType = this.type.compareTo(other.type)
        if (0 != comparisonByType) {
            return comparisonByType
        }

        for (i in 0..5) {
            val comparisonByCard = this.cards[i].compareTo(other.cards[i])
            if (0 != comparisonByCard) {
                return comparisonByCard
            }
        }

        return 0
    }
}
enum class HandType {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND;

    companion object {
        fun fromCards(cards: List<Card>): HandType {
            require(5 == cards.size) { "Invalid card list, it doesn't have 5 cards: $cards" }

            val cardCount: Map<Card, Int> = cards
                .groupBy { it }
                .mapValues { it.value.size }

            if (cardCount.any { 5 == it.value })
                return FIVE_OF_A_KIND
            if (cardCount.any { 4 == it.value })
                return FOUR_OF_A_KIND
            if (cardCount.any { 3 == it.value } && cardCount.any { 2 == it.value })
                return FULL_HOUSE
            if (cardCount.any { 3 == it.value })
                return THREE_OF_A_KIND
            if (2 == cardCount.count {
                2 == it.value }
            )
                return TWO_PAIR


            if (1 == cardCount.count {
                2 == it.value }
            )
                return ONE_PAIR

            if (cardCount.all { 1 == it.value })
                return HIGH_CARD

            throw IllegalArgumentException("Unrecognized game type - card count: $cardCount")
        }
    }
}

enum class Card (val charCard: Char) {

    TWO('2'),
    THREE( '3'),
    FOUR( '4'),
    FIVE( '5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    T('T'),
    J('J'),
    Q('Q'),
    K('K'),
    A('A');

    override fun toString(): String {
        return charCard.toString()
    }
    
    companion object {
        fun fromChar(card: Char): Card {
            val cardFound = Card.entries.find { it.charCard == card }
            checkNotNull(cardFound) { "Provided card is not valid: $card" }
            return cardFound
        }
    }
}