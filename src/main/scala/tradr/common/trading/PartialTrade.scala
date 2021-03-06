package tradr.common.trading


import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

object PartialTrade {

  private val partialTradeBuilder =
    (JsPath \ "id").read[Long] and
    (JsPath \ "instrument").read[String].map(s => Instruments.withName(s)) and
    (JsPath \ "action").read[String].map(s => Action.withName(s)) and
    (JsPath \ "time").read[Long] and
    (JsPath \ "price").read[Double] and
    (JsPath \ "lot").read[Double] and
    (JsPath \ "portfolioChange")
      .read[Array[(String, Double)]]
      .map(m => m.map{case (key, value) => (Currencies.withName(key), value)}.toMap)

  implicit val reads: Reads[PartialTrade] = partialTradeBuilder(PartialTrade.apply _)

  implicit val writes = new Writes[PartialTrade] {
    def writes(trade: PartialTrade): JsObject = {

      Json.obj(
        "id" -> trade.id,
        "instrument" -> trade.instrument.toString,
        "action" -> trade.action.toString,
        "time" -> trade.time,
        "price" -> trade.price,
        "lot" -> trade.lot,
        "portfolioChange" -> trade.portfolioChange.toArray.map{case (key, value) => (key.toString, value)}
      )
    }
  }



  //  val json: Format[PartialTrade] = (
  //    (JsPath \ "id").format[Long]()
  //    )


  //  def createEmptyHold = PartialTrade(
  //    id = 0L,
  //    currencyPair = (EUR, USD),
  //    action = Action.Hold,
  //    time = 0L,
  //    price = 0.0,
  //    lot = 0.0,
  //    portfolioChange = Map(EUR -> 0.0, USD -> 0.0),
  //    actionProbabilities = Nd4j.create(Array(0.0)),
  //    valuePrediction = Nd4j.create(Array(0.0))
  //  )
  //
  //  def createEmptyBuy = PartialTrade(
  //    id = 0L,
  //    currencyPair = (EUR, USD),
  //    action = Action.Buy,
  //    time = 0L,
  //    price = 1.0,
  //    lot = 0.0001,
  //    portfolioChange = Map(EUR -> -1.0, USD -> 1.0),
  //    actionProbabilities = Nd4j.create(Array(0.0)),
  //    valuePrediction = Nd4j.create(Array(0.0))
  //  )
  //
  //  def createEmptySell = PartialTrade(
  //    id = 0L,
  //    currencyPair = (EUR, USD),
  //    action = Action.Sell,
  //    time = 0L,
  //    price = 1.0,
  //    lot = 0.0001,
  //    portfolioChange = Map(EUR -> 1.0, USD -> -1.0),
  //    actionProbabilities = Nd4j.create(Array(0.0)),
  //    valuePrediction = Nd4j.create(Array(0.0))
  //  )



//  implicit val PartialTradeReads = Json.reads[PartialTrade]
//  implicit val PartialTradeWrites = Json.writes[PartialTrade]

}


/**
  * This class identifies a partialTrade which is a unique transaction in the sequence
  * of a full trade, i.e. a buy, sell or hold. Once the trade is closed, the
  * sequence of partial trades is converted into a trade object.
  *
  * @param id ID of the partial trade
  * @param instrument Instrument that was traded
  * @param action Action that was performed
  * @param time Timestamp, milliseconds epoch of the trade
  * @param price rate of the instrument with which the trade was realized
  * @param lot Lot of the trade
  * @param portfolioChange resulting portfolio change
  */
case class PartialTrade  (
                           id: Long,
                           instrument: Instruments.Value,
                           action: Action.Value,
                           time: Long,
                           price: Double,
                           lot: Double,
                           portfolioChange: Map[Currencies.Value, Double]
                         ) {
}
