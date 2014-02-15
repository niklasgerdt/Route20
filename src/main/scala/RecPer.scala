import scala.collection.mutable.LinkedList

object RecPer {
  var collection = LinkedList[Any]() //> collection  : scala.collection.mutable.LinkedList[Any] = LinkedList()
  val map = Map('1' -> List('a', 'b', 'c'), '2' -> List('d', 'e', 'f'), '3' -> List('g', 'h', 'i'))

  def main(args: Array[String]): Unit = {
    words("123", 0, "")
    println(collection.mkString)
  }

  def words(digits: String, index: Integer, word: String): Unit = {
    if (digits.length() == index)
      collection = collection ++ word
    else
      mapper(digits.charAt(index)).foreach(f => words(digits, index + 1, word + f))
  } //> words: (digits: String, index: Integer, word: String)Unit

  def mapper(c: Char) = map.get(c).get //> mapper: (c: Char)List[Char]

}
