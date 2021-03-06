package week4

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Empty[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Empty.head")
  def tail: Nothing = throw new NoSuchElementException("Empty.tail")
}

class Node[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false
}