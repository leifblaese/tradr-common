package tradr.common

object Action extends Enumeration {

  val Hold = Value(0)
  val Buy = Value(1)
  val Sell = Value(2)
  val Close = Value(3)


  def get(i: Int) = {
    i match {
      case 0 => Hold
      case 1 => Buy
      case 2 => Sell
      case 3 => Close
      case _ => throw new Exception("couldn't match action")
    }
  }

}
