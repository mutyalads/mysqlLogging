package com.rt.workAutomation.BootApp

/**
  * Created by mutyalart on 3/27/17.
  */

import com.rt.workAutomation.db.MySqlConnectionDriver._
import com.rt.workAutomation.db.DSL._

object ScalaJDBCConnectionTest extends App {
  // connect to the database named "mysql" on port 8889 of localhost

  try {
    createRunId("m007")
    println("retrun RunID: " +getRunId("m007").get)
    val rt = getRunId("m007").get
    startWorkFlow(rt,"m007","20161231")


  } catch {
    case e: Exception => e.printStackTrace
  }
  closeConnection()
}