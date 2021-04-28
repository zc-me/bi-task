package com.timing.bitaskadmin.core.alarm;

import com.timing.bitaskadmin.core.model.XxlJobInfo;
import com.timing.bitaskadmin.core.model.XxlJobLog;

public interface JobAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param jobLog
     * @return
     */
    public boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog);

}
