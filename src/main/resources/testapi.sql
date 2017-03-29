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
