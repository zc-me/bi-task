package com.timing.bitaskdataxexecuter.datax;

import com.timing.bitaskcore.context.XxlJobHelper;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

    public void executorJob (Map input) {
        XxlJobHelper.log("zhangchun's xxljob test function from datax-executor");
        //有参数的情况 start
        String str = (String) input.get("PARAM");
        XxlJobHelper.log("datax-execuotr param:" + str);
        //有参数的情况 end
        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("datax-execuotr sout:zhangchun" + i);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
