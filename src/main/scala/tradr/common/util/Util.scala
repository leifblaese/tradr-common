package tradr.common.util

import java.io._
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import scala.io.Source



object Util {



  // Compute epsilon for epsilon-greedy search strategy
  def computeEpsilon(i: Long, n: Long): Double = {
    if (i > n) {
      0.1
    } else {
      (n - i).toDouble/n.toDouble
    }
  }


  def loadHistData(inputFile: String): Seq[(Long, Double)] = {

    val s = new FileInputStream(inputFile)

    Source
      .fromInputStream(s)
      .getLines()
      .map(s => s.split(","))
      .map(arr => (arr.head.toDouble, arr.tail.head.toDouble))
      .map(a => (a._1.toLong, a._2))
      .toSeq
  }

  /**
    * Generic save method
    */
  def save[T](outputFile: String, data: Vector[T]): Unit = {
    val outStream = new ObjectOutputStream(new FileOutputStream(outputFile))
    outStream.writeObject(data)
    outStream.close()
  }

  /**
    * Generic load method
    */
  def load[T](inputFile: String): T = {
    val is = new ObjectInputStream(new FileInputStream(inputFile))
    val obj: AnyRef = is.readObject()
    obj.asInstanceOf[T]
  }


  def readHistData(filename: String): Vector[(DateTime, Double)] = {
    val lines = Source.fromFile(filename).getLines
    val splitLines = lines.toList.map(s => s.split(";"))
    val formatter = DateTimeFormat.forPattern("YYYYMMDD HHmmss")

    splitLines.map(
      a => (
        formatter.parseDateTime(a(0)),
        a(1).toDouble
        )
    ).toVector

  }




}
