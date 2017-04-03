package com.rt.workautomation.db

import java.sql.ResultSet

import com.rt.workautomation.db.MySqlConnectionDriver._

import scala.util.{Failure, Success}

/**
  * Created by mutyalart on 4/2/17.
  */
object FunctionalDSL {
  def startWorkFlow(runid:Long,model_id:String,processdt:String): Unit ={
    val query = s"""
                   |insert into metrics.workflow_details (runid,model_id,run_dt,start_time,end_time,run_status,process_dt)
                   |values($runid,'$model_id',current_date,current_timestamp,null,'started',"$processdt")
                   |on duplicate key update
                   |start_time = current_timestamp,
                   |run_status = 'started';""".stripMargin
    val x = updateSQLQueryFunctional(query)

    x match {
      case Success(i) => println(s"success, i = $i")
      case Failure(t) => println(t.getStackTrace.mkString("/n"))
    }
  }


  def createRunId(modelId: String): Unit ={
    val query = s"Insert into metrics.runid_generator (model_id) values('$modelId');"
    updateSQLQueryFunctional(query)
  }


  def getRunId(modelId: String): Option[Long] = {
    val query = s"select max(runid) from metrics.runid_generator where model_id='${modelId}'"
    val rs = excuteQueryFunctional(query).asInstanceOf[ResultSet]
    rs.next()
    Some(rs.getLong(1))
  }

}
