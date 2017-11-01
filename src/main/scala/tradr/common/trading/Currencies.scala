package tradr.common.trading

object Currencies extends Enumeration {
  val EUR: Currencies.Value = Value("EUR")
  val USD: Currencies.Value = Value("USD")



  def get(s: String): Currencies.Value = {
    s match {
      case "EUR" => EUR
      case "USD" => USD
      case _ => throw new Exception("couldn't match currency")
    }
  }
}
