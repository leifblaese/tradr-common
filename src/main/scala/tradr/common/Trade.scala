package tradr.common

import tradr.common.PartialTrade

object Trade {

  def getEmpty = Trade(id = 0L, tradeSequence = Seq())

  def addPartialTrade(partialTrade: PartialTrade,
                      currentTrade: Option[Trade]) = {
    currentTrade match {
      case Some(trade) => trade.copy(tradeSequence = trade.tradeSequence :+ partialTrade)
      case _ => Trade(0L, Seq(partialTrade))
    }
  }

  def addClosingTrade(partialTrade: PartialTrade, currentTrade: Option[Trade]): Trade = {
    currentTrade  match {
      case Some(trade) => trade.copy(tradeSequence = trade.tradeSequence :+ partialTrade)
      case _ => throw new Exception("Closing trade with no trade currently open")
    }

  }

  def computeProfit(trade: Trade) = {
    val tradewiseBaseCurrency = trade.tradeSequence.head.currency1
    trade.tradeSequence.last.portfolioChange(tradewiseBaseCurrency) + trade.tradeSequence.head.portfolioChange(tradewiseBaseCurrency)
  }
}

/**
  * A Trade is a sequence of one or more ordinary partial trades and one closing trade
  * For example one sequence might be (buy, sell, buy, sell, buy, hold, buy, close).
  * Once a trade is complete (that is, its sequence terminated with a close), we can
  * compute the reward and use it to train our model
  *
  * @param id
  * @param tradeSequence
  */
case class Trade(
                  id: Long,
                  tradeSequence: Seq[PartialTrade]
                ) extends Serializable




