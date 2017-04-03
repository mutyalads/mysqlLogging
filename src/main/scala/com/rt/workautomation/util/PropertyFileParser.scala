package com.rt.workautomation.util

import java.io.FileInputStream
import java.util.Properties
import com.typesafe.config.ConfigFactory

/**
  * Created by mutyalart on 3/28/17.
  */
object PropertyFileParser {

  def getProperties(args: scala.Array[String]): Properties = {
    val prop = new Properties()
    prop.load(new FileInputStream("config.properties"))
    prop
  }

  def getConfigProperty(path: String): String ={
    val value = ConfigFactory.load().getString(path) //"my.secret.value"
    value
  }
}
