package tradr.common.predictor

trait Predictor {

  // A predictor answers the prediction request with another json
  // with the results
  def predict(request: String) : String

}
