package tradr.common.trading

//
//import org.nd4j.linalg.factory.Nd4j
////import org.nd4j.linalg.api.ndarray.INDArray

import play.api.libs.functional.syntax._
import play.api.libs.json._
import Currencies

object PartialTrade {

  implicit val partialTradeWrites = new Writes[PartialTrade] {
    def writes(trade: PartialTrade) = {
//      val l: Int = trade.actionProbabilities.length()
//      val actionProbs =  (0 until l).map(i => trade.actionProbabilities.getDouble(i)).toArray
//      val valuePreds = (0 until l).map(i => trade.valuePrediction.getDouble(i)).toArray

      Json.obj(
        "id" -> trade.id,
        "currency1" -> trade.currency1.toString,
        "currency2" -> trade.currency2.toString,
        "action" -> trade.action.toString,
        "time" -> trade.time,
        "price" -> trade.price,
        "lot" -> trade.lot,
        "portfolioChange" -> trade.portfolioChange.toArray.map{case (key, value) => (key.toString, value)},
        "actionProbabilities" -> trade.actionProbabilities,
        "valuePrediction" -> trade.valuePrediction
      )
    }
  }

  private val partialTradeBuilder =
    (JsPath \ "id").read[Long] and
      (JsPath \ "currency1")
        .read[String]
        .map(s => Currencies.withName(s)) and
      (JsPath \ "currency2")
        .read[String]
        .map(s => Currencies.withName(s)) and
      (JsPath \ "action")
        .read[String]
        .map(s => Action.withName(s)) and
      (JsPath \ "time").read[Long] and
      (JsPath \ "price").read[Double] and
      (JsPath \ "lot").read[Double] and
      (JsPath \ "portfolioChange")
        .read[Array[(String, Double)]]
        .map(m => m.map{case (key, value) => (Currencies.withName(key), value)}.toMap) and
      (JsPath \ "actionProbabilities")
        .read[Array[Double]] and
//        .map(arr => Nd4j.create(arr)) and
      (JsPath \ "valuePrediction")
        .read[Array[Double]]

  //        .map(arr => Nd4j.create(arr))

  implicit val partialTradeRead = partialTradeBuilder(PartialTrade.apply _)



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



}

case class PartialTrade  (
                           id: Long,
                           currency1: Currencies.Value,
                           currency2: Currencies.Value,
                           action: Action.Value,
                           time: Long,
                           price: Double,
                           lot: Double,
                           portfolioChange: Map[Currencies.Value, Double],
                           actionProbabilities: Array[Double],
                           valuePrediction: Array[Double]
                         )
