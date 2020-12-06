import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("../inputs/DayFive").inputStream()

    val ticketList = mutableListOf<Int>()
    inputStream.bufferedReader().forEachLine {
        val rowNum = calculator(it.take(7), 127, 'F')
        val colNum = calculator(it.takeLast(3), 7, 'L')
        ticketList.add((rowNum * 8) + colNum)
    }

    println(ticketList.maxOrNull())
}

fun calculator(code: String, maxNum: Int, lowerCode: Char): Int {
    var calculatedList = mutableListOf(0, maxNum)

    code.forEach {
        val diff = calculatedList[1] - calculatedList[0]

        if (it == lowerCode) {
            calculatedList[1] = calculatedList[1] - Math.ceil((diff.toDouble() / 2)).toInt()
        } else {
            calculatedList[0] = calculatedList[0] + Math.ceil(diff.toDouble() / 2).toInt()
        }
     }

     if(calculatedList[0] == calculatedList[1]){
         return calculatedList[0]
     } else{
        throw Exception("Nope")
     }
}
