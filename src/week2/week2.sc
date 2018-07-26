package week2

object hof {

  def id(x: Int): Int = x
  def cube(x: Int): Int = x * x * x

  def sumInts(a: Int, b: Int): Int =
    if (a > b) 0 else id(a) + sumInts(a + 1, b)

  def sumCubes(a: Int, b: Int): Int =
    if (a > b) 0 else cube(a) + sumCubes(a + 1, b)

  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f, a + 1, b)

  def sumTR(f: Int => Int, a: Int, b: Int): Int = {
    def loop(i: Int, acc: Int): Int = {
      if (i > b) acc
      else loop(i + 1, acc + f(i))
    }
    loop(a, 0)
  }

  def productTR(f: Int => Int, a: Int, b: Int): Int = {
    def loop(i: Int, acc: Int): Int = {
      if (i > b) acc
      else loop(i + 1, acc * f(i))
    }
    loop(a, 1)
  }
  
  def productCurried(f: Int => Int)(a: Int, b: Int): Int =
  		if(a > b) 1 else productCurried(f)(a+1, b)
  
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
  		if(a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a+1, b))
  		
  	def sumMR(f: Int => Int, a: Int, b: Int) = mapReduce(f, (x, y) => x + y, 0)(a, b)
  	def productMR(f: Int => Int, a: Int, b: Int) = mapReduce(f, (x, y) => x * y, 0)(a, b)
  	
  sumInts(1, 5)
  sumCubes(1, 5)

  sum(x => x, 1, 5)
  sum(x => x * x * x, 1, 5)

  sumTR(x => x, 1, 5)
  sumTR(x => x * x * x, 1, 5)
  
  productTR(x => x, 1, 5)
  productCurried(x => x)(1, 5)
  
  sumMR(x => x, 1, 5)
  productMR(x => x, 1, 5)
  
}