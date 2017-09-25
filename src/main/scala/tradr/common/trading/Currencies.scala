package tradr.common.trading

object Currencies extends Enumeration {
  val EUR = Value("EUR")
  val USD = Value("USD")



  def get(s: String) = {
    s match {
      case "EUR" => EUR
      case "USD" => USD
      case _ => throw new Exception("couldn't match currency")
    }
  }
}
