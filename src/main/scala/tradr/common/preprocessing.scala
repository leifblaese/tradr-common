//package tradr.trader.Utils
//
//import java.io.{BufferedWriter, FileOutputStream, FileWriter, ObjectOutputStream}
//
//import org.apache.commons.math3.analysis.interpolation.SplineInterpolator
//import org.joda.time.DateTime
//import org.joda.time.format.DateTimeFormat
//
//import scala.io.Source
//
///**
//  * Created by leifblaese on 04.04.16.
//  */
//object Preprocess {
//
//
////
////  def linearInterpolation(
////                           data: List[(DateTime, Double)],
////                           gaps: List[(Int, Int)]): List[List[Double]] = {
////
////
////
////  }
//
//  def splineInterpolation(
//             data: Vector[(DateTime, Double)],
//             gaps: Vector[(Int, Int)]): Vector[Vector[Double]] = {
//
//    // For each gap:
//    //  Take values before, after
//    //  Interpolate a spline
//    //  Predict missing points using that spline
//
//    val interp = new SplineInterpolator()
//    val gapFills: Vector[Vector[Double]] = gaps.indices.map {
//      i => {
//        val (gapSize, gapIdx) = gaps(i)
//        println("GapIdx:" + gapSize.toString)
//        println("GapIdx:" + gapIdx.toString)
//        val neg = if (gapIdx > 10) {
//          10
//        } else {
//          gapIdx
//        }
//        println("neg: " + neg.toString)
//        val pos = if (gapIdx > data.length - 10) {
//          gaps.length
//        } else {
//          10
//        }
//        println("pos " + pos.toString)
//        val x = (List.range(0, neg) ++ List.range(neg + gapSize, neg + pos + gapSize)).map(_.toDouble).toArray
//        val tmp = data.map(_._2)
//        val y = tmp.slice(gapIdx - neg, gapIdx + pos).toArray
//        val f = interp.interpolate(x, y)
//        (neg + 1 to neg + gapSize).map(i => f.value(i.toDouble)).toVector
//      }
//    }.toVector
//    gapFills
//  }
//
//  def preprocessHistDataDir(inputDir: String, outputDir: String): Unit = {
//    val histDataFileNames: Array[(String, String)] = Util.listFilesAndSuffixes(inputDir)
//                                                         .filter{case (name, suffix) => suffix == "csv"}
//
//    histDataFileNames.foreach(
//      fileNameTuple => {
//        val inputFileName = inputDir + "/" + fileNameTuple._1 + "." + fileNameTuple._2
//        val outputFileName = outputDir + "/" + fileNameTuple._1 + "_processed." + fileNameTuple._2
//        preprocessHistData(inputFileName, outputFileName)
//      })
//
//
//  }
//
//
//  def preprocessHistData(inputFile: String, outputFile: String): Unit = {
//
////    val writer = new BufferedWriter(new FileWriter(outputFile))
////
////    // Read in the data
////    val open= Util.readHistData(inputFile)
//////    val open = data.map{case (time, o,h,l,c,v) => (time, o)}
////
////    val t1 = open.map{case (time, o) => time}.slice(0, open.length - 1)
////    val t2 = open.map{case (time, o) => time}.slice(1, open.length)
////
////    // Find indices of where the gaps are
////    val gaps: Vector[(Int, Int)] = (t1 zip t2).map{ case (a,b) => b.getMillis/60000 - a.getMillis/60000 - 1 }
////      .zipWithIndex
////      .filter{case (l,i) => l > 0}
////      .map{case (l,i) => (l.toInt, i)}
////    //      .sortWith{case ((l1,i1),(l2,i2)) => i1 > i2}
////    gaps.foreach(println)
////
////    // Get the toal number of missing steps
////    val totalNumGaps = gaps.map{case (a,b) => a}.sum
////
////    // Create data to fill the gaps using spline interpolation
////    val gapFills = Preprocess.splineInterpolation(open, gaps)
////
////    // Reserve memory for the new data
////    val newData = Vector.fill(t1.size + totalNumGaps)(0.0)
////    val oldData = open.map{case (time, open) => open}.toVector
////
////    val gapIdx = gaps.map{case (gapSize, gapIdx) => gapIdx}
////    val adjGapIdx = gapIdx + cumsum(gapIdx) // Breeze cumulative sum and breeze vector addition
////
////
////    // Fill gaps in open
////    var n = 0
////    data.indices.foreach(
////      i => {
////        println("i: " + i.toString)
////        if (gaps(n)._2 == i) { // If we have a gap
////          // First, write the normal data, then comes the gap fill
////          val (gapSize, gapIdx) = gaps(n)
////          writer.write(open(i)._1.getMillis.toString + "," + open(i)._2.toString + "\n")
////          // Create the new time
////          val newTime = (1 to gapSize).map(j => open(gapIdx)._1.plusMinutes(j))
////          val newData = newTime zip gapFills(n)
////          newData.foreach{case (dt, o) => writer.write(dt.getMillis.toString + "," + o.toString + "\n")}
////          n = n+1
////        } else { // If we have no gap
////          writer.write(open(i)._1.getMillis.toString + "," + open(i)._2.toString + "\n")
////        }
////      }
////    )
////    writer.flush()
////    writer.close()
//  }
//
//}
