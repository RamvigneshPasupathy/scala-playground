package week6

object pairs {
  val n = 4                                       //> n  : Int = 4

  def isPrime(n: Int): Boolean = (2 until n).forall(x => n % x != 0)
                                                  //> isPrime: (n: Int)Boolean

  (1 until n).map(i =>
    (1 until i).map(j => (i, j)))                 //> res0: scala.collection.immutable.IndexedSeq[scala.collection.immutable.Index
                                                  //| edSeq[(Int, Int)]] = Vector(Vector(), Vector((2,1)), Vector((3,1), (3,2)))

  (1 until n).map(i =>
    (1 until i).map(j => (i, j))).flatten         //> res1: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,1
                                                  //| ), (3,2))

  (1 until n).flatMap(i =>
    (1 until i).map(j => (i, j)))                 //> res2: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,1
                                                  //| ), (3,2))
  (1 until n).flatMap(i =>
    (1 until i).map(j => (i, j))).filter(prime =>
    isPrime(prime._1 + prime._2))                 //> res3: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,2
                                                  //| ))
    
   for {
   	i <- 1 until n
   	j <- 1 until i
   	if isPrime(i + j)
   } yield (i, j)                                 //> res4: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,2
                                                  //| ))
 	 	
}