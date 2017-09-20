//package tradr.util
//
//import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
//
//import tradr.trader.Trades.Currencies
//import org.joda.time.DateTime
//import tradr.common.Trade
//import tradr.trader.Trader
//
//
//object Summary {
//
//  def getSummary(trader: Trader) = {
//    trader.tradeHistory.foldLeft(Summary(trader.id, tradeHistory = trader.tradeHistory)){
//      case (summary, trade) =>
//        val profit = trade.tradeSequence.last.portfolioChange(Currencies.EUR)
//
//
//        // Get all the changes of the eur portfolio of all trades
//        val eurChanges = trade
//          .tradeSequence
//          .map(_.portfolioChange)
//          .map(_.apply(Currencies.EUR))
//        // scan (summing it all up)
//        val eur = eurChanges.scan(summary.eur.last)(_ + _).drop(1)
//
//        summary.copy(
//          profits = summary.profits :+ profit,
//          eur = summary.eur ++ eur
//        )
//    }
//  }
//
//
//  def writeCurrencies(summary: Summary, folder: String): Unit = {
//    val outputFile = new File(folder + "eur.csv")
//    val bw = new BufferedWriter(new FileWriter(outputFile))
//
//    summary.eur.foreach{i =>
//      val str = f"$i%3.4f" + "\n"
//      bw.write(str)
//    }
//    bw.close()
//  }
//
//  def writeSummary(summary: Summary, folder: String): Unit = {
//
//    writeProfits(summary, folder)
//    writeCurrencies(summary, folder)
//    writeTrades(summary, folder)
//  }
//
//  def writeProfits(summary: Summary, folder: String): Unit = {
//    val outputFile = new File(folder + "profits.csv")
//    val bw = new BufferedWriter(new FileWriter(outputFile))
//
//
//    summary.profits.foreach{i =>
//      val str = f"$i%3.4f" + "\n"
//      bw.write(str)
//    }
//    bw.close()
//  }
//
//
//  def writeTrades(summary: Summary, folder: String): Unit = {
//    val outputFile = new File(folder + "trades.csv")
//    val bw = new BufferedWriter(new FileWriter(outputFile))
//
//    val formatter = java.text.NumberFormat.getCurrencyInstance
//
//    summary.tradeHistory.foreach{trade =>
//      val str = trade.toString + "\n"
//      bw.write(str)
//    }
//    bw.close()
//  }
//
//
//
//}
//
//// Summary for one training run
//case class Summary(
//                    traderID: Int,
//                    profits:  List[Double] = List(),
//                    tradeHistory: List[Trade] = List(),
//                    eur: Seq[Double] = Seq(100.0)
//                  )
