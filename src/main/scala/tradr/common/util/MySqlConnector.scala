package tradr.common.util

import java.sql.{Connection, DriverManager, ResultSet}

import com.typesafe.config.ConfigFactory

import scala.util.{Failure, Success, Try}

object MySqlConnector {


  def getMySQLConnection: Connection = {

    val conf = ConfigFactory.load()

    val mySqlHost = conf.getString("mysql.ip")
    val mySqlUser = conf.getString("mysql.user")
    val mySqlPW = conf.getString("mysql.password")

    val db = conf.getString("mysql.database")
    val url = s"jdbc:mysql://$mySqlHost/$db"


    Try(DriverManager.getConnection(url, mySqlUser, mySqlPW)) match {
      case Success(conn) => conn
      case Failure(ex) =>
        throw new Exception(s"No connection to MySQL with $url and user $mySqlUser")
    }
  }

  private var openConnection: Option[Connection] = None

  def getConnection = {
    val usedConnection = openConnection
      .flatMap { connection =>
        if (!connection.isValid(10)) {
          Some(connection)
        } else {
          println("No valid connection found. Creating a new one one.")
          None
        }
      }
      .getOrElse(getMySQLConnection)
    openConnection = Some(usedConnection)
    usedConnection
  }


  def executeQuery(query: String): ResultSet = {

    val connection = getConnection
    connection
      .prepareStatement(query)
      .executeQuery()
  }

}
