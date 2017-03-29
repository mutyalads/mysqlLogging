package com.rt.workAutomation.db

/**
  * Created by mutyalart on 3/27/17.
  */
import java.sql.Date

import MySqlConnectionDriver._

object DSL{

  def createRunId(modelId: String): Unit ={
    val query = s"Insert into metrics.runid_generator (model_id) values('$modelId');"
    executeSQLQuery(query)
  }

  def getRunId(modelId: String): Option[Long] = {
    val query = s"select max(runid) from metrics.runid_generator where model_id='${modelId}'"
    val connection = getConnection()
    val rs = connection.createStatement.executeQuery(s"select max(runid) from metrics.runid_generator where model_id='${modelId}'")
    rs.next()
    Some(rs.getLong(1))
  }

  def startWorkFlow(runid:Long,model_id:String,processdt:String): Unit ={
    val query = s"""
                    |insert into metrics.workflow_details (runid,model_id,run_dt,start_time,end_time,run_status,process_dt)
                    |values($runid,'$model_id',current_date,current_timestamp,null,'started',"$processdt")
                    |on duplicate key update
                    |start_time = current_timestamp,
                    |run_status = 'started';""".stripMargin
    executeSQLQuery(query)
  }

  def endWorkFlow(runid:Int,model_id:String,processdt:Date): Unit ={
    if(istasksFailed(runid,model_id)){
      val query = s"""
      |insert into metrics.workflow_details (runid,model_id,run_dt,start_time,end_time,run_status,process_dt)
      |values($runid,'$model_id',current_date,null,current_timestamp,'failed',${processdt})
      |on duplicate key update
      |end_time = null,
      |run_status = 'failed';"""
      executeSQLQuery(query)
    }else{
      val query = s"""
       |insert into metrics.workflow_details (runid,model_id,run_dt,start_time,end_time,run_status,process_dt)
       |values($runid,'$model_id',current_date,null,current_timestamp,'completed',${processdt})
       |on duplicate key update
       |end_time = current_timestamp,
       |run_status = 'completed';"""
      executeSQLQuery(query)
    }
  }


  def istasksFailed(runid:Int, model_id:String): Boolean ={
    val query = s"select count(*) from task_status where task_status != 'completed' where runid=$runid and model_id='$model_id';"
    val count = executeSQLQuery(query)
    if (count == 0 ){
      false
    } else {
      true
    }
  }

  def startTask(runid:String,model_id:String,task_id:Int): Unit ={
    val query = s"""insert into metrics.task_status (runid,model_id,task_id,task_start_time,task_end_time,task_status,comment) values($runid,'$model_id',$task_id,current_timestamp,null,'started','nocomments')
    | on duplicate key update
    | task_start_time = current_timestamp,
    | task_status = 'started';""".stripMargin
    executeSQLQuery(query)
  }

  def updateTask(runid:String,model_id:String,task_id:Int,task_status:Int): Unit ={
    var query = ""
    if (task_status == 0 ){
      query = s"""insert into metrics.task_status (runid,model_id,task_id,task_start_time,task_end_time,task_status,comment) values($runid,'$model_id',$task_id,current_timestamp,null,'completed','nocomments')
                 | on duplicate key update
                 | task_start_time = current_timestamp,
                 | task_status = 'completed';""".stripMargin
    }else {
      query = s"""insert into metrics.task_status (runid,model_id,task_id,task_start_time,task_end_time,task_status,comment) values($runid,'$model_id',$task_id,current_timestamp,null,'failed','nocomments')
                 | on duplicate key update
                 | task_start_time = current_timestamp,
                 | task_status = 'failed';""".stripMargin
    }
    executeSQLQuery(query)
  }

}

