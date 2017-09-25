package tradr.common.trading

object Instruments extends Enumeration {
  val EURUSD = Value("EURUSD")


  def get(s: String) = {
    s match {
      case "EURUSD" => EURUSD
      case _ => throw new Exception("couldn't match currency")
    }
  }


}