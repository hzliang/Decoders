package com.soundcloud.spark.pagerank

import java.text.SimpleDateFormat
import java.util.Calendar

/**
  * @Title: DateTest
  * @ProjectName pg
  * @Description: TODO
  * @author huzuoliang
  * @date 2018/7/2315:54
  */
object DateTest {

  def main(args: Array[String]): Unit = {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_MONTH, -1)
    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    val calDt = sdf.format(calendar.getTime)

    println(calDt)
  }

}
