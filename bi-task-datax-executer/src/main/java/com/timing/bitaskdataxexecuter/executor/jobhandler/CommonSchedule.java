package com.timing.bitaskdataxexecuter.executor.jobhandler;

import com.timing.bitaskcore.context.XxlJobHelper;
import com.timing.bitaskcore.handler.annotation.XxlJob;
import com.timing.bitaskdataxexecuter.datax.ExecutorTest;
import com.timing.bitaskdataxexecuter.datax.impl.DataxSyncTaskSVImpl;
import com.timing.bitaskdataxexecuter.datax.interfaces.DataxSyncTaskSV;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommonSchedule {

    @XxlJob("executorTest")
    public void executorTest() throws Exception {
        // 任务调度及参数获取测试 start
        String param = XxlJobHelper.getJobParam();
        Map input = new HashMap();
        input.put("PARAM", param);
        ExecutorTest executorTest = new ExecutorTest();
        executorTest.executorJob(input);
        // 任务调度及参数获取测试 end
    }

    @XxlJob("executorDataxSync")
    public void executorDataxSync() throws Exception {
        // datax数据同步测试 start
        DataxSyncTaskSV dataxSyncTaskSV = new DataxSyncTaskSVImpl();
        dataxSyncTaskSV.syncMysqlData2Mysql();
        // datax数据同步测试 end
    }

}
