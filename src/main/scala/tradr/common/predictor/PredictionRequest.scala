package tradr.common.predictor

import play.api.libs.json.{Json, Reads, Writes}


object PredictionRequest {


  implicit val reads: Reads[PredictionRequest] = Json.reads[PredictionRequest]
  implicit val writes: Writes[PredictionRequest] = Json.writes[PredictionRequest]


}

/**
  * Prediction request is the json request that is send out by the
  * trader to get a answer from the prediction API, i.e. the
  * models
  */
case class PredictionRequest(
                            timestamp: Long,
                            instrument: String
                            )