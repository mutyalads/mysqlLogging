package com.rt.workautomation.slickDAO

/**
  * Created by mutyalart on 4/2/17.
  */

import slick.driver.H2Driver.api._

import scala.concurrent.ExecutionContext.Implicits.global


object SlickCreateDatabasePool {


  private val db = Database.forConfig("h2mem1")
  try {
    def getDB(): Unit = {
      db
    }
  } finally db.close

}
