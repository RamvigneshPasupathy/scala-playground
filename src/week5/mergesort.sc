object mergesort {

	def mergeWithPairs(ls: List[Int], rs: List[Int]): List[Int] = (ls, rs) match {
		case (Nil, ys) => ys
		case (xs, Nil) => xs
		case (x :: xs, y :: ys) =>
			if(x < y) x :: merge(xs, rs)
			else y :: merge(ys, ls)
	}                                         //> mergeWithPairs: (ls: List[Int], rs: List[Int])List[Int]
	
	def merge(ls: List[Int], rs: List[Int]): List[Int] = ls match {
		case Nil => rs
		case x :: xs =>
			rs match {
				case Nil => ls
				case y :: ys =>
					if(x < y) x :: merge(xs, rs)
					else y :: merge(ys, ls)
			}
	}                                         //> merge: (ls: List[Int], rs: List[Int])List[Int]
	
  def msort(xs: List[Int]): List[Int] = {
  		val c = xs.length / 2
  		if(c == 0) xs
  		else {
  			val (ls, rs)	= xs splitAt c
  			merge(msort(ls), msort(rs))
  		}
  }                                               //> msort: (xs: List[Int])List[Int]
  
  msort(List(2, 3, 1, 5, 4, 7, 6))                //> res0: List[Int] = List(1, 2, 3, 4, 5, 6, 7)
 
}