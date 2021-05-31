package com.timing.bitaskdataxexecuter.datax;

import com.alibaba.datax.core.Engine;
import com.alibaba.datax.core.util.ExceptionTracker;
import com.timing.bitaskcore.context.XxlJobHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.time.LocalTime;

public class DataxTest {

    public void syncMysqlData2Mysql() {
        Logger logger = LoggerFactory.getLogger(DataxTest.class);

        XxlJobHelper.log("数据同步开始！！！");

        try {
            //datax编译后的目录
            String path1 = "/home/bi/BI-TASK/executer/config/datax/target/conf/core.json";
            path1 = path1.substring(0, path1.indexOf("core.json") - 6);
            System.setProperty("datax.home", path1);
            System.setProperty("now", LocalTime.now().toString());

            //自己的json文件路径
            String path2 = "/home/bi/BI-TASK/executer/config/datax/json/mysql2mysql.json";
            String[] dataxArgs = {"-job", path2, "-mode", "standalone", "-jobid", "-1"};
            Engine.entry(dataxArgs);
        } catch (Throwable throwable) {
            logger.error("数据同步异常:", throwable);
            XxlJobHelper.log("数据同步异常：", throwable);
        }

        XxlJobHelper.log("数据同步结束！！！");
    }

    public void syncOracleData2Oracle() {
        XxlJobHelper.log("数据同步开始！！！");

        try {
            //datax编译后的目录
            ClassPathResource resource1 = new ClassPathResource("datax/target/conf/core.json");
            String path1 = resource1.getFile().getAbsolutePath();
            path1 = path1.substring(0, path1.indexOf("core.json") - 6);
            System.setProperty("datax.home", path1);
            System.setProperty("now", LocalTime.now().toString());

            //自己的json文件路径
            ClassPathResource resource2 = new ClassPathResource("datax/json/oracle2oracle.json");
            String path2 = resource2.getFile().getAbsolutePath();
            String[] dataxArgs = {"-job", path2, "-mode", "standalone", "-jobid", "-1"};
            Engine.entry(dataxArgs);
        } catch (Throwable throwable) {
            XxlJobHelper.log("数据同步异常：", ExceptionTracker.trace(throwable));
        }

        XxlJobHelper.log("数据同步结束！！！");
    }

    public void syncOracleData2Mysql() {
        XxlJobHelper.log("数据同步开始！！！");

        try {
            //datax编译后的目录
            ClassPathResource resource1 = new ClassPathResource("datax/target/conf/core.json");
            String path1 = resource1.getFile().getAbsolutePath();
            path1 = path1.substring(0, path1.indexOf("core.json") - 6);
            System.setProperty("datax.home", path1);
            System.setProperty("now", LocalTime.now().toString());

            //自己的json文件路径
            ClassPathResource resource2 = new ClassPathResource("datax/json/oracle2mysql.json");
            String path2 = resource2.getFile().getAbsolutePath();
            String[] dataxArgs = {"-job", path2, "-mode", "standalone", "-jobid", "-1"};
            Engine.entry(dataxArgs);
        } catch (Throwable throwable) {
            XxlJobHelper.log("数据同步异常：", ExceptionTracker.trace(throwable));
        }

        XxlJobHelper.log("数据同步结束！！！");
    }
}
