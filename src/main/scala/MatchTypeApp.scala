import scala.compiletime.ops.any.*
import scala.compiletime.ops.string.*
import scala.compiletime.ops.int.*
import scala.compiletime.ops.boolean.*
import scala.compiletime.ops.*
import scala.compiletime.asMatchable
import scala.compiletime.ops.string.+

object MatchTypeApp {
  class Person(name: String, age: Int)
  type Last[X <: Tuple] = X match
    case head *: EmptyTuple => head
    case _ *: tail=> Last[tail]

  type Elem[X] <: Any = X match
    case String => Char
    case Option[t] => t
    case Seq[t] => t

  type LeafElem[X] <: Any = X match
    case Option[t] => LeafElem[t]
    case _ => X

  def elem[X](x: X): Elem[X] = x match
    case x: String => x(0)
    case x: Option[t] => x.get
    case x: Seq[t] => x.head



  @main
  def main(): Unit = {
    println("Data test")

    summon[Elem[String] =:= Char]
    summon[Elem[Some[Int]] =:= Int]
    summon[Elem[List[Float]] =:= Float]
    summon[LeafElem[Option[Option[Int]]] =:= Int]
    summon[Last[Int *: String *: EmptyTuple] =:= String]
    summon[S[23] =:= 24]
    summon[64 == 128 =:= false]
    //summon[NamedTuple.From[Person] =:= (name: String, age: Int)] ???
    summon[("abc" + "def") =:= "abcdef"]
    summon[Substring["scala", 3, 5] =:= "la"]
    summon[CharAt["scala", 1] =:= 'c']
    summon[Length["scala"] =:= 5]
    summon[Tuple.Map[(1, 2, 3), [X <: Int] =>> X * 2] =:= (2, 4, 6)]
    summon[(23 match {case S[n] => n}) =:= 22]
    summon[(1, 2, 3) =:= (1 *: 2 *: 3 *: EmptyTuple)]
  }
}
