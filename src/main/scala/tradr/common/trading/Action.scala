package tradr.common.trading

object Action extends Enumeration {

  val Hold: Action.Value = Value(0)
  val Buy: Action.Value = Value(1)
  val Sell: Action.Value = Value(2)
  val Close: Action.Value = Value(3)


  def get(i: Int): Action.Value = {
    i match {
      case 0 => Hold
      case 1 => Buy
      case 2 => Sell
      case 3 => Close
      case _ => throw new Exception("couldn't match action")
    }
  }

}
