package week4

import week4._

object nth {
	def nth[T](n: Int, xs: List[T]): T =
		if(xs.isEmpty) throw new IndexOutOfBoundsException
		else if(n == 0) xs.head
		else nth(n-1, xs.tail)            //> nth: [T](n: Int, xs: week4.List[T])T
		
	val list = new Node(1, new Node(2, new Empty))
                                                  //> list  : week4.Node[Int] = week4.Node@6bdf28bb
	
	nth(0, list)                              //> res0: Int = 1
	nth(8, list)                              //> java.lang.IndexOutOfBoundsException
                                                  //| 	at week4.nth$.nth$1(week4.nth.scala:7)
                                                  //| 	at week4.nth$.$anonfun$main$1(week4.nth.scala:14)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$anonfun$$ex
                                                  //| ecute$1(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:76)
                                                  //| 	at week4.nth$.main(week4.nth.scala:5)
                                                  //| 	at week4.nth.main(week4.nth.scala)
}