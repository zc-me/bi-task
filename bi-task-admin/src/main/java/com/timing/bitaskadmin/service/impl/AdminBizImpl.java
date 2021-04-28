package com.timing.bitaskadmin.service.impl;

import com.timing.bitaskadmin.core.thread.JobCompleteHelper;
import com.timing.bitaskadmin.core.thread.JobRegistryHelper;
import com.timing.bitaskcore.biz.AdminBiz;
import com.timing.bitaskcore.biz.model.HandleCallbackParam;
import com.timing.bitaskcore.biz.model.RegistryParam;
import com.timing.bitaskcore.biz.model.ReturnT;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminBizImpl implements AdminBiz {


    @Override
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
        return JobCompleteHelper.getInstance().callback(callbackParamList);
    }

    @Override
    public ReturnT<String> registry(RegistryParam registryParam) {
        return JobRegistryHelper.getInstance().registry(registryParam);
    }

    @Override
    public ReturnT<String> registryRemove(RegistryParam registryParam) {
        return JobRegistryHelper.getInstance().registryRemove(registryParam);
    }

}
