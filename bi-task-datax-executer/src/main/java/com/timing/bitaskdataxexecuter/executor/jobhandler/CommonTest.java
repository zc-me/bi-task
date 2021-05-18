package com.timing.bitaskdataxexecuter.executor.jobhandler;

import com.timing.bitaskcore.context.XxlJobHelper;
import com.timing.bitaskcore.handler.annotation.XxlJob;
import com.timing.bitaskdataxexecuter.datax.DataxTest;
import com.timing.bitaskdataxexecuter.datax.ExecutorTest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommonTest {

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

    @XxlJob("executorDataxTest")
    public void executorDataxTest() throws Exception {
        // datax数据同步测试 start
        DataxTest dataxTest = new DataxTest();
        dataxTest.syncOracleData2Mysql();
        // datax数据同步测试 end
    }

}
