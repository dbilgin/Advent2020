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
            if (checkIfMoreValid(current)) {
                validCount++
            }

            current = ""
            return@forEachIndexed
        }
    }

    println(validCount)
}

fun checkIfMoreValid(text: String): Boolean {
    var pattern = Pattern.compile("(?=.*byr:)(?=.*iyr:)(?=.*eyr:)(?=.*hgt:)(?=.*hcl:)(?=.*ecl:)(?=.*pid:)")
    if (pattern.matcher(text).find()) {
        var splitStr = text.split(' ')

        splitStr.forEach {
            val result = when {
                it.contains("byr:") -> checkYears("byr:", it, 1920, 2002)
                it.contains("iyr:") -> checkYears("iyr:", it, 2010, 2020)
                it.contains("eyr:") -> checkYears("eyr:", it, 2020, 2030)
                it.contains("hgt:") -> checkHgt("hgt:", it)
                it.contains("hcl:") -> checkHcl("hcl:", it)
                it.contains("ecl:") -> checkEcl("ecl:", it)
                it.contains("pid:") -> checkPid("pid:", it)
                else -> true
            }

            if (!result) {
                return false
            }
        }
        return true
    }

    return false
}

fun checkPid(replaceText: String, it: String): Boolean {
    val newPid = it.replace(replaceText, "")

    if (newPid.length != 9 || newPid.toIntOrNull() == null) {
        return false
    }

    return true
}

fun checkEcl(replaceText: String, it: String): Boolean {
    val newEcl = it.replace(replaceText, "")
    if (
        newEcl != "amb" &&
        newEcl != "blu" &&
        newEcl != "brn" &&
        newEcl != "gry" &&
        newEcl != "grn" &&
        newEcl != "hzl" &&
        newEcl != "oth"
    ) {
        return false
    }

    return true
}

fun checkHcl(replaceText: String, it: String): Boolean {
    val newHcl = it.replace(replaceText, "")
    val colorPattern = Pattern.compile("#([0-9a-f]{6})")
    if (colorPattern.matcher(newHcl).find()) {
        return true
    }

    return false
}

fun checkYears(replaceText: String, it: String, yearOne: Int, yearTwo: Int): Boolean {
    val newYr = it.replace(replaceText, "")
    if (newYr.length != 4 || newYr.toInt() < yearOne || newYr.toInt() > yearTwo) {
        return false
    }
    return true
}

fun checkHgt(replaceText: String, it: String): Boolean {
    var newHgt = it.replace(replaceText, "")
    var newHgtType = newHgt.takeLast(2)
    var newHgtNum = newHgt.replace(newHgtType, "")

    if (
        (newHgtType != "in" && newHgtType != "cm") ||
        (newHgtType == "in" &&
            (newHgtNum.toInt() < 59 || newHgtNum.toInt() > 76)
        ) ||
        (newHgtType == "cm" &&
            (newHgtNum.toInt() < 150 || newHgtNum.toInt() > 193)
        )
    ) {
        return false
    }
    return true
}
