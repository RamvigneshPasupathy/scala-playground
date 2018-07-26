object week5 {
  val berries = List("blueberry", "raspberry")
  val citrus = List("lemon", "orange")
  val empty = List()

	// complexity: |xs|
	def concat[T](xs: List[T], ys: List[T]): List[T]  = xs match {
		case List() => ys
		case z :: zs => z :: concat(zs, ys)
	}

	// complexity: N
  def last[T](xs: List[T]): T = xs match {
    case List()  => throw new Error("cannot find last of an empty list")
    case List(x) => x
    case y :: ys => last(ys)
  }

	// complexity: N
  def init[T](xs: List[T]): List[T] = xs match {
    case List()  => throw new Error("cannot find init of an empty list")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  }

	// complexity: N * N
  def reverse[T](xs: List[T]): List[T] = xs match {
    case List()  => xs
    case y :: ys => reverse(ys) ++ List(y)
  }
  
	val fruits = concat(berries, citrus)
  last(fruits)
  init(fruits)
  reverse(fruits)
}