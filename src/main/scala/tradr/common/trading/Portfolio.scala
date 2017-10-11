package tradr.common.trading


object Portfolio {

}


/**
  * Portfolio is a case class holding the current portfolio, i.e. the
  * amount of currencies held at a certain time point
  *
  * This corresponds to a table stored on Cassandra. The values are snapshots
  * of the actual portfolio held by the broker.
  *
  * The portfolioId identifies the portfolio for a specific run, which is needed
  * in case several portfolios are used in parallel
  * @param currencies
  */
case class Portfolio(portfolioid: String,
                     currencies: Map[Currencies.Value, Double])
