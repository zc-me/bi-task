package com.timing.bitaskadmin.core.route.strategy;

import com.timing.bitaskadmin.core.route.ExecutorRouter;
import com.timing.bitaskcore.biz.model.ReturnT;
import com.timing.bitaskcore.biz.model.TriggerParam;

import java.util.List;
import java.util.Random;

public class ExecutorRouteRandom extends ExecutorRouter {

    private static Random localRandom = new Random();

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        String address = addressList.get(localRandom.nextInt(addressList.size()));
        return new ReturnT<String>(address);
    }

}
