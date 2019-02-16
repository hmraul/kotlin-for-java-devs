package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    return Evaluation(rightGuess(secret, guess), wrongPosition(secret, guess))
}

fun rightGuess(first: String, second: String): Int {
    if (first.length != second.length) {
        throw IllegalArgumentException("String should have same size")
    }

    val firstCase = sumIfEquals(first.first(), second.first())

    if(first.length == 1) {
        return firstCase
    }

    return firstCase + rightGuess(first.substring(1), second.substring(1))
}

fun sumIfEquals(a: Char, b: Char): Int {
    return when (a.equals(b)) {
        true -> 1
        else -> 0
    }
}

fun wrongPosition(first: String, second: String): Int {
    var result = 0

    var reducedFirst = ""
    var reducedSecond = ""

    for (i: Int in 0..(first.length - 1)) {
        if (!first[i].equals(second[i])) {
            reducedFirst += first[i]
            reducedSecond += second[i]
        }
    }

    for (c in reducedFirst) {
        if (reducedSecond.contains(c)) {
            reducedSecond = reducedSecond.removeAt(reducedSecond.indexOf(c))
            result++
        }
    }
    return result
}

fun String.removeAt(index: Int): String {
    return this.removeRange(index, index + 1)
}