-- check workflow

    -- startWorkFlow creates a rulesID and notes down in the table
        -- java -cp *.jar com.comcast.ebi.wa.logapi.LoggerApp --function startWorkFlow --modelId m007 --processDt 20161231
        insert into metrics.runid_generator (model_id) values('m007');
        insert into metrics.workflow_details (runid,model_id,run_dt,start_time,end_time,run_status,process_dt) values(47,'m007',current_date,current_timestamp,null,'started','2016-12-31');

    -- endWorkFlow check the failure of tasks and update the status
        -- java -cp *.jar com.comcast.ebi.wa.logapi.LoggerApp --function endWorkFlow --modelId m007 --processDt 20171231

        select count(*) from task_status where task_status != 'completed' group by concat(runid,model_id);
        -- on success count == 0
            insert into metrics.workflow_details (runid,model_id,run_dt,start_time,end_time,run_status,process_dt) values(47,'m007',current_date,null,current_timestamp,'completed','2016-12-31')
                            on duplicate key update
                            end_time = current_timestamp,
                            run_status = 'completed';
        -- on failure
            insert into metrics.workflow_details (runid,model_id,run_dt,start_time,end_time,run_status,process_dt) values(47,'m007',current_date,null,current_timestamp,'failed','2016-12-31')
                            on duplicate key update
                            end_time = null,
                            run_status = 'failed';


-- check task

    -- startTask
        -- java -cp *.jar com.comcast.ebi.wa.logapi.LoggerApp --function startWorkFlow --modelId m007 --taskId 01 --processDt 20171231
                insert into metrics.task_status (runid,model_id,task_id,task_start_time,task_end_time,task_status,comment) values(47,'m007',01,current_timestamp,null,'started','nocomments')
                            on duplicate key update
                            task_start_time = current_timestamp,
                            task_status = 'started';
    -- updateTask
        -- java -cp *.jar com.comcast.ebi.wa.logapi.LoggerApp --function updateTask --modelId m007 --taskId 01 --processDt 20171231 --taskStatus 0

            -- on success (taskStatus = 0)
                insert into metrics.task_status (runid,model_id,task_id,task_start_time,task_end_time,task_status,comment) values(47,'m007',01,current_timestamp,null,'completed','nocomments')
                            on duplicate key update
                            task_end_time = current_timestamp,
                            task_status = 'completed';

            -- on failure (taskStatus != 0)
                insert into metrics.task_status (runid,model_id,task_id,task_start_time,task_end_time,task_status,comment) values(47,'m007',01,current_timestamp,null,'failed','nocomments')
                            on duplicate key update
                            task_end_time = current_timestamp,
                            task_status = 'failed';




-- workflow_scheduler

        -- work flow setup, setup model_id checkpoint_start_dt,schedule_period_type,schedule_interval (has to setup all custom jobs other than daily jobs)
                insert into metrics.workflow_scheduler (model_id,checkpoint_start_dt,run_status,schedule_period_type,schedule_interval)
                        values(modelId,checkpointStartDt,'SETUP_VALUES_ONLY',periodType,scheduleInterval);
        -- get new process date and update init file
                -- get checkpoint_start_dt + schedule_interval + schedule_type
        -- work flow start (update runId,processDt,run_status)
                insert into metrics.workflow_scheduler (model_id,run_id,checkpointStartDt,current_process_dt,no_of_tasks_completed,no_of_tasks_failed,no_of_tasks_running,run_status,schedule_period_type,schedule_interval)
                        values(modelId,runId,processDt,processDt,0,0,0,'RUNNING','DAILY',1)
                        on duplicate key update
                        run_id = runId,
                        current_processing_dt = processDt,
                        no_of_tasks_completed = 0,
                        no_of_tasks_failed = 0,
                        no_of_tasks_runnning = 0,
                        run_status='RUNNING';

        -- task start (after updating task_status table , update no of tasks: completed , failed , running from task_table)
        -- task update (after updating task_status table , update no of tasks: completed , failed , running from task_table)
        -- work flow complete ( on complete update checkpoint_start_dt)