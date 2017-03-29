USE metrics;

DROP table IF EXISTS runid_generator;
CREATE TABLE `runid_generator` (
   `runid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `model_id` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`runid`),
   KEY `model_id` (`model_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS workflow_details;
CREATE TABLE `workflow_details` (
   `runid` bigint(20) NOT NULL,
   `model_id` varchar(50) NOT NULL,
   `run_dt` date DEFAULT NULL,
   `start_time` datetime DEFAULT NULL,
   `end_time` datetime DEFAULT NULL,
   `run_status` varchar(10) DEFAULT 'STARTED',
   `process_dt` date DEFAULT NULL,
   PRIMARY KEY (`runid`,`model_id`),
   KEY `model_id` (`model_id`) USING BTREE,
   KEY `run_dt` (`run_dt`),
   KEY `run_status` (`run_status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS task_status;
CREATE TABLE `task_status` (
   `runid` bigint(20) NOT NULL,
   `model_id` varchar(50) NOT NULL,
   `task_id` smallint(4) NOT NULL,
   `task_start_time` datetime DEFAULT NULL,
   `task_end_time` datetime DEFAULT NULL,
   `task_status` varchar(10) DEFAULT 'DEFAULT',
   `comment` varchar(20) DEFAULT 'NO COMMENTS',
   PRIMARY KEY (`runid`,`model_id`,`task_id`),
   KEY `model_id` (`model_id`) USING BTREE,
   KEY `task_status` (`task_status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;


