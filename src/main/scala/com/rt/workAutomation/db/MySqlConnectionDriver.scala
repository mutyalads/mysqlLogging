package com.rt.workAutomation.db

/**
  * Created by mutyalart on 3/28/17.
  */
import java.sql.{Connection, DriverManager}


object MySqlConnectionDriver {

  private val (url, driver, username, password) = ("jdbc:mysql://localhost:3306/metrics", "com.mysql.jdbc.Driver", "root", "root")
  private var connection: Connection = _

  try {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
  }catch {
    case e: Exception => e.printStackTrace
  }

  def getConnection(): Connection = {
    connection
  }

  def closeConnection() {
    connection.close
  }

  def executeSQLQuery(sql:String): Unit ={
    println(sql)
    val statement = connection.createStatement
    statement.executeUpdate(sql);
  }
}
