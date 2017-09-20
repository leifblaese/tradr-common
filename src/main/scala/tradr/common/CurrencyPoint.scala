package tradr.common

import play.api.libs.json._


object PricingPoint {

//  implicit val PricingPointWrites = new Writes[PricingPoint] {
//    def writes(point: PricingPoint): JsObject = {
//      Json.obj(
//        "timestamp" -> point.timestamp,
//        "currencyPair" -> point.currencyPair,
//        "value" -> point.value
//      )
//    }
//  }


  implicit val PricingPointReads = Json.reads[PricingPoint]
  implicit val PricingPointWrites = Json.writes[PricingPoint]

//  private val PricingPointBuilder =
//    (JsPath \ "timestamp").read[Long] and
//    (JsPath \ "currencyPair") .read[String] and
//    (JsPath \ "value").read[Double]
//
//  implicit val PricingPoint = PricingPointBuilder(PricingPoint.apply _)

}

/**
  * Case class represeting the exchange value for a currency pair in a specific point in time
  *
  * @param timestamp
  * @param currencyPair
  * @param value
  */
case class PricingPoint(
                          timestamp: Long,
                          currencyPair: String,
                          value: Double
                        ) {
  import PricingPoint._
}


