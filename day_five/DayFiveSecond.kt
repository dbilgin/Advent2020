import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("../inputs/DayFive").inputStream()

    var ticketList = mutableListOf<Int>()
    inputStream.bufferedReader().forEachLine {
        val rowNum = calculateTickets(it.take(7), 127, 'F')
        val colNum = calculateTickets(it.takeLast(3), 7, 'L')
        ticketList.add((rowNum * 8) + colNum)
    }

    ticketList.sort()
    getTicketNum(ticketList)
}

fun getTicketNum(ticketList: List<Int>){
    var previousNum: Int? = null
    ticketList.forEach {
        if (it - 1 != previousNum && it - 2 == previousNum) {
            println(it - 1)
        }

        previousNum = it
    }

}

fun calculateTickets(code: String, maxNum: Int, lowerCode: Char): Int {
    var calculatedList = mutableListOf(0, maxNum)

    code.forEach {
        val diff = calculatedList[1] - calculatedList[0]

        if (it == lowerCode) {
            calculatedList[1] = calculatedList[1] - Math.ceil((diff.toDouble() / 2)).toInt()
        } else {
            calculatedList[0] = calculatedList[0] + Math.ceil(diff.toDouble() / 2).toInt()
        }
     }

     if (calculatedList[0] == calculatedList[1]) {
         return calculatedList[0]
     } else {
        throw Exception("Nope")
     }
}
