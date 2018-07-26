package week5

object listfun {
  val ns = List(1, -2, 3, 4, 5)                   //> ns  : List[Int] = List(1, -2, 3, 4, 5)
  def p(x: Int): Boolean = x > 0                  //> p: (x: Int)Boolean
  
  ns.filter(p)                                    //> res0: List[Int] = List(1, 3, 4, 5)
  ns.filterNot(p)                                 //> res1: List[Int] = List(-2)
  ns.partition(p)                                 //> res2: (List[Int], List[Int]) = (List(1, 3, 4, 5),List(-2))
  ns.takeWhile(p)                                 //> res3: List[Int] = List(1)
  ns.dropWhile(p)                                 //> res4: List[Int] = List(-2, 3, 4, 5)
  ns.span(p)                                      //> res5: (List[Int], List[Int]) = (List(1),List(-2, 3, 4, 5))
  
  val data = List(1, 1, 1, 3, 4, 1, 2, 1, 3, 3, 3, 2)
                                                  //> data  : List[Int] = List(1, 1, 1, 3, 4, 1, 2, 1, 3, 3, 3, 2)
  
  def pack[T](xs: List[T]): List[List[T]] = xs match {
  		case Nil => Nil
  		case i :: is =>
  			val (ls, rs) = xs.span(y => y == i)
  			ls :: pack(rs)
  }                                               //> pack: [T](xs: List[T])List[List[T]]
  
  pack(data)                                      //> res6: List[List[Int]] = List(List(1, 1, 1), List(3), List(4), List(1), List(
                                                  //| 2), List(1), List(3, 3, 3), List(2))
  
  def encode[T](xs: List[T]): List[(T, Int)] =
 		pack(xs).map(ys => (ys.head, ys.length))
                                                  //> encode: [T](xs: List[T])List[(T, Int)]
 		
 	encode(data)                              //> res7: List[(Int, Int)] = List((1,3), (3,1), (4,1), (1,1), (2,1), (1,1), (3,3
                                                  //| ), (2,1))
  
}