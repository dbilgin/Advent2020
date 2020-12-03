import java.io.File
import java.io.InputStream

fun main() {
  val inputStream: InputStream = File("../inputs/DayTwo").inputStream()
  val lineList = mutableListOf<String>()
  inputStream.bufferedReader().forEachLine { lineList.add(it) }

  var count = 0
  lineList.forEach {
      val splitResult = it.split("-|\\: | ".toRegex())
      val countResult = splitResult[3].filter { it.toString() == splitResult[2] }.count()

      if (countResult >= splitResult[0].toInt() && countResult <= splitResult[1].toInt()) {
        count++
      }
  }

    println(count)
}
