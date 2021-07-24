package com.timing.bitaskdataxexecuter.datax.impl;

import com.alibaba.datax.core.Engine;
import com.timing.bitaskdataxexecuter.datax.interfaces.DataxSyncTaskSV;
import com.timing.bitaskdataxexecuter.http.HttpUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataxSyncTaskSVImpl implements DataxSyncTaskSV {

    /**
     * 这个是存放整个datax相关配置的路径，"/datax"的上级目录
     */
    private static final String DATAX_PATH = "/home/bi/bi-task/config";

    private static final Logger logger = LoggerFactory.getLogger(DataxSyncTaskSVImpl.class);

    private static final List<Map> datasSyncList = new ArrayList<Map>();

    static {
        // 一次需要同步个的话，直接构造map添加进去就行
        Map MC_MCBI = new HashMap();
        MC_MCBI.put("tableName", "MC_MCBI");
        MC_MCBI.put("jsonName", "MC_MCBI.json");
        MC_MCBI.put("state", "U");
        datasSyncList.add(MC_MCBI);
    }

    @Override
    public void syncMysqlData2Mysql() {
        logger.error("数据同步开始！！！");
        try {
            //datax编译后的目录
            String path1 = DATAX_PATH + "/datax/target/conf/core.json";
            path1 = path1.substring(0, path1.indexOf("core.json") - 6);
            System.setProperty("datax.home", path1);
            System.setProperty("now", LocalTime.now().toString());

            for (Map syncInfoMap : datasSyncList) {
                if ("U".equals(MapUtils.getString(syncInfoMap, "state"))) {
                    //自己的json文件路径
                    String path2 = DATAX_PATH + "/datax/json/" + MapUtils.getString(syncInfoMap, "jsonName");
                    String[] dataxArgs = {"-job", path2, "-mode", "standalone", "-jobid", "-1"};
                    Engine.entry(dataxArgs);
                }
            }
        } catch (Throwable throwable) {
            logger.error("数据同步异常:", throwable);
        }
        logger.error("数据同步结束！！！");

        // 数据采集完成后进行汇总
        HttpUtils.callInterfaceByHttp("/summary/insertHotelProcurementSummary", new HashMap());
    }
}
