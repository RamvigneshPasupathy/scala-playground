package week2

object functions {
	// funciton literal
  val f1: (Int, Int) => Int = (a, b) => a * b;    //> f1  : (Int, Int) => Int = week2.functions$$$Lambda$3/664223387@7aec35a
  
  // funciton value
  val f2 = new Function2[Int, Int, Int] {
  		override def apply(a: Int, b: Int): Int = a * b
  }                                               //> f2  : (Int, Int) => Int = <function2>
  
  f1(2, 4)                                        //> res0: Int = 8
  f1.apply(2, 4)                                  //> res1: Int = 8
  f2(2, 4)                                        //> res2: Int = 8
  f2.apply(2, 4)                                  //> res3: Int = 8
  
  val fcurried = f1.curried                       //> fcurried  : Int => (Int => Int) = scala.Function2$$Lambda$9/2083562754@49e4c
                                                  //| b85
  fcurried(2)(4)                                  //> res4: Int = 8
  fcurried.apply(2).apply(4)                      //> res5: Int = 8
  
  val ftupled = f1.tupled                         //> ftupled  : ((Int, Int)) => Int = scala.Function2$$Lambda$11/509886383@6e8dac
                                                  //| df
  val tuple = (2, 4)                              //> tuple  : (Int, Int) = (2,4)
  
  ftupled(tuple)                                  //> res6: Int = 8
}