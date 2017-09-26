package tradr.common.predictor

import play.api.libs.json.{Json, OWrites, Reads, Writes}


object PredictorResult {

  implicit val PredictorResultReads: Reads[PredictorResult] = Json.reads[PredictorResult]
  implicit val PredictorResultWrites: Writes[PredictorResult] = Json.writes[PredictorResult]

}


/**
  * Predictor result is the case class for the JSON result with which a predictor answers
  * upon request.
  * The predictionID is a unique ID for the trade, used to identify it e.g. in a database.
  * Since it needs to be unique it must be handlet by a global ID system. As log as that is not up
  * and running, all IDs will be random longs.
  *
  * The results map holds the results ofthe prediction, i.e. a probability distribution over all actions
  * or the value-function predictions for all actions.
  *
  *
  * @param predictionId id for this prediction, see general description
  * @param timestamp timestamp (millis, long) of the prediction
  * @param modelId id (string) of the model that made the prediction
  * @param results Map[Array[Double\]\] of all results. Always in there: 'probabilities', which gives a probability
  *                distribution about all the possible actions
  */
case class PredictorResult(
                          predictionId: Long,
                          timestamp: Long,
                          modelId: String,
                          results: Map[String, Array[Double]]
                         ) {
  import PredictorResult._
}