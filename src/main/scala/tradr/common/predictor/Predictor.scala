package tradr.common.predictor

trait Predictor {
  // predictor returns json of prediction
  def predict(time: Long) : String

}
