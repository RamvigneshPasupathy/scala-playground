package post.course

import scala.io.Source

object WhatsappChat extends App {

  def getDateTuple(date: String): (Int, Int, Int) = {
    val dateArray = date.split("/")
    (dateArray(1).toInt, dateArray(0).toInt, dateArray(2).toInt)
  }

  def isLeapYear(year: Int): Boolean = {
    if (year % 400 == 0) true
    if (year % 100 == 0) false
    if (year % 4 == 0) true
    false
  } //> isLeapYear: (year#1014933: Int#935)Boolean#1754

  def getLeapYearsCount(currentYear: Int): Int = {
    val _400 = currentYear / 400
    val _100 = currentYear / 100
    val _4 = currentYear / 4
    _400 + _4 - _100
  } //> getLeapYearsCount: (currentYear#1014973: Int#935)Int#935

  def getDaysBetween(from: (Int, Int, Int), to: (Int, Int, Int)): Int = {
    val month_days = Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    var days1 = 0
    var days2 = 0

    // days for completed years from 00-00-000
    days1 = from._3 * 365 + getLeapYearsCount(from._3)
    days2 = to._3 * 365 + getLeapYearsCount(to._3)

    // days for completed month of the current year
    for (i <- 1 until from._2) {
      if (i == 2 && isLeapYear(from._2)) days1 += 1
      days1 += month_days(i - 1)
    }

    for (i <- 1 until to._2) {
      if (i == 2 && isLeapYear(to._2)) days2 += 1
      days2 += month_days(i - 1)
    }

    // completed days for current month of the current year
    days1 += from._1
    days2 += to._1

    days2 - days1
  }

  val datePattern = "([1-9]|1[0-2])/([1-9]|[12][0-9]|3[01])/([0-9]{2})"
  val tsPattern = "(" + datePattern + ", ([1-9]|1[0-2]):(0[0-9]|[1-5][0-9]) (A|P)M - )"
  val pattern = tsPattern + ".*"

  def getStats(filePath: String): (Map[String, Int], (String, String)) = {

    def substringByPattern(line: String, regex: String): String = {
      val matcher = regex.r.pattern.matcher(line)
      if (matcher.find())
        line.substring(matcher.start(), matcher.end())
      else
        ""
    }

    def getDetails(line: String, splitter: String): (String, String) = {
      val tsRemoved = line.replaceFirst(tsPattern, "")
      val splitIndex = tsRemoved indexOf splitter
      if (splitIndex == -1) ("$computer generated$", line)
      else (tsRemoved.substring(0, splitIndex), tsRemoved.substring(splitIndex + splitter.length))
    }

    try {
      val lines =
        Source.fromFile(filePath)
          .getLines.toList

      val day1 = substringByPattern(lines(0), datePattern)
      val dayN = substringByPattern(lines(lines.length - 1), datePattern)

      (
        lines
        .filter(_.matches(pattern))
        .map(getDetails(_, ": "))
        .groupBy(_._1)
        .map(record => (record._1, record._2.length)),
        (day1, dayN))

    } catch {
      case _ => (Map(), ("", "")) 
    }

  }

  def getSortedStats(table: Map[String, Int]): List[(String, Int)] = {
    table.toList.sortWith(_._2 > _._2)
  }

  def drawTableFor(pairs: List[(String, Int)], totalMsgCount: Int, totalDaysCount: Int) = {
    val padding = 5

    val indexCellSize = 5 + padding
    val nameCellSize = pairs.reduceLeft((p1, p2) => if (p1._1.length > p2._1.length) p1 else p2)._1.length + padding
    val msgcountCellSize = 10 + padding
    val contributionCellSize = 15 + padding
    val msgsperdayCellSize = 15 + padding
    val colCount = 5;

    val totalSize = indexCellSize + nameCellSize +
      msgcountCellSize + (colCount * padding) +
      contributionCellSize + msgsperdayCellSize +
      (2 * (colCount - 1)) + 1

    val leftPaddingString = "+" + "".formatted("%" + padding + "s")

    def printFence = {
      for (i <- 0 to totalSize) print("+")
      println
    }

    def printHeader = {
      printFence
      print(leftPaddingString + "S. NO".formatted("%-" + indexCellSize + "s") + "+")
      print(leftPaddingString + "NAME".formatted("%-" + nameCellSize + "s") + "+")
      print(leftPaddingString + "MSG COUNT".formatted("%-" + msgcountCellSize + "s") + "+")
      print(leftPaddingString + "CONTRIBUTION".formatted("%-" + contributionCellSize + "s") + "+")
      print(leftPaddingString + "MSGs PER DAY".formatted("%-" + msgsperdayCellSize + "s") + "+")
      println
    }

    def printRow(i: Int, c: (String, Int)) = {
      val contribution = f"${c._2.toDouble / totalMsgCount * 100}%02.2f"
      val msgsPerDay = c._2 / totalDaysCount
      print(leftPaddingString + (i + 1).toString.formatted("%-" + indexCellSize + "s") + "+")
      print(leftPaddingString + c._1.formatted("%-" + nameCellSize + "s") + "+")
      print(leftPaddingString + c._2.formatted("%-" + msgcountCellSize + "s") + "+")
      print(leftPaddingString + contribution.formatted("%-" + contributionCellSize + "s") + "+")
      print(leftPaddingString + msgsPerDay.toString.formatted("%-" + msgsperdayCellSize + "s") + "+")
    }

    printHeader

    pairs.foreach(p => {
      printFence
      printRow(pairs.indexOf(p), p)
      println
    })

    printFence
  }

  def printReportFor(data: List[(String, Int)], dates: (String, String)) = {
    val totalMsgs = data.foldLeft(("", 0))((a: (String, Int), b: (String, Int)) => ("", a._2 + b._2))._2
    val totalDays = getDaysBetween(getDateTuple(dates._1), getDateTuple(dates._2))
    println("YOUR WHATSAPP CHAT REPORT")
    println
    println("File Name: " + filePath.replaceAll(".*/", ""))
    println("First msg was on: " + dates._1)
    println("Last msg was on: " + dates._2)
    println("Total Number of Days: " + totalDays)
    println("Total Messages: " + totalMsgs)
    println("Messages per Day: " + (totalMsgs / totalDays))
    drawTableFor(data, totalMsgs, totalDays)
  }

  // val filePath = "/Users/ram-7197/Downloads/WhatsApp Chat with Pune Buddies/WhatsApp Chat with Pune Buddies.txt"

  println("An absolute file path is required. Waiting for you to type...")
  val filePath = scala.io.StdIn.readLine()

  val (data, dates) = getStats(filePath)
  
  if (!data.isEmpty) {
    val sortedData = getSortedStats(data)
    printReportFor(sortedData, dates)
  } else {
    println("Invalid File Path! Try again.")
  }
  

}