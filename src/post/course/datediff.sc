package post.course

object datediff {
  def isLeapYear(year: Int): Boolean = {
    if (year % 400 == 0) true
    if (year % 100 == 0) false
    if (year % 4 == 0) true
    false
  }                                               //> isLeapYear: (year#1143334: Int#935)Boolean#1754

  def getLeapYearsCount(currentYear: Int): Int = {
    val _400 = currentYear / 400
    val _100 = currentYear / 100
    val _4 = currentYear / 4
    _400 + _4 - _100
  }                                               //> getLeapYearsCount: (currentYear#1143374: Int#935)Int#935

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
  }                                               //> getDaysBetween: (from#1143385: (Int#935, Int#935, Int#935), to#1143386: (In
                                                  //| t#935, Int#935, Int#935))Int#935

  getDaysBetween((10, 2, 2014), (10, 3, 2015))    //> res0: Int#935 = 393
}