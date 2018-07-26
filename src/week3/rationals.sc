package week3

object rationals {

  val x = Rational(1, 3)                          //> x  : week3.Rational = 1/3
  val y = Rational(5, 7)                          //> y  : week3.Rational = 5/7
  val z = Rational(3, 2)                          //> z  : week3.Rational = 3/2

  x.add(y)                                        //> res0: week3.Rational = 22/21
  x.sub(y).sub(z)                                 //> res1: week3.Rational = -79/42
  y.add(y)                                        //> res2: week3.Rational = 10/7
  
  y + y                                           //> res3: week3.Rational = 10/7
  y + 5                                           //> res4: week3.Rational = 40/7
  5 + y                                           //> res5: week3.Rational = 40/7
  
  x.less(y)                                       //> res6: Boolean = true
  x.max(y)                                        //> res7: week3.Rational = 5/7
}

// the apply method is mandatory when the primary constructor is private
class Rational private (x: Int, y: Int) {
	require(y != 0, "denomintor must be a nonzero integer")
	
	// this auxilary constructor can be ignored when using the apply method
	// def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  def numer = x
  def denom = y

  def neg: Rational = Rational(-numer, denom)

  def add(that: Rational): Rational =
    Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)
      
  	def +(that: Rational): Rational = add(that)
  	
  	// this overloaded symbolic method can be ignored when using the implicit apply method
  	// def +(i: Int): Rational = add(Rational(i, 1))

  def sub(that: Rational): Rational = add(that.neg)

  def less(that: Rational): Boolean = this.numer * that.denom < that.numer * this.denom

  def max(that: Rational): Rational =
    if (this.less(that)) that else this

  override def toString = {
  		val g = gcd(x, y)
  		numer / g + "/" + denom / g
  }
}

object Rational {
	def apply(x: Int, y: Int): Rational =
		new Rational(x, y)
		
	implicit def apply(x: Int): Rational =
		new Rational(x, 1)
}