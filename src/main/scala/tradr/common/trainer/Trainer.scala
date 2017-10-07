package tradr.common.trainer

trait Trainer {

  /**
    * Train and save a model.
    * Get a set of trades from the DB, use them for a gradient update
    * apply it and save the updated model
    */
  def train(): Unit = {

  }

}
