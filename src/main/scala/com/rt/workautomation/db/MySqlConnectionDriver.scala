package com.rt.workautomation.db

/**
  * Created by mutyalart on 3/28/17.
  */
import java.sql.{Connection, DriverManager, SQLException, SQLTimeoutException}

import scala.util.{Failure, Success, Try}


object MySqlConnectionDriver {

  private val (url, driver, username, password) = ("jdbc:mysql://localhost:3306/metrics", "com.mysql.jdbc.Driver", "root", "root")
  private var connection: Connection = _

  try {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
  } catch {
    case e: Exception => e.printStackTrace
  }

  def getConnection(): Connection = {
    connection
  }

  def closeConnection() {
    connection.close
  }

  @throws(classOf[SQLException])
  def executeSQLQuery(sql: String): Unit = {
    try {
      println(sql)
      val statement = connection.createStatement
      statement.executeUpdate(sql);
    } catch {
      case e: SQLTimeoutException => e.printStackTrace
      case unknown => throw unknown
    } finally {
      closeConnection()
    }
  }


  def updateSQLQueryFunctional(sql: String): Try[Unit] = {
    val statement = connection.createStatement
    Try{
      statement.executeUpdate(sql)
    }
  }

  def excuteQueryFunctional(sql: String): Try[Any] = {
    val statement = connection.createStatement
    Try{
      statement.executeQuery(sql)
    }
  }
}
