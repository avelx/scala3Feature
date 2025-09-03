import language.experimental.modularity

final case class Person(tracked val name: String)

@main
def main(): Unit = {

  def f(p: Person): p.name.type =
    p.name

  // final :: compile failure

  //final val name: "John"

  val p = Person("John")

  val x: p.name.type = f(p)

  p.name: "John"

  val x2: "John" = f(p)

  val jk2 = p.copy(name = "Data2")

  println(x)

  case class Keys(tracked val keys: List[String], extra: String)

  def validate(keys: Keys): Either[Throwable, Keys { val keys: :: [String] }] = {
    keys.keys match {
      case Nil => Left(new IllegalArgumentException("KeyS can not be empty"))
      case head:: next => Right( keys.copy(keys = ::(head, next)))
    }
  }

  val initKeyForValidation = Keys(List("a", "b"), extra = "c")
  val validationState = validate(initKeyForValidation).foreach{ validateKeys =>
    validateKeys.keys match {
      case head::next => "Not Impl"
    }
    "Test"
  }

}

