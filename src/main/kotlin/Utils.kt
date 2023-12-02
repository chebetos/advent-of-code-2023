import java.math.BigInteger
import java.security.MessageDigest

object Utils {
    /**
     * Reads lines from the given input txt file.
     */
    fun readInput(name: String): List<String> {
        return this::class.java
            .getResourceAsStream(name)
            ?.bufferedReader()
            ?.readLines()
            ?: listOf()
    }
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')


/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)