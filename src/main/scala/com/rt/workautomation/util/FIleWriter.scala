package com.rt.workautomation.util

/**
  * Created by mutyalart on 4/2/17.
  */

import java.io._
object FIleWriter {

  val pw = new PrintWriter(new File("hello.txt"))
  pw.write("Hello, world")
  pw.close

/** Writer for writing small text with characters to a file
  *
  *@param outputFile
  *@param outText
  *
  */

  def writeSmallTextToOutputFile(outputFile: String, outText: String): Unit = {
  val file = new File(outputFile)
  val bw = new BufferedWriter(new FileWriter(file))
  bw.write(outText)
  bw.close()
 }
}
