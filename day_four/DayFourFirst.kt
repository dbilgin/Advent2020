import java.io.File
import java.io.InputStream
import java.util.regex.Pattern

fun main() {
    val inputStream: InputStream = File("../inputs/DayFour").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var validCount = 0
    var current = ""

    lineList.forEachIndexed { index, element ->
        current += " $element"

        if (element.isNullOrEmpty() || index + 1 == lineList.size) {
            if (checkIfValid(current)) {
                validCount++
            }

            current = ""
            return@forEachIndexed
        }
    }

    println(validCount)
}

fun checkIfValid(text: String): Boolean {
    var pattern = Pattern.compile("(?=.*byr:)(?=.*iyr:)(?=.*eyr:)(?=.*hgt:)(?=.*hcl:)(?=.*ecl:)(?=.*pid:)")
    if (pattern.matcher(text).find()) {
        return true
    }

    return false
}
