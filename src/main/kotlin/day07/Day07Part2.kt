package day07

import println

fun main() {
    val day07Input = Utils.readInput("day07_input.txt")
    val part2Result = Day07Part2.part2(day07Input)
    part2Result.println()
}

object Day07Part2 {
    fun part2(lines: List<String>): Int {
        val hands = lines.map { HandPart2.fromString(it) }
            .sorted()
        val camelCardGame = CamelCardGamePart2(hands)
        return camelCardGame.totalWinnings()
    }
}

data class CamelCardGamePart2(val handsAndBids: List<HandPart2>) {
    fun totalWinnings(): Int {
        val sortedHand = this.handsAndBids.sorted()
        return sortedHand
            .also { it.println() }
            .mapIndexed { index, hand -> (index + 1) * hand.bid }
            .also { it.println() }
            .sum()
    }
}
data class HandPart2(
    val cards: List<CardPart2>,
    val bid: Int,
    val type: HandTypePart2 = HandTypePart2.fromCards(cards),
) : Comparable<HandPart2> {
    companion object {
        fun fromString(input: String): HandPart2 {
            require(input.contains(' ')) { "Input should contains only 1 space: '$input'" }
            val inputArray = input.split(' ')
            require(2 == inputArray.size) { "Input should contains only 2 words: '$inputArray'" }
            val bid = inputArray[1].toInt()
            require(5 == inputArray[0].length) { "First word (hand), should contains 5 chars: '${inputArray[0]}'" }
            val cards = inputArray[0].toCharArray().map { CardPart2.fromChar(it) }
            return HandPart2(cards, bid)
        }
    }
    override fun compareTo(other: HandPart2): Int {
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
enum class HandTypePart2 {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND;

    companion object {
        fun fromCards(cards: List<CardPart2>): HandTypePart2 {
            require(5 == cards.size) { "Invalid card list, it doesn't have 5 cards: $cards" }

            var cardCount: Map<CardPart2, Int> = cards
                .groupBy { it }
                .mapValues { it.value.size }

            val jCount = cardCount[CardPart2.J] ?: 0

            cardCount = cardCount.minus(CardPart2.J)

            if (cardCount.any { 5 == (it.value + jCount) } || 5 == jCount)
                //4J + High Card is FIVE_OF_A_KIND
                return FIVE_OF_A_KIND
            if (cardCount.any { 4 == (it.value + jCount) })
                //3J + High Card is FOUR_OF_A_KIND
                return FOUR_OF_A_KIND

            if (
                (cardCount.any { 3 == it.value } && cardCount.any { 2 == it.value }) || //normal FULL
                (2 == cardCount.count {2 == it.value } && 1 == jCount) //two pair + 1 J
            )
                return FULL_HOUSE

            if (cardCount.any { 3 == (it.value + jCount) })
                //2J + High Card is THREE_OF_A_KIND
                return THREE_OF_A_KIND

            if (
                (2 == cardCount.count {2 == it.value }) || // normal Two Pair
                (1 == cardCount.count {2 == it.value } && 1 == jCount)  // 1 pair + 1J
            )
                return TWO_PAIR


            if (
                (1 == cardCount.count {2 == it.value }) || //normal 1 pair
                (1 == jCount) //high card + 1 J
            )
                return ONE_PAIR

            if (cardCount.all { 1 == it.value })
                return HIGH_CARD

            throw IllegalArgumentException("Unrecognized game type - card count: $cardCount")
        }
    }
}

enum class CardPart2 (val charCard: Char) {

    J('J'),
    TWO('2'),
    THREE( '3'),
    FOUR( '4'),
    FIVE( '5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    T('T'),
    Q('Q'),
    K('K'),
    A('A');

    override fun toString(): String {
        return charCard.toString()
    }
    
    companion object {
        fun fromChar(card: Char): CardPart2 {
            val cardFound = CardPart2.entries.find { it.charCard == card }
            checkNotNull(cardFound) { "Provided card is not valid: $card" }
            return cardFound
        }
    }
}