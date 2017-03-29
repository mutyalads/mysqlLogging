package com.rt.workAutomation.Util

import org.slf4j.LoggerFactory

/**
  * Created by mutyalart on 3/28/17.
  */

class CommandLineParser{

}
object CommandLineParser {

  val logger = LoggerFactory.getLogger(classOf[CommandLineParser])
    def getParmeterMap(args: scala.Array[String]): Map[String,String] = {
      if (args.length < 2) {
        logger.info("Not Enough Arguments Provided")
        System.exit(1)
      }
      try {
        val params = args.zipWithIndex.filter(z => z._2 == 0 || z._2 % 2 == 0).map(x => (args(x._2).replaceFirst("--", ""), args(x._2 + 1))).toMap
        params
      } catch {
        case e: Exception =>
          logger.info("Required Param Not Found")
          System.exit(1)
          Map()
      }
    }
}
