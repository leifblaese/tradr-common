package tradr.common.trading


import java.sql.{Connection, DriverManager, ResultSet}

import com.mysql.cj.api.jdbc.Statement
import com.mysql.cj.jdbc.PreparedStatement
import com.typesafe.config.{Config, ConfigFactory}
import tradr.common.util.MySqlConnector

import scala.util.{Failure, Success, Try}


object Portfolio {


  def getCurrentPortfolio(portfolioId: String, conf: Config) = {


    val query = s"SELECT * FROM portfolio.portfolio1 ORDER BY id LIMIT 1"
    val resultSet = MySqlConnector.executeQuery(query)
    (0 until resultSet.getMetaData.getColumnCount).map(i => resultSet.getMetaData.getColumnName(i))

  }


  def hold(portfolio: Portfolio, conf: Config): Portfolio = {
    portfolio
  }

  def buy(portfolio: Portfolio, conf: Config): Portfolio = {
    // Change values of the portfolio in mysql and return the new, current portfolio
    portfolio
  }

  def sell(portfolio: Portfolio, conf: Config): Portfolio = {
    // Change values of the portfolio in mysql and return the new, current portfolio
    portfolio


  }

  def close(portfolio: Portfolio, conf: Config): Portfolio = {
    // Change values of the portfolio in mysql and return the new, current portfolio
    portfolio
  }

}


/**
  * Portfolio is a case class holding the current portfolio, i.e. the
  * amount of currencies held at a certain time point
  *
  * This corresponds to a table stored on Cassandra. The values are snapshots
  * of the actual portfolio held by the broker.
  *
  * The portfolioId identifies the portfolio for a specific run, which is needed
  * in case several portfolios are used in parallel
  * @param currencies
  */
case class Portfolio(portfolioId: String,
                     currencies: Map[Currencies.Value, Double])
