package tradr.common.trading

object Instruments extends Enumeration {
  val EURUSD: Instruments.Value = Value("EURUSD")

  def getBaseCurrency(v: Instruments.Value): Currencies.Value = {
    Currencies.get(v.toString.splitAt(3)._1)
  }

  def getQuoteCurrency(v: Instruments.Value): Currencies.Value = {
    Currencies.get(v.toString.splitAt(3)._2)
  }

  def getBaseCurrencyAsString(v: Instruments.Value): String = {
    getBaseCurrency(v).toString
  }

  def getQuoteCurrencyAsString(v: Instruments.Value): String = {
    getQuoteCurrency(v).toString
  }

  def get(s: String): Instruments.Value = {
    s match {
      case "EURUSD" => EURUSD
      case _ => throw new Exception("couldn't match currency")
    }
  }


}