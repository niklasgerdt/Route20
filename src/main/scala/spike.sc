object spike {
  import scala.collection.mutable.LinkedList
  var collection = LinkedList[Any]()              //> collection  : scala.collection.mutable.LinkedList[Any] = LinkedList()
  val map = Map('1' -> List('a', 'b', 'c'), '2' -> List('d', 'e', 'f'), '3' -> List('g', 'h', 'i'))
                                                  //> map  : scala.collection.immutable.Map[Char,List[Char]] = Map(1 -> List(a, b,
                                                  //|  c), 2 -> List(d, e, f), 3 -> List(g, h, i))

  def words(digits: String, index: Integer, word: String): Unit = {
    if (digits.length() == index)
      collection = collection ++ word
    else
      mapper(digits.charAt(index)).foreach(f => words(digits, index + 1, word + f))
  }                                               //> words: (digits: String, index: Integer, word: String)Unit

  def mapper(c: Char) = map.get(c).get            //> mapper: (c: Char)List[Char]

  words("123", 0, "")
  collection.mkString                            //> res0: String = adgadhadiaegaehaeiafgafhafibdgbdhbdibegbehbeibfgbfhbficdgcdhc
                                                  //| dicegcehceicfgcfhcfi
}