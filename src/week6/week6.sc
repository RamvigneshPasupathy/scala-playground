package week6

object week6 {
  
  val xs = Array(1, 2, 4, 8)                      //> xs  : Array[Int] = Array(1, 2, 4, 8)
  xs.map(_ * 2)                                   //> res0: Array[Int] = Array(2, 4, 8, 16)
  
  val s = "Hello"                                 //> s  : String = Hello
  s.filter(_.isUpper)                             //> res1: String = H
  
  
  def isPrime(n: Int): Boolean = (2 until n).forall(x => n % x != 0)
                                                  //> isPrime: (n: Int)Boolean
  
  isPrime(2)                                      //> res2: Boolean = true
}