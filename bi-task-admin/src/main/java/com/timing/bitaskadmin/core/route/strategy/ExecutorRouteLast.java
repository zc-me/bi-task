package com.timing.bitaskadmin.core.route.strategy;

import com.timing.bitaskadmin.core.route.ExecutorRouter;
import com.timing.bitaskcore.biz.model.ReturnT;
import com.timing.bitaskcore.biz.model.TriggerParam;

import java.util.List;

public class ExecutorRouteLast extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        return new ReturnT<String>(addressList.get(addressList.size()-1));
    }

}
