package tradr.common.trading

import play.api.libs.json.{Json, Reads, Writes}

object Trade {


  implicit val TradeReads: Reads[Trade] = Json.reads[Trade]
  implicit val TradeWrites: Writes[Trade] = Json.writes[Trade]


  def getEmpty = Trade(id = 0L, tradeSequence = Seq())

  def addPartialTrade(partialTrade: PartialTrade,
                      currentTrade: Option[Trade]): Trade = {
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

  def computeProfit(trade: Trade): Double = {
    val tradedInstrument = trade.tradeSequence.head.instrument
    val baseCurrency = Instruments.getBaseCurrency(tradedInstrument)

    trade.tradeSequence.last.portfolioChange(baseCurrency) + trade.tradeSequence.head.portfolioChange(baseCurrency)
  }
}

/**
  * A Trade is a sequence of one or more ordinary partial trades and one closing trade
  * For example one sequence might be (buy, sell, buy, sell, buy, hold, buy, close).
  * Once a trade is complete (that is, its sequence terminated with a close), we can
  * compute the reward and use it to train our model
  *
  * @param id Id of the trade
  * @param tradeSequence Sequence of partialTrades
  */
case class Trade(
                  id: Long,
                  tradeSequence: Seq[PartialTrade]
                ) extends Serializable




