package tradr.common.models

import com.typesafe.config.Config
import tradr.common.trading.Trade

trait Model {

//  /**
//    * Create a new model or load it from disk
//    * @return
//    */
//  def apply(): Model
//
//  /**
//    * Load a model with a given id (string) where the location and everything else
//    * must be specified by the config. Returns a Model
//    * @param id String, id of the model
//    * @param conf Typesafe Configuration that specifies everything needed to load the model
//    * @return Model
//    */
//  def load(id: String, conf: Config): Model
//
//
//  /**
//    * Save a trained model with a given id. The config specifies the save folder etc.
//    * @param id id of the saved model (e.g. save file name)
//    * @param conf Typesafe Configuration that specifies where to save the model etc.
//    */
//  def save(id: String, conf: Config): Unit

  /**
    * Predict actions for a given frame of information
    * @param frame
    * @return
    */
  def predict(frame: Array[Double]): Map[String, Array[Double]]


  /**
    * Train the coefficients of a model on a set of given trades.
    * @return
    */

  def train(trades: Array[Trade]): Model

}
